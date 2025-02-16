package ru.foodmaker.recipes.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.foodmaker.recipes.dto.IngredientDto;
import ru.foodmaker.recipes.entity.Ingredient;
import ru.foodmaker.recipes.exception.EntityException;
import ru.foodmaker.recipes.mapper.RecipeMapper;
import ru.foodmaker.recipes.repository.IngredientsRepository;
import ru.foodmaker.recipes.service.IngredientService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientsRepository ingredientsRepository;
    private final RecipeMapper mapper;

    public IngredientServiceImpl(IngredientsRepository ingredientsRepository,
                                 RecipeMapper mapper) {
        this.ingredientsRepository = ingredientsRepository;
        this.mapper=mapper;
    }


    @Override
    @Transactional
    public IngredientDto addIngredient(IngredientDto ingredientDto) {

        IngredientDto newIngredientDto;

        Ingredient ingredient = this.mapper.toIngredient(ingredientDto);
        Ingredient newIngredient = this.ingredientsRepository.save(ingredient);
        newIngredientDto = this.mapper.toIngredientDto(newIngredient);


        return newIngredientDto;

    }

    @Override
    public List<IngredientDto> getAllIngredients() {
        return Optional.of(this.ingredientsRepository.findAll())
                .stream()
                .flatMap(Collection::stream)
                .map(this.mapper::toIngredientDto)
                .toList();

    }

    @Override
    public IngredientDto updateIngredient(IngredientDto ingredientDto) {
        Ingredient ingredient = this.ingredientsRepository.findById(ingredientDto.getIngredientId()).orElseThrow(() -> new EntityException(String.format("Ingredient with code %s not exists", ingredientDto.getIngredientId())));
        ingredient.setIngredientTitle(ingredientDto.getIngredientTitle());

        Ingredient newIngredient = this.ingredientsRepository.save(ingredient);

        return this.mapper.toIngredientDto(newIngredient);
    }

    @Override
    public void deleteIngredient(Integer id) {

        if (this.ingredientsRepository.existsById(id)) {
            this.ingredientsRepository.deleteById(id);
        } else {
            throw new EntityException(String.format("Recipe with ingredientId %s not exists", id));
        }

    }
}
