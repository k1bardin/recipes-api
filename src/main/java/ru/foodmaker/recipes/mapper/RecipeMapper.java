package ru.foodmaker.recipes.mapper;
import org.mapstruct.Mapper;
import ru.foodmaker.recipes.dto.*;
import ru.foodmaker.recipes.entity.*;

@Mapper
public interface RecipeMapper {

    Recipe toRecipe (RecipeDto recipeDto);

    RecipeDto toRecipeDto (Recipe recipe);

    Ingredient toIngredient (IngredientDto ingredientDto);

    IngredientDto toIngredientDto (Ingredient ingredient);

    RecipeIngredients toRecipeIngredients (RecipeIngredientsDto recipeIngredientsDto);

    RecipeIngredientsDto toRecipeIngredientsDto (RecipeIngredients recipeIngredients);

    PreparationSteps toPreparationSteps (PreparationStepsDto preparationStepsDto);

    PreparationStepsDto toPreparationStepsDto (PreparationSteps preparationSteps);

    Category toCategory (CategoryDto categoryDto);

    CategoryDto toCategoryDto (Category category);

    RecipeCategories toRecipeCategories (RecipeCategoriesDto recipeCategoriesDto);

    RecipeCategoriesDto toRecipeCategoriesDto (RecipeCategories recipeCategories);

    Country toCountry (CountryDto countryDto);

    CountryDto toCountryDto (Country country);

    Holiday toHoliday (HolidayDto holidayDto);

    HolidayDto toHolidayDto (Holiday holiday);

    TypeMeal toTypeMeal (TypeMealDto typeMealDto);

    TypeMealDto toTypeMealDto (TypeMeal typeMeal);

    RecipeTypeMeals toRecipeTypeMeals (RecipeTypeMealsDto recipeTypeMealsDto);

    RecipeTypeMealsDto toRecipeTypeMealsDto (RecipeTypeMeals recipeTypeMeals);

    RecipeHolidays toRecipeHolidays (RecipeHolidaysDto recipeHolidaysDto);

    RecipeHolidaysDto toRecipeHolidaysDto (RecipeHolidays recipeHolidays);

    RecipeCountries toRecipeCountries  (RecipeCountriesDto recipeCountriesDto);

    RecipeCountriesDto toRecipeCountriesDto (RecipeCountries recipeCountries);


}
