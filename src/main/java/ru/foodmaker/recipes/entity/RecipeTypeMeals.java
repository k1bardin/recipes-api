package ru.foodmaker.recipes.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="recipe_type_meals")
public class RecipeTypeMeals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer entityId;

    @Column(name="recipe_id")
    private Integer recipeId;

    @Column(name="type_meal_id")
    private Integer typeMealId;

    @Column(name="type_meal_name")
    private String typeMealName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", insertable = false, updatable = false)
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "type_meal_id", insertable = false, updatable = false)
    private TypeMeal typeMeal;
}
