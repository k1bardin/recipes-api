package ru.foodmaker.recipes.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="type_meals")
public class TypeMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="type_meal_id")
    private Integer typeMealId;

    @OneToMany(mappedBy = "typeMeal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeAttributes> recipeAttributes;

    @Column(name="type_meal_name")
    private String typeMealName;
}
