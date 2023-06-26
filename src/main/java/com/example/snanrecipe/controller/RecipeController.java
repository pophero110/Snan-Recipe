package com.example.snanrecipe.controller;

import com.example.snanrecipe.model.RecipeDto;
import com.example.snanrecipe.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<RecipeDto> createRecipe(@RequestBody RecipeDto recipeDto) {
        RecipeDto createRecipeDto = recipeService.createRecipe(recipeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createRecipeDto);
    }
}
