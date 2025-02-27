package ru.foodmaker.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.foodmaker.recipes.entity.RecipeCategories;

import java.util.stream.Stream;

@Repository
public interface RecipeCategoriesRepository extends JpaRepository<RecipeCategories, Integer> {
    Stream<RecipeCategories> findByCategoryId(Integer categoryId);
}
