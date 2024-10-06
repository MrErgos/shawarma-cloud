package com.springstudy.shawarma_cloud.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data

public class Shawarma {

    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "Название должно содержать минимум 5 символов")
    private String name;

    @Size(min = 1, message = "Вы должны выбрать хотя бы 1 ингредиент")
    private List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
