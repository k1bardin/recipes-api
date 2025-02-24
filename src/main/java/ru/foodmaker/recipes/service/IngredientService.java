package ru.foodmaker.recipes.service;

import ru.foodmaker.recipes.dto.IngredientDto;

import java.util.List;

public interface IngredientService {
    IngredientDto addIngredient(IngredientDto ingredientDto);
    List<IngredientDto> getAllIngredients();
    IngredientDto updateIngredient(IngredientDto ingredientDto);
    void deleteIngredient(Integer id);
}
