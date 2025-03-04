package ru.foodmaker.recipes.entity;
import lombok.Data;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
@Entity
@Data
@Table(name="recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recipe_id")
    private Integer recipeId;

    @Column(name="recipe_title")
    private String recipeTitle;

    @Column(name="recipe_country")
    private String recipeCountry;

    @Column(name="recipe_holiday")
    private String recipeHoliday;

    @Column(name="progress")
    private String progress;

    @Column(name="type_meal")
    private String typeMeal;

    @Column(name="time")
    private String time;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<RecipeIngredients> ingredients;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<RecipeCategories> categories;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<RecipeHolidays> holidays;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<RecipeCountries> countries;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<RecipeTypeMeals> typeMeals;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<PreparationSteps> steps;

    @Column(name="image_link")
    private String imageLink;

    @Column(name="image_link_preview")
    private String imageLinkPreview;

    @Column(name="author_id")
    private Integer authorId;

}
