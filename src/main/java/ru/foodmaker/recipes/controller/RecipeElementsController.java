package ru.foodmaker.recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.foodmaker.recipes.dto.IngredientDto;
import ru.foodmaker.recipes.service.IngredientService;
import ru.foodmaker.recipes.dto.CategoryDto;
import ru.foodmaker.recipes.service.CategoriesService;

import java.util.List;

@RestController
public class RecipeElementsController {

    private final IngredientService ingredientService;

    @Autowired
    public RecipeElementsController(IngredientService ingredientService){

        this.ingredientService =ingredientService;
    }
}
