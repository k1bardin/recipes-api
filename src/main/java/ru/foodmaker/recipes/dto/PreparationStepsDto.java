package ru.foodmaker.recipes.dto;

import lombok.Data;

@Data
public class PreparationStepsDto {

    private Integer recipeId;

    private Integer stepNumber;

    private String stepDescription;
}
