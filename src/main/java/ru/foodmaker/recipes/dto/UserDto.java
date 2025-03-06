package ru.foodmaker.recipes.dto;


import lombok.Data;
import ru.foodmaker.recipes.entity.UserFavouriteRecipe;
import ru.foodmaker.recipes.entity.UserRecipe;

import java.util.List;

@Data
public class UserDto {

    private Integer userId;

    private List<UserFavouriteRecipe> recipes;

    private List<UserRecipe> favouriteRecipes;
}
