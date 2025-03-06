package ru.foodmaker.recipes.service;

import ru.foodmaker.recipes.dto.FindRecipesRequest;
import ru.foodmaker.recipes.dto.RecipeDto;


import java.util.List;


public interface RecipesService {
    RecipeDto createRecipe(RecipeDto recipeDto);
    List<RecipeDto> getAllRecipes();
    RecipeDto updateRecipe(RecipeDto recipeDto);

    List<RecipeDto> findRecipesByCategoryId(Integer categoryId);
    List<RecipeDto> findRecipesByFilter(FindRecipesRequest findRecipesRequest);

    RecipeDto getRecipe(Integer id);
    void deleteRecipe(Integer id);
}
