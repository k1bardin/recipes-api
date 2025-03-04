package ru.foodmaker.recipes.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import ru.foodmaker.recipes.entity.RecipeCountries;
import ru.foodmaker.recipes.entity.RecipeHolidays;
import ru.foodmaker.recipes.entity.RecipeTypeMeals;

import java.util.List;

@Data
public class RecipeDto {

    private Integer recipeId;

    private String recipeTitle;

    private String time;

    private String imageLink;

    private String imageLinkPreview;

    private Integer authorId;

    private List<RecipeCategoriesDto> categories;

    private List<RecipeIngredientsDto> ingredients;

    private List<PreparationStepsDto> steps;

    private List<RecipeHolidays> holidays;

    private List<RecipeCountries> countries;

    private List<RecipeTypeMeals> typeMeals;
}
