package ru.foodmaker.recipes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="recipe_attributes")
public class RecipeAttributes {
}
