package ru.foodmaker.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.foodmaker.recipes.entity.Holiday;

@Repository
public interface HolidaysRepository extends JpaRepository<Holiday, Integer> {
}
