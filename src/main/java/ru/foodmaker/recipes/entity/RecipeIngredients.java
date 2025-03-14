package ru.foodmaker.recipes.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name="recipe_ingredients")
public class RecipeIngredients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer entityId;

    @Column(name="recipe_id")
    private Integer recipeId;

    @Column(name="ingredient_id")
    private Integer ingredientId;

    @Column(name="ingredient_title")
    private String ingredientTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", insertable = false, updatable = false)
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", insertable = false, updatable = false)
    private Ingredient ingredient;

    @Column(name="quantity")
    private String quantity;

}


