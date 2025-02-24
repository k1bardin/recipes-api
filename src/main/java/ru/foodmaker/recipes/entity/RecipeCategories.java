package ru.foodmaker.recipes.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="recipe_categories")
public class RecipeCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer entityId;

    @Column(name="recipe_id")
    private Integer recipeId;

    @Column(name="category_id")
    private Integer categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", insertable = false, updatable = false)
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;
}
