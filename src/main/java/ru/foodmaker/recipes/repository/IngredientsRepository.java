package ru.foodmaker.recipes.repository;

import org.springframework.stereotype.Repository;
import ru.foodmaker.recipes.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredient, Integer>  {
}