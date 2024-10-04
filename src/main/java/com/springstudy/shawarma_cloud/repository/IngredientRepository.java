package com.springstudy.shawarma_cloud.repository;

import com.springstudy.shawarma_cloud.model.Ingredient;

import java.util.Optional;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
}
