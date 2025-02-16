package ru.foodmaker.recipes.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.foodmaker.recipes.dto.RecipeDto;
import ru.foodmaker.recipes.service.RecipesService;

import java.util.List;

@RestController
public class RecipesController {
    private final RecipesService recipesService;

    @Autowired
    public RecipesController(RecipesService recipesService){

        this.recipesService = recipesService;
    }

    @GetMapping(path = "/recipes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RecipeDto> getAllRecipes() {

        return this.recipesService.getAllRecipes();
    }

    @GetMapping(path = "/recipe/{recipeId}")
    public RecipeDto getRecipe(@PathVariable(name = "recipeId") Integer recipeId) {

        return this.recipesService.getRecipe(recipeId);
    }

    @PostMapping(path = "/recipe", produces = MediaType.APPLICATION_JSON_VALUE)
    public RecipeDto createRecipe(@RequestBody RecipeDto recipe) {

        return this.recipesService.createRecipe(recipe);
    }

    @DeleteMapping(path = "/recipe", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteRecipe(@PathVariable(name = "recipeId") Integer recipeId) {

        this.recipesService.deleteRecipe(recipeId);
    }

    @PutMapping(path = "/recipe", produces = MediaType.APPLICATION_JSON_VALUE)
    public RecipeDto updateRecipe(@RequestBody RecipeDto recipeDto) {

        return this.recipesService.updateRecipe(recipeDto);
    }
}
