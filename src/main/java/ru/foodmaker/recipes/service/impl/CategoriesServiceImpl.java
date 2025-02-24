package ru.foodmaker.recipes.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.foodmaker.recipes.dto.CategoryDto;
import ru.foodmaker.recipes.entity.Category;
import ru.foodmaker.recipes.exception.EntityException;
import ru.foodmaker.recipes.mapper.RecipeMapper;
import ru.foodmaker.recipes.repository.CategoriesRepository;
import ru.foodmaker.recipes.service.CategoriesService;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    private final CategoriesRepository categoriesRepository;
    private final RecipeMapper mapper;

    public CategoriesServiceImpl(CategoriesRepository categoriesRepository,
                                 RecipeMapper mapper) {
        this.categoriesRepository = categoriesRepository;
        this.mapper=mapper;
    }


    @Override
    @Transactional
    public CategoryDto addCategory(CategoryDto categoryDto) {

        CategoryDto newCategoryDto;

        Category category = this.mapper.toCategory(categoryDto);
        Category newCategory = this.categoriesRepository.save(category);
        newCategoryDto = this.mapper.toCategoryDto(newCategory);


        return newCategoryDto;

    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return Optional.of(this.categoriesRepository.findAll())
                .stream()
                .flatMap(Collection::stream)
                .map(this.mapper::toCategoryDto)
                .toList();

    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category category = this.categoriesRepository.findById(categoryDto.getCategoryId()).orElseThrow(() -> new EntityException(String.format("Category with code %s not exists", categoryDto.getCategoryId())));
        category.setCategoryName(categoryDto.getCategoryName());
        category.setImageLink(categoryDto.getImageLink());

        Category newCategory = this.categoriesRepository.save(category);

        return this.mapper.toCategoryDto(newCategory);
    }

    @Override
    public void deleteCategory(Integer id) {

        if (this.categoriesRepository.existsById(id)) {
            this.categoriesRepository.deleteById(id);
        } else {
            throw new EntityException(String.format("Category with categoryId %s not exists", id));
        }
}
}
