package com.springstudy.shawarma_cloud.converter;

import com.springstudy.shawarma_cloud.model.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    public IngredientByIdConverter() {
        ingredientMap.put("FLLA", new Ingredient("FLLA", "Flour Lavash", Ingredient.Type.WRAP));
        ingredientMap.put("COLA", new Ingredient("COLA", "Corn Lavash", Ingredient.Type.WRAP));
        ingredientMap.put("ORLA", new Ingredient("ORLA", "Origin Lavash", Ingredient.Type.WRAP));
        ingredientMap.put("GRBF", new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
        ingredientMap.put("CHFI", new Ingredient("CHFI", "Chicken Fillet", Ingredient.Type.PROTEIN));
        ingredientMap.put("PORK", new Ingredient("PORK", "Pork", Ingredient.Type.PROTEIN));
        ingredientMap.put("TMTO", new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
        ingredientMap.put("WHCA", new Ingredient("WHCA", "White Cabbage", Ingredient.Type.VEGGIES));
        ingredientMap.put("POTA", new Ingredient("POTA", "Potato", Ingredient.Type.VEGGIES));
        ingredientMap.put("TOMA", new Ingredient("TOMA", "Tomato", Ingredient.Type.VEGGIES));
        ingredientMap.put("PICK", new Ingredient("PICK", "Pickle", Ingredient.Type.VEGGIES));
        ingredientMap.put("FRCU", new Ingredient("FRCU", "Fresh Cucumber", Ingredient.Type.VEGGIES));
        ingredientMap.put("MAYO", new Ingredient("MAYO", "Mayo", Ingredient.Type.SAUCE));
        ingredientMap.put("KETC", new Ingredient("KETC", "Ketchup", Ingredient.Type.SAUCE));
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }
}
