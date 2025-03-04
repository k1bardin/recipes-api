package ru.foodmaker.recipes.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeDto {

    private Integer recipeId;

    private String recipeTitle;

    private String time;

    private String imageLink;

    private String imageLinkPreview;

    private List<RecipeIngredientsDto> ingredients;

    private List<PreparationStepsDto> steps;

    private List<RecipeAttributesDto> attributes;
}
