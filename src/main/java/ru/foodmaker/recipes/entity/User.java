package ru.foodmaker.recipes.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer userId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserFavouriteRecipe> recipes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserRecipe> favouriteRecipes;
}
