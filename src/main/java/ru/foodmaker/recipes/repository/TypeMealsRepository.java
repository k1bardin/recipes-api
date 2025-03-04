package ru.foodmaker.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.foodmaker.recipes.entity.TypeMeal;

@Repository
public interface TypeMealsRepository extends JpaRepository<TypeMeal, Integer> {
}
