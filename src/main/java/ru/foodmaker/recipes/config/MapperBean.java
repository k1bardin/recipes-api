package ru.foodmaker.recipes.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.foodmaker.recipes.mapper.RecipeMapper;

@Configuration
public class MapperBean {
    @Bean
    public RecipeMapper recipeMapperMapper() {
        return Mappers.getMapper(RecipeMapper.class);
    }
}
