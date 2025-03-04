package ru.foodmaker.recipes.dto;


import lombok.Data;

@Data
public class RecipeTypeMealsDto {

    private String typeMealName;

    private Integer recipeId;

    private Integer typeMealId;
}
