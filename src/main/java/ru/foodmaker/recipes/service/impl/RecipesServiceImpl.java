package ru.foodmaker.recipes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.foodmaker.recipes.dto.PreparationStepsDto;
import ru.foodmaker.recipes.dto.RecipeCategoriesDto;
import ru.foodmaker.recipes.dto.RecipeDto;
import ru.foodmaker.recipes.dto.RecipeIngredientsDto;
import ru.foodmaker.recipes.entity.*;
import ru.foodmaker.recipes.repository.IngredientsRepository;
import ru.foodmaker.recipes.repository.RecipesRepository;
import ru.foodmaker.recipes.repository.RecipeCategoriesRepository;
import ru.foodmaker.recipes.exception.EntityException;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ru.foodmaker.recipes.mapper.RecipeMapper;
import ru.foodmaker.recipes.service.RecipesService;

@Service
public class RecipesServiceImpl implements RecipesService {

    private final RecipesRepository recipesRepository;

    private final RecipeCategoriesRepository recipeСategoriesRepository;

    private final IngredientsRepository ingredientsRepository;

    private final RecipeMapper mapper;


    @Autowired
    public RecipesServiceImpl(RecipesRepository recipesRepository,
                              RecipeCategoriesRepository recipeСategoriesRepository,
                              IngredientsRepository ingredientsRepository,
                              RecipeMapper mapper
                              ) {

        this.recipeСategoriesRepository=recipeСategoriesRepository;
        this.ingredientsRepository=ingredientsRepository;
        this.recipesRepository = recipesRepository;
        this.mapper = mapper;

    }

    @Override
    @Transactional
    public RecipeDto createRecipe(RecipeDto recipeDto) {

        RecipeDto newRecipeDto;

        Recipe recipe = this.mapper.toRecipe(recipeDto);
        Recipe newRecipe = this.recipesRepository.save(recipe);

        List<RecipeIngredients> ingredientList = new ArrayList<>();
        for (RecipeIngredientsDto recipeIngredientsDto : recipeDto.getIngredients()) {
            RecipeIngredients ingredient = new RecipeIngredients();
            ingredient.setRecipeId(newRecipe.getRecipeId());
            ingredient.setQuantity(recipeIngredientsDto.getQuantity());
            ingredient.setIngredientId(recipeIngredientsDto.getIngredientId());
            ingredientList.add(ingredient);
        }
        newRecipe.setIngredients(ingredientList);

        List<PreparationSteps> preparationStepsList = new ArrayList<>();
        for (PreparationStepsDto preparationStepsDto : recipeDto.getSteps()) {
            PreparationSteps preparationSteps = new PreparationSteps();
            preparationSteps.setRecipeId(newRecipe.getRecipeId());
            preparationSteps.setStepNumber(preparationStepsDto.getStepNumber());
            preparationSteps.setStepDescription(preparationStepsDto.getStepDescription());
            preparationStepsList.add(preparationSteps);
        }
        newRecipe.setSteps(preparationStepsList);

        List<RecipeCategories> recipeCategoriesList = new ArrayList<>();
        for (RecipeCategoriesDto recipeCategoriesDto : recipeDto.getCategories()) {
            RecipeCategories recipeCategories = new RecipeCategories();
            recipeCategories.setRecipeId(newRecipe.getRecipeId());
            recipeCategories.setCategoryId(recipeCategoriesDto.getCategoryId());
            recipeCategoriesList.add(recipeCategories);
        }
        newRecipe.setCategories(recipeCategoriesList);

        Recipe savedRecipe=this.recipesRepository.save(recipe);
        newRecipeDto = this.mapper.toRecipeDto(savedRecipe);


        return newRecipeDto;
    }

    @Override
    public List<RecipeDto> getAllRecipes() {

        return Optional.of(this.recipesRepository.findAll())
                .stream()
                .flatMap(Collection::stream)
                .map(this.mapper::toRecipeDto)
                .toList();
    }

    @Override
    @Transactional
    public List<RecipeDto> findRecipesByCategoryId(Integer categoryId) {
                List<Integer> recipeIds = recipeСategoriesRepository
                .findByCategoryId(categoryId)
                .map(RecipeCategories::getRecipeId)
                .collect(Collectors.toList());

        return recipesRepository
                .findByRecipeIdIn(recipeIds)
                .map(this.mapper::toRecipeDto)
                .collect(Collectors.toList());
    }



    @Override
    public RecipeDto getRecipe(Integer id) {
        Optional<Recipe> optionalRecipe = this.recipesRepository.findById(id);

        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            RecipeDto recipeDto = this.mapper.toRecipeDto(recipe);

            // Получаем список ингредиентов из RecipeDto
            List<RecipeIngredientsDto> ingredients = recipeDto.getIngredients();

            // Обновляем названия ингредиентов
            ingredients.forEach(ingredient -> {
                Optional<Ingredient> optionalIngredient =
                        this.ingredientsRepository.findById(ingredient.getIngredientId());

                if (optionalIngredient.isPresent()) {
                    ingredient.setIngredientTitle(optionalIngredient.get().getIngredientTitle());
                } else {
                    throw new EntityException(
                            String.format("Ingredient with id %s not found", ingredient.getIngredientId()));
                }
            });

            return recipeDto;
        } else {
            throw new EntityException(String.format("Recipe with code %s not exists", id));
        }
    }



    @Override
    @Transactional
    public RecipeDto updateRecipe(RecipeDto recipeDto) {

       Recipe recipe = this.recipesRepository.findById(recipeDto.getRecipeId()).orElseThrow(() -> new EntityException(String.format("Recipe with code %s not exists", recipeDto.getRecipeId())));
        recipe.setRecipeTitle(recipeDto.getRecipeTitle());
        recipe.setRecipeCountry(recipeDto.getRecipeCountry());
        recipe.setRecipeHoliday(recipeDto.getRecipeHoliday());
        recipe.setProgress(recipeDto.getProgress());
        recipe.setTypeMeal(recipeDto.getTypeMeal());
        recipe.setAuthorId(recipeDto.getAuthorId());
        recipe.setTime(recipeDto.getTime());
        recipe.setImageLink(recipeDto.getImageLink());

        List<RecipeIngredients> updatedIngredients = recipeDto.getIngredients().stream()
                .map(ingredientDto -> {
                    RecipeIngredients recipeIngredients = new RecipeIngredients();
                    recipeIngredients.setRecipeId(recipe.getRecipeId());
                    recipeIngredients.setQuantity(ingredientDto.getQuantity());
                    recipeIngredients.setIngredientId(ingredientDto.getIngredientId());

                    return recipeIngredients;
                })
                .collect(Collectors.toList());

        recipe.setIngredients(updatedIngredients);
        Recipe newRecipe = this.recipesRepository.save(recipe);

        return this.mapper.toRecipeDto(newRecipe);
    }

    @Override
    public void deleteRecipe(Integer id) {

        if (this.recipesRepository.existsById(id)) {
            this.recipesRepository.deleteById(id);
        } else {
            throw new EntityException(String.format("Recipe with recipeId %s not exists", id));
        }
    }

}
