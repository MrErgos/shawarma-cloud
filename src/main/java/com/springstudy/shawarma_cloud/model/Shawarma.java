package com.springstudy.shawarma_cloud.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class Shawarma {
    @NotNull
    @Size(min = 5, message = "Название должно содержать минимум 5 символов")
    private String name;

    @NotNull
    @Size(min = 1, message = "Вы должны выбрать хотя бы 1 ингредиент")
    private List<Ingredient> ingredients;
}
