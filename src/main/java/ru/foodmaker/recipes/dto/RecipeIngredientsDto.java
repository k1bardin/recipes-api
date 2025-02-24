package ru.foodmaker.recipes.dto;

import lombok.Data;

@Data
public class RecipeIngredientsDto {

    private String quantity;

    private String ingredientTitle;

    private Integer recipeId;

    private Integer ingredientId;
}
