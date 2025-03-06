package ru.foodmaker.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.foodmaker.recipes.dto.FindRecipesRequest;
import ru.foodmaker.recipes.entity.RecipeAttributes;


import java.util.stream.Stream;

@Repository
public interface RecipeAttributesRepository extends JpaRepository<RecipeAttributes, Integer>,
        JpaSpecificationExecutor<RecipeAttributes> {
    Stream<RecipeAttributes> findByCategoryId(Integer categoryId);

  /*  Stream<RecipeAttributes> findByCategoryIdOrCountryIdOrHolidayIdOrTypeMealId(
            @Param("categoryId") Integer categoryId,
            @Param("countryId") Integer countryId,
            @Param("holidayId") Integer holidayId,
            @Param("typeMealId") Integer typeMealId);*/
}

