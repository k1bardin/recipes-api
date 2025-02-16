package ru.foodmaker.recipes.mapper;
import org.mapstruct.Mapper;
import ru.foodmaker.recipes.dto.RecipeDto;
import ru.foodmaker.recipes.dto.IngredientDto;
import ru.foodmaker.recipes.dto.RecipeIngredientsDto;
import ru.foodmaker.recipes.entity.Recipe;
import ru.foodmaker.recipes.entity.Ingredient;
import ru.foodmaker.recipes.entity.RecipeIngredients;

@Mapper
public interface RecipeMapper {

    Recipe toRecipe (RecipeDto recipeDto);

    RecipeDto toRecipeDto (Recipe recipe);

    Ingredient toIngredient (IngredientDto ingredientDto);

    IngredientDto toIngredientDto (Ingredient ingredient);

    RecipeIngredients toRecipeIngredients (RecipeIngredientsDto recipeIngredientsDto);

    RecipeIngredientsDto toRecipeIngredientsDto (RecipeIngredients recipeIngredients);


}
