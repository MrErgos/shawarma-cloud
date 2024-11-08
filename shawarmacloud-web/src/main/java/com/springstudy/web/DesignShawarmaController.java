package com.springstudy.web;

import com.springstudy.data.IngredientRepository;
import com.springstudy.model.Ingredient;
import com.springstudy.model.Shawarma;
import com.springstudy.model.ShawarmaOrder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("shawarmaOrder")
public class DesignShawarmaController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignShawarmaController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {

        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                filterByType((List) ingredients, type));
        }
    }

    @PostMapping
    public String processShawarma(@Valid Shawarma shawarma, Errors errors,
                                  @ModelAttribute ShawarmaOrder shawarmaOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        shawarmaOrder.addShawarma(shawarma);

        return "redirect:/orders/current";
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
