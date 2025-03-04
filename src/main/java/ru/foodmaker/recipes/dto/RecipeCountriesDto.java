package ru.foodmaker.recipes.dto;

import lombok.Data;

@Data
public class RecipeCountriesDto {

    private String countryName;

    private Integer recipeId;

    private Integer countryId;
}
