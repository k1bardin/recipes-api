package ru.foodmaker.recipes.service;

import ru.foodmaker.recipes.dto.CategoryDto;

import java.util.List;

public interface CategoriesService {
    CategoryDto addCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategories();

    CategoryDto updateCategory(CategoryDto categoryDto);

    void deleteCategory(Integer id);
}
