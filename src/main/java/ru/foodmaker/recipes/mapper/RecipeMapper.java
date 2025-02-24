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


}
