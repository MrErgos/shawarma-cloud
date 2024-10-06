package com.springstudy.shawarma_cloud.converter;

import com.springstudy.shawarma_cloud.model.Ingredient;
import com.springstudy.shawarma_cloud.repository.IngredientRepository;
import com.springstudy.shawarma_cloud.udt.IngredientUDT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).orElse(null);
    }
}
