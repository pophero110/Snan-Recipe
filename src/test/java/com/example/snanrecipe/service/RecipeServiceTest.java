package com.example.snanrecipe.service;

import com.example.snanrecipe.model.Recipe;
import com.example.snanrecipe.model.RecipeDto;
import com.example.snanrecipe.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @InjectMocks
    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Test
    void createRecipe() {
        RecipeDto fakeRecipeDto = RecipeDto.builder()
                .name("Hamburger")
                .ingredients("cheese")
                .directions("combine ingredients")
                .build();

        Recipe savedRecipe = Recipe.builder()
                .id(1L)
                .name(fakeRecipeDto.getName())
                .ingredients(fakeRecipeDto.getIngredients())
                .directions(fakeRecipeDto.getDirections())
                .build();

        when(recipeRepository.save(any(Recipe.class))).thenReturn(savedRecipe);

        RecipeDto recipeDto = recipeService.createRecipe(fakeRecipeDto);

        // Assert input of the method
        assertThat(recipeDto, notNullValue());
        assertThat(recipeDto.getName(), is(fakeRecipeDto.getName()));
        assertThat(recipeDto.getIngredients(), is(fakeRecipeDto.getIngredients()));
        assertThat(recipeDto.getDirections(), is(fakeRecipeDto.getDirections()));

        // Assert interaction with recipeRepository
        ArgumentCaptor<Recipe> recipeCaptor = ArgumentCaptor.forClass(Recipe.class);
        verify(recipeRepository, times(1)).save(recipeCaptor.capture());
        Recipe recipe = recipeCaptor.getValue();

        // Assert that RecipeDto map to Recipe correctly
        assertThat(recipe.getName(), is(fakeRecipeDto.getName()));
        assertThat(recipe.getIngredients(), is(fakeRecipeDto.getIngredients()));
        assertThat(recipe.getDirections(), is(fakeRecipeDto.getDirections()));

        // Assert output of the method
        assertThat(recipeDto.getId(), is(savedRecipe.getId()));
        assertThat(recipeDto.getName(), is(savedRecipe.getName()));
        assertThat(recipeDto.getIngredients(), is(savedRecipe.getIngredients()));
        assertThat(recipeDto.getDirections(), is(savedRecipe.getDirections()));
    }
}