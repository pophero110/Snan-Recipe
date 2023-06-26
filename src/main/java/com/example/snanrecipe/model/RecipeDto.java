package com.example.snanrecipe.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RecipeDto {

    private Long id;
    private String name;
    private String ingredients;
    private String directions;
}
