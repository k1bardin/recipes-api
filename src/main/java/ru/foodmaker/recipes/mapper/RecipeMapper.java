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


    Country toCountry (CountryDto countryDto);

    CountryDto toCountryDto (Country country);

    Holiday toHoliday (HolidayDto holidayDto);

    HolidayDto toHolidayDto (Holiday holiday);

    TypeMeal toTypeMeal (TypeMealDto typeMealDto);

    TypeMealDto toTypeMealDto (TypeMeal typeMeal);

    RecipeAttributes toRecipeAttributes (RecipeAttributesDto recipeAttributesDto);

    RecipeAttributesDto toRecipeAttributesDto (RecipeAttributes recipeAttributes);

    User toUser (UserDto userDto);

    UserDto toUserDto (User user);
    UserRecipe toUserRecipe (UserRecipeDto userRecipeDto);

    UserRecipeDto toUserRecipeDto (UserRecipe userRecipe);

    UserFavouriteRecipe toUserFavouriteRecipe (UserRecipeDto userRecipeDto);

    UserRecipeDto toUserRecipeDto (UserFavouriteRecipe userFavouriteRecipe);

}
