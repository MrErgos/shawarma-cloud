package com.springstudy.shawarma_cloud.service;

import com.springstudy.shawarma_cloud.model.Ingredient;
import com.springstudy.shawarma_cloud.model.Shawarma;
import com.springstudy.shawarma_cloud.udt.IngredientUDT;
import com.springstudy.shawarma_cloud.udt.ShawarmaUDT;

import java.util.List;
import java.util.stream.Collectors;

public class ShawarmaUDRUtils {
    public static IngredientUDT toIngredientUDT(Ingredient ingredient) {
        return new IngredientUDT(ingredient.getName(), ingredient.getType());
    }

    public static List<IngredientUDT> toIngredientUDTs(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(ShawarmaUDRUtils::toIngredientUDT)
                .collect(Collectors.toList());
    }

    public static ShawarmaUDT toShawarmaUDT(Shawarma shawarma) {
        return new ShawarmaUDT(shawarma.getName(), shawarma.getIngredients());
    }
}
