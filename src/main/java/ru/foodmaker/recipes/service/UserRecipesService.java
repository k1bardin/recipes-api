package ru.foodmaker.recipes.service;

import ru.foodmaker.recipes.dto.RecipeDto;
import ru.foodmaker.recipes.dto.UserRecipeDto;

import java.util.List;

public interface UserRecipesService {

    List<RecipeDto> findUserRecipes(Integer userId);

    List<RecipeDto> findUserFavouriteRecipes(Integer userId);
    UserRecipeDto saveUserRecipe(UserRecipeDto userRecipeDto);

    UserRecipeDto saveUserFavouriteRecipe(UserRecipeDto userRecipeDto);

    void deleteUserRecipe(UserRecipeDto userRecipeDto);

    void deleteUserFavouriteRecipe(UserRecipeDto userRecipeDto);
}
