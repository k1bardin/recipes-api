package ru.foodmaker.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.foodmaker.recipes.entity.Category;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Integer> {
}
