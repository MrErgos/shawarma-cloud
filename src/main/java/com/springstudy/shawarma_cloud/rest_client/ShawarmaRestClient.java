package com.springstudy.shawarma_cloud.rest_client;

import com.springstudy.shawarma_cloud.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ShawarmaRestClient {
    private RestTemplate restTemplate;

    public ShawarmaRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Ingredient getIngredientById(String ingredientId) {
        return restTemplate.getForObject("http://localhost:8080/data-api/ingredients/{id}",
                Ingredient.class, ingredientId);
    }

    public void updateIngredient(Ingredient ingredient) {
        restTemplate.put("http://localhost:8080/data-api/ingredients/{id}",
                ingredient, ingredient.getId());
    }

    public void deleteIngredient(Ingredient ingredient) {
        restTemplate.delete("http://localhost:8080/data-api/ingredients/{id}",
                ingredient.getId());
    }

    public Ingredient postIngredient(Ingredient ingredient) {
        ResponseEntity<Ingredient> response = restTemplate.postForEntity("http://localhost:8080/data-api/ingredients",
                ingredient, Ingredient.class);
        log.info("Новый ингредиент добавлен на {}", response.getHeaders().getLocation());
        return response.getBody();
    }
}

