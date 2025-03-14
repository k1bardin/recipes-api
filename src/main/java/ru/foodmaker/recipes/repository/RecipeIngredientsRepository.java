package ru.foodmaker.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.foodmaker.recipes.entity.RecipeIngredients;

import java.util.List;
import java.util.stream.Stream;


@Repository
public interface RecipeIngredientsRepository extends JpaRepository<RecipeIngredients, Integer> {
    Stream<RecipeIngredients> findByIngredientIdIn(List<Integer> ingredientIds);

    void deleteAllByRecipeId(Integer recipeId);
}
