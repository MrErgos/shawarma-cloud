package com.springstudy.shawarma_cloud.controller.api;

import com.springstudy.shawarma_cloud.model.Ingredient;
import com.springstudy.shawarma_cloud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
@CrossOrigin(origins = "http://shawarmacloud:8080")
public class IngredientApiController {
    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientApiController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public Iterable<Ingredient> allIngredients() {
        return ingredientRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable("id") String ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }
}
