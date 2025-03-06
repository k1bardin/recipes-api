package ru.foodmaker.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.foodmaker.recipes.entity.PreparationSteps;

@Repository
public interface PreparationStepsRepository extends JpaRepository<PreparationSteps, Integer> {

    void deleteAllByRecipeId(Integer recipeId);
}
