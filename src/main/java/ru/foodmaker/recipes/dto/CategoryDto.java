package ru.foodmaker.recipes.dto;

import lombok.Data;

@Data
public class CategoryDto {

    private Integer categoryId;

    private String categoryName;

    private String imageLink;
}
