package com.springstudy.shawarma_cloud.repository;

import com.springstudy.shawarma_cloud.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
