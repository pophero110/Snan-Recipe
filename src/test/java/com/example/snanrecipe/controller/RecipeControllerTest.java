package com.example.snanrecipe.controller;

import com.example.snanrecipe.model.RecipeDto;
import com.example.snanrecipe.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @InjectMocks
    private RecipeController recipeController;

    @Mock
    private RecipeService recipeService;

    @Test
    void createRecipe() {
        RecipeDto recipeDto = RecipeDto.builder()
                .name("Hamburger")
                .ingredients("Cheese, " + "Bread, Ham")
                .directions("combine ingredients")
                .build();
        when(recipeService.createRecipe(recipeDto)).thenReturn(recipeDto);

        ResponseEntity<RecipeDto> responseEntity =
                recipeController.createRecipe(recipeDto);

        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(responseEntity.getBody(), equalTo(recipeDto));
        verify(recipeService, times(1)).createRecipe(recipeDto);
    }
}