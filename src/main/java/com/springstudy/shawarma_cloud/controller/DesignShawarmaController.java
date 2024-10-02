package com.springstudy.shawarma_cloud.controller;

import com.springstudy.shawarma_cloud.model.Ingredient;
import com.springstudy.shawarma_cloud.model.Shawarma;
import com.springstudy.shawarma_cloud.model.ShawarmaOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("shawarmaOrder")
public class DesignShawarmaController {
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLLA", "Flour Lavash", Ingredient.Type.WRAP),
                new Ingredient("COLA", "Corn Lavash", Ingredient.Type.WRAP),
                new Ingredient("ORLA", "Origin Lavash", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CHFI", "Chicken Fillet", Ingredient.Type.PROTEIN),
                new Ingredient("PORK", "Pork", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("WHCA", "White Cabbage", Ingredient.Type.VEGGIES),
                new Ingredient("POTA", "Potato", Ingredient.Type.VEGGIES),
                new Ingredient("TOMA", "Tomato", Ingredient.Type.VEGGIES),
                new Ingredient("PICK", "Pickle", Ingredient.Type.VEGGIES),
                new Ingredient("FRCU", "Fresh Cucumber", Ingredient.Type.VEGGIES),
                new Ingredient("MAYO", "Mayo", Ingredient.Type.SAUCE),
                new Ingredient("KETC", "Ketchup", Ingredient.Type.SAUCE)
                );
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
            filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "shawarmaOrder")
    public ShawarmaOrder order() {
        return new ShawarmaOrder();
    }

    @ModelAttribute(name = "shawarma")
    public Shawarma shawarma() {
        return new Shawarma();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
