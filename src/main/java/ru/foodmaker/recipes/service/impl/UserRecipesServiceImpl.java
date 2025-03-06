package ru.foodmaker.recipes.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.foodmaker.recipes.dto.IngredientDto;
import ru.foodmaker.recipes.dto.RecipeDto;
import ru.foodmaker.recipes.dto.UserRecipeDto;
import ru.foodmaker.recipes.entity.Ingredient;
import ru.foodmaker.recipes.entity.RecipeAttributes;
import ru.foodmaker.recipes.entity.UserFavouriteRecipe;
import ru.foodmaker.recipes.entity.UserRecipe;
import ru.foodmaker.recipes.mapper.RecipeMapper;
import ru.foodmaker.recipes.repository.RecipesRepository;
import ru.foodmaker.recipes.repository.UserFavouriteRecipesRepository;
import ru.foodmaker.recipes.repository.UserRecipesRepository;
import ru.foodmaker.recipes.service.UserRecipesService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRecipesServiceImpl implements UserRecipesService {

    private final UserRecipesRepository userRecipesRepository;

    private final RecipesRepository recipesRepository;
    private final UserFavouriteRecipesRepository userFavouriteRecipesRepository;
    private final RecipeMapper mapper;

    public UserRecipesServiceImpl(UserRecipesRepository userRecipesRepository,
                                 UserFavouriteRecipesRepository userFavouriteRecipesRepository,
                                  RecipesRepository recipesRepository,
                                 RecipeMapper mapper) {
        this.userRecipesRepository = userRecipesRepository;
        this.recipesRepository=recipesRepository;
        this.userFavouriteRecipesRepository=userFavouriteRecipesRepository;
        this.mapper=mapper;
    }

    @Override
    @Transactional
    public List<RecipeDto> findUserRecipes(Integer userId) {
        List<Integer> recipeIds = userRecipesRepository
                .findByUserId(userId)
                .map(UserRecipe::getRecipeId)
                .collect(Collectors.toList());

        return recipesRepository
                .findByRecipeIdIn(recipeIds)
                .map(this.mapper::toRecipeDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<RecipeDto> findUserFavouriteRecipes(Integer userId) {
        List<Integer> recipeIds = userFavouriteRecipesRepository
                .findByUserId(userId)
                .map(UserFavouriteRecipe::getRecipeId)
                .collect(Collectors.toList());

        return recipesRepository
                .findByRecipeIdIn(recipeIds)
                .map(this.mapper::toRecipeDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserRecipeDto saveUserRecipe(UserRecipeDto userRecipeDto){
        UserRecipeDto newUserRecipeDto;

        UserRecipe userRecipe = this.mapper.toUserRecipe(userRecipeDto);
        UserRecipe newUserRecipe = this.userRecipesRepository.save(userRecipe);
        newUserRecipeDto = this.mapper.toUserRecipeDto(newUserRecipe);
        return newUserRecipeDto;
    }

    @Override
    @Transactional
    public UserRecipeDto saveUserFavouriteRecipe(UserRecipeDto userRecipeDto){
        UserRecipeDto newUserRecipeDto;

        UserFavouriteRecipe userFavouriteRecipe = this.mapper.toUserFavouriteRecipe(userRecipeDto);
        UserFavouriteRecipe newUserFavouriteRecipe = this.userFavouriteRecipesRepository.save(userFavouriteRecipe);
        newUserRecipeDto = this.mapper.toUserRecipeDto(newUserFavouriteRecipe);
        return newUserRecipeDto;
    }

    @Override
    @Transactional
    public void deleteUserRecipe(UserRecipeDto userRecipeDto) {
        // Проверяем существование записи перед удалением
        if (userRecipesRepository.findByRecipeIdAndUserId(
                userRecipeDto.getRecipeId(),
                userRecipeDto.getUserId()).isPresent()) {
            userRecipesRepository.deleteByRecipeIdAndUserId(
                    userRecipeDto.getRecipeId(),
                    userRecipeDto.getUserId());
        } else {
            throw new EntityNotFoundException(
                    "Связь между пользователем и рецептом не найдена");
        }
    }

    @Override
    @Transactional
    public void deleteUserFavouriteRecipe(UserRecipeDto userRecipeDto) {
        // Проверяем существование записи перед удалением
        if (userFavouriteRecipesRepository.findByRecipeIdAndUserId(
                userRecipeDto.getRecipeId(),
                userRecipeDto.getUserId()).isPresent()) {
            userFavouriteRecipesRepository.deleteByRecipeIdAndUserId(
                    userRecipeDto.getRecipeId(),
                    userRecipeDto.getUserId());
        } else {
            throw new EntityNotFoundException(
                    "Связь между пользователем и рецептом не найдена");
        }
    }
}
