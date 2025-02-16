package ru.foodmaker.recipes.dto;

import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class RecipeIngredientsDto {

    private Integer recipeId;

    private Integer ingredientId;

    private String quantity;
}
