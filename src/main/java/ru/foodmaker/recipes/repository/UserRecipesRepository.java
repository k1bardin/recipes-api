package ru.foodmaker.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.foodmaker.recipes.entity.UserRecipe;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface UserRecipesRepository extends JpaRepository<UserRecipe, Integer> {

    void deleteByRecipeIdAndUserId(Integer recipeId, Integer userId);

    Stream<UserRecipe> findByUserId(Integer userId);
    Optional<UserRecipe> findByRecipeIdAndUserId(Integer recipeId, Integer userId);

    Stream<UserRecipe> findByRecipeId(Integer recipeId);
}
