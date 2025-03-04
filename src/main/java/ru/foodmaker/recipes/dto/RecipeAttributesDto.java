package ru.foodmaker.recipes.dto;

import lombok.Data;

@Data
public class RecipeAttributesDto {

    private Integer recipeId;

    private Integer countryId;

    private String countryName;

    private Integer categoryId;

    private String categoryName;

    private Integer holidayId;

    private String holidayName;

    private Integer typeMealId;

    private String typeMealName;

}
