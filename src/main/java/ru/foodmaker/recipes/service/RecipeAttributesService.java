package ru.foodmaker.recipes.service;

import ru.foodmaker.recipes.dto.CategoryDto;
import ru.foodmaker.recipes.dto.CountryDto;
import ru.foodmaker.recipes.dto.HolidayDto;
import ru.foodmaker.recipes.dto.TypeMealDto;

import java.util.List;

public interface RecipeAttributesService {
    CategoryDto addCategory(CategoryDto category);

    List<CategoryDto> getAllCategories();

    void deleteCategory(Integer categoryId);

    CategoryDto updateCategory(CategoryDto categoryDto);

    List<TypeMealDto> getAllTypeMeals();

    List<CountryDto> getAllCountries();

    List<HolidayDto> getAllHolidays();
}
