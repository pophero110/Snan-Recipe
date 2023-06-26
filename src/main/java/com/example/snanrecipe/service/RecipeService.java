package com.example.snanrecipe.service;

import com.example.snanrecipe.model.Recipe;
import com.example.snanrecipe.model.RecipeDto;
import com.example.snanrecipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public RecipeDto createRecipe(RecipeDto recipeDto) {
        Recipe recipe = Recipe.builder()
                .name(recipeDto.getName())
                .ingredients(recipeDto.getIngredients())
                .directions(recipeDto.getDirections())
                .build();

        Recipe savedRecipe = recipeRepository.save(recipe);

        return RecipeDto.builder()
                .id(savedRecipe.getId())
                .name(savedRecipe.getName())
                .ingredients(savedRecipe.getIngredients())
                .directions(savedRecipe.getDirections())
                .build();
    }
}
