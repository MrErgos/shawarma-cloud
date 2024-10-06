package com.springstudy.shawarma_cloud.converter;

import com.springstudy.shawarma_cloud.model.Ingredient;
import com.springstudy.shawarma_cloud.repository.IngredientRepository;
import com.springstudy.shawarma_cloud.udt.IngredientUDT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StringToIngredientConverter implements Converter<String, IngredientUDT> {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public StringToIngredientConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientUDT convert(String id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if (ingredient.isEmpty()) {
            return null;
        }
        return ingredient.map(ingredient1 ->
                        new IngredientUDT(ingredient1.getName(), ingredient1.getType()))
                .get();
    }
}
