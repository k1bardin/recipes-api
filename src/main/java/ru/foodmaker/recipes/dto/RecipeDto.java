package ru.foodmaker.recipes.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeDto {

    private Integer recipeId;

    private String recipeTitle;

    private String recipeCountry;

    private String recipeHoliday;

    private String progress;

    private String typeMeal;

    private String time;

    private String imageLink;

    private Integer authorId;

    private List<RecipeCategoriesDto> categories;

    private List<RecipeIngredientsDto> ingredients;

    private List<PreparationStepsDto> steps;
}
