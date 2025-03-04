package ru.foodmaker.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.foodmaker.recipes.entity.Country;

@Repository
public interface CountriesRepository extends JpaRepository<Country, Integer> {
}
