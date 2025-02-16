package ru.foodmaker.recipes.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.foodmaker.recipes.dto.IngredientDto;
import ru.foodmaker.recipes.service.IngredientService;

import java.util.List;

@RestController
public class IngredientsController {
    private final IngredientService ingredientService;

    @Autowired
    public IngredientsController(IngredientService ingredientService){

        this.ingredientService =ingredientService;
    }

    @GetMapping(path = "/ingredients", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IngredientDto> getAllIngredients() {

        return this.ingredientService.getAllIngredients();
    }

    @PostMapping(path = "/ingredient", produces = MediaType.APPLICATION_JSON_VALUE)
    public IngredientDto addIngredient(@RequestBody IngredientDto ingredient) {

        return this.ingredientService.addIngredient(ingredient);
    }

    @PutMapping(path = "/ingredient", produces = MediaType.APPLICATION_JSON_VALUE)
    public IngredientDto updateIngredient(@RequestBody IngredientDto ingredientDto) {

        return this.ingredientService.updateIngredient(ingredientDto);
    }

    @DeleteMapping(path = "/ingredient", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteIngredient(@PathVariable(name = "ingredientId") Integer ingredientId) {

        this.ingredientService.deleteIngredient(ingredientId);
    }
}
