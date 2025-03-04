package ru.foodmaker.recipes.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="recipe_attributes")
public class RecipeAttributes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer entityId;

    @Column(name="recipe_id")
    private Integer recipeId;

    @Column(name="category_id")
    private Integer categoryId;

    @Column(name="category_name")
    private Integer categoryName;

    @Column(name="country_id")
    private Integer countryId;

    @Column(name="country_name")
    private String countryName;

    @Column(name="holiday_id")
    private Integer holidayId;

    @Column(name="holiday_name")
    private String holidayName;

    @Column(name="type_meal_id")
    private Integer typeMealId;

    @Column(name="type_meal_name")
    private String typeMealName;

    @ManyToOne
    @JoinColumn(name = "type_meal_id", insertable = false, updatable = false)
    private TypeMeal typeMeal;

    @ManyToOne
    @JoinColumn(name = "holiday_id", insertable = false, updatable = false)
    private Holiday holiday;

    @ManyToOne
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", insertable = false, updatable = false)
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;
}
