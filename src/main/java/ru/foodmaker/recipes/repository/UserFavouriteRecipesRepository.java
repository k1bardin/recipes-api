package ru.foodmaker.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.foodmaker.recipes.entity.UserFavouriteRecipe;


import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface UserFavouriteRecipesRepository extends JpaRepository<UserFavouriteRecipe, Integer> {

    void deleteByRecipeIdAndUserId(Integer recipeId, Integer userId);

    Stream<UserFavouriteRecipe> findByUserId(Integer userId);
    Optional<UserFavouriteRecipe> findByRecipeIdAndUserId(Integer recipeId, Integer userId);
}
