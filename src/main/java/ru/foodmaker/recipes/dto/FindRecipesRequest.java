package ru.foodmaker.recipes.dto;

import lombok.Data;

import java.util.List;

@Data
public class FindRecipesRequest {

    private Integer countryId;

    private Integer categoryId;

    private Integer holidayId;

    private Integer typeMealId;

    private List<IngredientDto> ingredients;

}
