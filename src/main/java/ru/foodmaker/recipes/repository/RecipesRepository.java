package ru.foodmaker.recipes.repository;

import org.springframework.stereotype.Repository;
import ru.foodmaker.recipes.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface RecipesRepository extends JpaRepository<Recipe, Integer>  {
    Stream<Recipe> findByRecipeIdIn(List<Integer> recipeIds);

}
