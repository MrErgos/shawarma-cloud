package com.springstudy.restclient;

import com.springstudy.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    public List<Ingredient> getAllIngredients() {
        return restTemplate.exchange("http://localhost:8080/data-api/ingredients/{id}",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Ingredient>>(){})
                .getBody();
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

