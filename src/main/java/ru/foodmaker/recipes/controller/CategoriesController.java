package ru.foodmaker.recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.foodmaker.recipes.dto.CategoryDto;
import ru.foodmaker.recipes.service.CategoriesService;

import java.util.List;

@RestController
public class CategoriesController {

    private final CategoriesService categoriesService;

    @Autowired
    public CategoriesController(CategoriesService categoriesService){

        this.categoriesService = categoriesService;
    }

    @GetMapping(path = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDto> getAllCategories() {

        return this.categoriesService.getAllCategories();
    }

    @PostMapping(path = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto addCategory(@RequestBody CategoryDto category) {

        return this.categoriesService.addCategory(category);
    }

    @PutMapping(path = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto) {

        return this.categoriesService.updateCategory(categoryDto);
    }

    @DeleteMapping(path = "/category/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCategory(@PathVariable(name = "categoryId") Integer categoryId) {

        this.categoriesService.deleteCategory(categoryId);
    }
}
