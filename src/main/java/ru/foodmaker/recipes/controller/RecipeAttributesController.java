package ru.foodmaker.recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import ru.foodmaker.recipes.dto.CategoryDto;
import ru.foodmaker.recipes.dto.CountryDto;
import ru.foodmaker.recipes.dto.HolidayDto;
import ru.foodmaker.recipes.dto.TypeMealDto;
import ru.foodmaker.recipes.service.RecipeAttributesService;

import java.util.List;

@RestController
public class RecipeAttributesController {

    private final RecipeAttributesService recipeAttributesService;

    @Autowired
    public RecipeAttributesController(RecipeAttributesService recipeAttributesService){

        this.recipeAttributesService = recipeAttributesService;
    }
    @GetMapping(path = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDto> getAllCategories() {

        return this.recipeAttributesService.getAllCategories();
    }

    @PostMapping(path = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto addCategory(@RequestBody CategoryDto category) {

        return this.recipeAttributesService.addCategory(category);
    }

    @PutMapping(path = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto) {

        return this.recipeAttributesService.updateCategory(categoryDto);
    }

    @DeleteMapping(path = "/category/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCategory(@PathVariable(name = "categoryId") Integer categoryId) {

        this.recipeAttributesService.deleteCategory(categoryId);
    }

    @GetMapping(path = "/countries", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CountryDto> getAllCountries() {

        return this.recipeAttributesService.getAllCountries();
    }

    @GetMapping(path = "/typeMeals", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TypeMealDto> getAllTypeMeals() {

        return this.recipeAttributesService.getAllTypeMeals();
    }

    @GetMapping(path = "/holidays", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HolidayDto> getAllHolidays() {

        return this.recipeAttributesService.getAllHolidays();
    }
}
