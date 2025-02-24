package ru.foodmaker.recipes.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="preparation_step")
public class PreparationSteps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="step_id")
    private Integer stepId;

    @Column(name="recipe_id")
    private Integer recipeId;

    @ManyToOne
    @JoinColumn(name = "recipe_id", insertable = false, updatable = false)
    private Recipe recipe;

    @Column(name="step_number")
    private Integer stepNumber;

    @Column(name="step_description")
    private String stepDescription;
}
