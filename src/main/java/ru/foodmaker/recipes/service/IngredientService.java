package ru.foodmaker.recipes.service;

import ru.foodmaker.recipes.dto.IngredientDto;
import ru.foodmaker.recipes.dto.RecipeDto;
import ru.foodmaker.recipes.entity.Ingredient;

import java.util.List;

public interface IngredientService {
    IngredientDto addIngredient(IngredientDto ingredientDto);
    List<IngredientDto> getAllIngredients();
    IngredientDto updateIngredient(IngredientDto ingredientDto);
    void deleteIngredient(Integer id);
}
