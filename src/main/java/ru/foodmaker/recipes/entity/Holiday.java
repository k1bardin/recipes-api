package ru.foodmaker.recipes.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="holidays")
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="holday_id")
    private Integer holidayId;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeHolidays> recipeHolidays;

    @Column(name="holiday_name")
    private String holidayName;
}
