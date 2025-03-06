package ru.foodmaker.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.foodmaker.recipes.entity.RecipeAttributes;


import java.util.stream.Stream;

@Repository
public interface RecipeAttributesRepository extends JpaRepository<RecipeAttributes, Integer>,
        JpaSpecificationExecutor<RecipeAttributes> {
    Stream<RecipeAttributes> findByCategoryId(Integer categoryId);

    void deleteAllByRecipeId(Integer recipeId);

}

