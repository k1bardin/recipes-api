package ru.foodmaker.recipes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.foodmaker.recipes.dto.*;
import ru.foodmaker.recipes.entity.*;
import ru.foodmaker.recipes.repository.*;
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

    private final RecipeAttributesRepository recipeAttributesRepository;

    private final IngredientsRepository ingredientsRepository;

    private final TypeMealsRepository typeMealsRepository;

    private final CountriesRepository countriesRepository;

    private final HolidaysRepository holidaysRepository;
    private final CategoriesRepository categoriesRepository;

    private final RecipeMapper mapper;


    @Autowired
    public RecipesServiceImpl(RecipesRepository recipesRepository,
                              RecipeAttributesRepository recipeAttributesRepository,
                              IngredientsRepository ingredientsRepository,
                              CategoriesRepository categoriesRepository,
                              TypeMealsRepository typeMealsRepository,
                              CountriesRepository countriesRepository,
                              HolidaysRepository holidaysRepository,
                              RecipeMapper mapper
                              ) {

        this.recipeAttributesRepository=recipeAttributesRepository;
        this.ingredientsRepository=ingredientsRepository;
        this.recipesRepository = recipesRepository;
        this.holidaysRepository=holidaysRepository;
        this.countriesRepository=countriesRepository;
        this.typeMealsRepository=typeMealsRepository;
        this.categoriesRepository=categoriesRepository;
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

        List<RecipeAttributes> recipeAttributesList = new ArrayList<>();
        for (RecipeAttributesDto recipeAttributesDto : recipeDto.getAttributes()) {
            RecipeAttributes recipeAttributes = new RecipeAttributes();
            recipeAttributes.setRecipeId(newRecipe.getRecipeId());
            recipeAttributes.setCategoryId(recipeAttributesDto.getCategoryId());
            recipeAttributes.setTypeMealId(recipeAttributesDto.getTypeMealId());
            recipeAttributes.setHolidayId(recipeAttributesDto.getHolidayId());
            recipeAttributes.setCountryId(recipeAttributesDto.getCountryId());
            recipeAttributesList.add(recipeAttributes);
        }
        newRecipe.setAttributes(recipeAttributesList);

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
                List<Integer> recipeIds = recipeAttributesRepository
                .findByCategoryId(categoryId)
                .map(RecipeAttributes::getRecipeId)
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

            List<RecipeAttributesDto> attributes = recipeDto.getAttributes();

            attributes.forEach(attribute -> {
                Optional<Category> optionalCategory =
                        this.categoriesRepository.findById(attribute.getCategoryId());
                attribute.setCategoryName(optionalCategory.get().getCategoryName());
            });

            attributes.forEach(attribute -> {
                Optional<TypeMeal> optionalTypeMeal =
                        this.typeMealsRepository.findById(attribute.getTypeMealId());
                attribute.setTypeMealName(optionalTypeMeal.get().getTypeMealName());
            });

            attributes.forEach(attribute -> {
                Optional<Country> optionalCountry =
                        this.countriesRepository.findById(attribute.getCountryId());
                attribute.setCountryName(optionalCountry.get().getCountryName());
            });

            attributes.forEach(attribute -> {
                Optional<Holiday> optionalHoliday =
                        this.holidaysRepository.findById(attribute.getHolidayId());
                attribute.setHolidayName(optionalHoliday.get().getHolidayName());
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
