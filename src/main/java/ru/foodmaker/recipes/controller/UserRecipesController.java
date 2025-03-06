package ru.foodmaker.recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.foodmaker.recipes.dto.RecipeDto;
import ru.foodmaker.recipes.dto.UserRecipeDto;
import ru.foodmaker.recipes.service.UserRecipesService;

import java.util.List;

public class UserRecipesController {

    private final UserRecipesService userRecipesService;

    @Autowired
    public UserRecipesController(UserRecipesService userRecipesService){

        this.userRecipesService = userRecipesService;
    }

    @GetMapping("/recipes/{userId}")
    public List<RecipeDto> getRecipesByUser(@PathVariable Integer userId) {
        return userRecipesService.findUserRecipes(userId);
    }

    @GetMapping("/favouriteRecipes/{userId}")
    public List<RecipeDto> getFavouriteRecipesByUser(@PathVariable Integer userId) {
        return userRecipesService.findUserFavouriteRecipes(userId);
    }

    @PostMapping(path = "/user/recipe", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRecipeDto createUserRecipe(@RequestBody UserRecipeDto userRecipe) {

        return this.userRecipesService.saveUserRecipe(userRecipe);
    }

    @PostMapping(path = "/user/favouriteRecipe", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRecipeDto createUserFavouriteRecipe(@RequestBody UserRecipeDto userRecipe) {

        return this.userRecipesService.saveUserFavouriteRecipe(userRecipe);
    }

    @DeleteMapping(path = "/user/recipe", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUserRecipe(@RequestBody UserRecipeDto userRecipe) {

        this.userRecipesService.deleteUserRecipe(userRecipe);
    }

    @DeleteMapping(path = "/user/favouriteRecipe", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUserFavouriteRecipe(@RequestBody UserRecipeDto userRecipe) {

        this.userRecipesService.deleteUserFavouriteRecipe(userRecipe);
    }
}
