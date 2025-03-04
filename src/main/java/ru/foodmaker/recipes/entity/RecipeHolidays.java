package ru.foodmaker.recipes.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="recipe_holidays")
public class RecipeHolidays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer entityId;

    @Column(name="recipe_id")
    private Integer recipeId;

    @Column(name="holiday_id")
    private Integer holidayId;

    @Column(name="holiday_name")
    private String holidayName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", insertable = false, updatable = false)
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "holiday_id", insertable = false, updatable = false)
    private Holiday holiday;
}
