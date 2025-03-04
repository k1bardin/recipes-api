package ru.foodmaker.recipes.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="recipe_countries")
public class RecipeCountries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer entityId;

    @Column(name="recipe_id")
    private Integer recipeId;

    @Column(name="country_id")
    private Integer countryId;

    @Column(name="country_name")
    private String countryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", insertable = false, updatable = false)
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    private Country country;
}
