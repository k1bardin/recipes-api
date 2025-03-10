package ru.foodmaker.recipes.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ru.foodmaker.recipes.dto.*;
import ru.foodmaker.recipes.entity.*;
import ru.foodmaker.recipes.repository.*;
import ru.foodmaker.recipes.exception.EntityException;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import ru.foodmaker.recipes.mapper.RecipeMapper;
import ru.foodmaker.recipes.service.RecipesService;

@Service
public class RecipesServiceImpl implements RecipesService {

    private final RecipesRepository recipesRepository;

    private final RecipeAttributesRepository recipeAttributesRepository;

    private final RecipeIngredientsRepository recipeIngredientsRepository;

    private final IngredientsRepository ingredientsRepository;

    private final TypeMealsRepository typeMealsRepository;

    private final CountriesRepository countriesRepository;

    private final HolidaysRepository holidaysRepository;

    private final PreparationStepsRepository preparationStepsRepository;

    private final CategoriesRepository categoriesRepository;

    private final RecipeMapper mapper;


    @Autowired
    public RecipesServiceImpl(RecipesRepository recipesRepository,
                              RecipeAttributesRepository recipeAttributesRepository,
                              IngredientsRepository ingredientsRepository,
                              PreparationStepsRepository preparationStepsRepository,
                              CategoriesRepository categoriesRepository,
                              TypeMealsRepository typeMealsRepository,
                              CountriesRepository countriesRepository,
                              HolidaysRepository holidaysRepository,
                              RecipeIngredientsRepository recipeIngredientsRepository,
                              RecipeMapper mapper
                              ) {

        this.recipeAttributesRepository=recipeAttributesRepository;
        this.ingredientsRepository=ingredientsRepository;
        this.recipesRepository = recipesRepository;
        this.holidaysRepository=holidaysRepository;
        this.countriesRepository=countriesRepository;
        this.typeMealsRepository=typeMealsRepository;
        this.categoriesRepository=categoriesRepository;
        this.recipeIngredientsRepository=recipeIngredientsRepository;
        this.preparationStepsRepository = preparationStepsRepository;
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
    @Transactional
    public List<RecipeDto> findRecipesByFilter(FindRecipesRequest findRecipesRequest) {
        Integer countryId = findRecipesRequest.getCountryId();
        Integer categoryId = findRecipesRequest.getCategoryId();
        Integer holidayId = findRecipesRequest.getHolidayId();
        Integer typeMealId = findRecipesRequest.getTypeMealId();

        if (countryId == null && categoryId == null && holidayId == null && typeMealId == null
                && (findRecipesRequest.getIngredients() == null || findRecipesRequest.getIngredients().isEmpty())) {
            return recipesRepository.findAll().stream()
                    .map(this.mapper::toRecipeDto)
                    .collect(Collectors.toList());
        }

        Specification<RecipeAttributes> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (categoryId != null) {
                predicates.add(cb.equal(root.get("categoryId"), categoryId));
            }
            if (countryId != null) {
                predicates.add(cb.equal(root.get("countryId"), countryId));
            }
            if (holidayId != null) {
                predicates.add(cb.equal(root.get("holidayId"), holidayId));
            }
            if (typeMealId != null) {
                predicates.add(cb.equal(root.get("typeMealId"), typeMealId));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        List<RecipeAttributes> recipeAttributes = recipeAttributesRepository
                .findAll(spec);

        List<Integer> recipeIds = recipeAttributes.stream()
                .map(RecipeAttributes::getRecipeId)
                .collect(Collectors.toList());
        if (findRecipesRequest.getIngredients() != null && !findRecipesRequest.getIngredients().isEmpty()){

            List<Integer> ingredientIds = Optional.ofNullable(findRecipesRequest.getIngredients())
                    .stream()
                    .flatMap(List::stream) // Добавляем flatMap для работы с элементами списка
                    .map(ingredient -> ingredient.getIngredientId())
                    .filter(Objects::nonNull)
                    .distinct()
                    .collect(Collectors.toList());


            Stream<RecipeIngredients> recipeIngredients = recipeIngredientsRepository
                    .findByIngredientIdIn(ingredientIds);

            recipeIngredients = recipeIngredients
                    .filter(ri -> ri.getRecipeId() != null && ri.getIngredientId() != null);

            Map<Integer, Set<Integer>> recipeToIngredients = recipeIngredients
                    .filter(ri -> recipeIds.contains(ri.getRecipeId()) && ri.getIngredientId() != null)
                    .collect(Collectors.groupingBy(
                            RecipeIngredients::getRecipeId,
                            Collectors.mapping(RecipeIngredients::getIngredientId, Collectors.toSet())
                    ));

            Set<Integer> requiredIngredientIds = new HashSet<>(ingredientIds);

            List<Integer> filteredRecipeIds = recipeToIngredients.entrySet().stream()
                    .filter(entry -> requiredIngredientIds.stream()
                            .allMatch(requiredId -> entry.getValue().contains(requiredId)))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            return recipesRepository
                    .findByRecipeIdIn(filteredRecipeIds)
                    .map(this.mapper::toRecipeDto)
                    .collect(Collectors.toList());
        }else {

            return recipesRepository
                    .findByRecipeIdIn(recipeIds)
                    .map(this.mapper::toRecipeDto)
                    .collect(Collectors.toList());
        }
    }


    @Override
    public RecipeDto getRecipe(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }

        Optional<Recipe> optionalRecipe = this.recipesRepository.findById(id);

        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            RecipeDto recipeDto = this.mapper.toRecipeDto(recipe);

            // Получаем список ингредиентов из RecipeDto и проверяем на null
            List<RecipeIngredientsDto> ingredients = recipeDto.getIngredients();
            if (ingredients == null) {
                ingredients = new ArrayList<>();
            }

            // Обновляем названия ингредиентов
            ingredients.forEach(ingredient -> {
                if (ingredient.getIngredientId() != null) {
                    Optional<Ingredient> optionalIngredient =
                            this.ingredientsRepository.findById(ingredient.getIngredientId());

                    if (optionalIngredient.isPresent()) {
                        ingredient.setIngredientTitle(optionalIngredient.get().getIngredientTitle());
                    }
                }
                // Если ingredientId null, заменяем объект на null
                else {
                    ingredient = null;
                }
            });

            // Аналогично проверяем attributes
            List<RecipeAttributesDto> attributes = recipeDto.getAttributes();
            if (attributes == null) {
                attributes = new ArrayList<>();
            }

            attributes.forEach(attribute -> {
                if (attribute.getCategoryId() != null) {
                    Optional<Category> optionalCategory =
                            this.categoriesRepository.findById(attribute.getCategoryId());
                    attribute.setCategoryName(optionalCategory.get().getCategoryName());
                }
            });

            attributes.forEach(attribute -> {
                if (attribute.getTypeMealId() != null) {
                    Optional<TypeMeal> optionalTypeMeal =
                            this.typeMealsRepository.findById(attribute.getTypeMealId());
                    attribute.setTypeMealName(optionalTypeMeal.get().getTypeMealName());
                }
            });

            attributes.forEach(attribute -> {
                if (attribute.getCountryId() != null) {
                    Optional<Country> optionalCountry =
                            this.countriesRepository.findById(attribute.getCountryId());
                    attribute.setCountryName(optionalCountry.get().getCountryName());
                }
            });

            attributes.forEach(attribute -> {
                if (attribute.getHolidayId() != null) {
                    Optional<Holiday> optionalHoliday =
                            this.holidaysRepository.findById(attribute.getHolidayId());
                    attribute.setHolidayName(optionalHoliday.get().getHolidayName());
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

        Recipe existingRecipe = recipesRepository.findById(recipeDto.getRecipeId())
                .orElseThrow(() -> new EntityNotFoundException("Recipe not found"));

        // Обновляем только те поля, которые переданы в запросе
        if (recipeDto.getRecipeTitle() != null) {
            existingRecipe.setRecipeTitle(recipeDto.getRecipeTitle());
        }
        if (recipeDto.getTime() != null) {
            existingRecipe.setTime(recipeDto.getTime());
        }
        if (recipeDto.getImageLink() != null) {
            existingRecipe.setImageLink(recipeDto.getImageLink());
        }
        if (recipeDto.getImageLinkPreview() != null) {
            existingRecipe.setImageLinkPreview(recipeDto.getImageLinkPreview());
        }

        // Обновляем связанные сущности только если они переданы в запросе
        if (recipeDto.getIngredients() != null) {
            // Удаляем старые ингредиенты и создаем новые
            recipeIngredientsRepository.deleteAllByRecipeId(recipeDto.getRecipeId());
            List<RecipeIngredients> ingredientList = new ArrayList<>();
            for (RecipeIngredientsDto recipeIngredientsDto : recipeDto.getIngredients()) {
                RecipeIngredients ingredient = new RecipeIngredients();
                ingredient.setRecipeId(recipeDto.getRecipeId());
                ingredient.setQuantity(recipeIngredientsDto.getQuantity());
                ingredient.setIngredientId(recipeIngredientsDto.getIngredientId());
                ingredientList.add(ingredient);
            }
            existingRecipe.setIngredients(ingredientList);
        }

        if (recipeDto.getSteps() != null) {
            // Удаляем старые шаги приготовления и создаем новые
            preparationStepsRepository.deleteAllByRecipeId(recipeDto.getRecipeId());
            List<PreparationSteps> preparationStepsList = new ArrayList<>();
            for (PreparationStepsDto preparationStepsDto : recipeDto.getSteps()) {
                PreparationSteps preparationSteps = new PreparationSteps();
                preparationSteps.setRecipeId(recipeDto.getRecipeId());
                preparationSteps.setStepNumber(preparationStepsDto.getStepNumber());
                preparationSteps.setStepDescription(preparationStepsDto.getStepDescription());
                preparationStepsList.add(preparationSteps);
            }
            existingRecipe.setSteps(preparationStepsList);
        }

        if (recipeDto.getAttributes() != null) {
            // Удаляем старые атрибуты и создаем новые
            recipeAttributesRepository.deleteAllByRecipeId(recipeDto.getRecipeId());
            List<RecipeAttributes> recipeAttributesList = new ArrayList<>();
            for (RecipeAttributesDto recipeAttributesDto : recipeDto.getAttributes()) {
                RecipeAttributes recipeAttributes = new RecipeAttributes();
                recipeAttributes.setRecipeId(recipeDto.getRecipeId());
                recipeAttributes.setCategoryId(recipeAttributesDto.getCategoryId());
                recipeAttributes.setTypeMealId(recipeAttributesDto.getTypeMealId());
                recipeAttributes.setHolidayId(recipeAttributesDto.getHolidayId());
                recipeAttributes.setCountryId(recipeAttributesDto.getCountryId());
                recipeAttributesList.add(recipeAttributes);
            }
            existingRecipe.setAttributes(recipeAttributesList);
        }

        // Сохраняем обновленный рецепт
        Recipe savedRecipe = recipesRepository.save(existingRecipe);
        RecipeDto updatedRecipeDto = this.mapper.toRecipeDto(savedRecipe);

        return updatedRecipeDto;
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
