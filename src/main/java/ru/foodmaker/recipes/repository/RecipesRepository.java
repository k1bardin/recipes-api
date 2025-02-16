package ru.foodmaker.recipes.repository;

import org.springframework.stereotype.Repository;
import ru.foodmaker.recipes.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RecipesRepository extends JpaRepository<Recipe, Integer>  {
}
