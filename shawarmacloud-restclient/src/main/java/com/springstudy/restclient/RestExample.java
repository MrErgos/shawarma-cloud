package com.springstudy.restclient;

import com.springstudy.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootConfiguration
@ComponentScan
@Slf4j
public class RestExample {
    public static void main(String[] args) {
        SpringApplication.run(RestExample.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner fetchIngredients(ShawarmaRestClient client) {
        return args -> {
            log.info("----------------------- GET -------------------------");
            log.info("GETTING INGREDIENT BY IDE");
            log.info("Ingredient:  " + client.getIngredientById("ORLA"));
            log.info("GETTING ALL INGREDIENTS");
            List<Ingredient> ingredients = client.getAllIngredients();
            log.info("All ingredients:");
            for (Ingredient ingredient : ingredients) {
                log.info("   - " + ingredient);
            }
        };
    }

    @Bean
    public CommandLineRunner putAnIngredient(ShawarmaRestClient client) {
        return args -> {
            log.info("----------------------- PUT -------------------------");
            Ingredient before = client.getIngredientById("LETC");
            log.info("BEFORE:  " + before);
            client.updateIngredient(new Ingredient("LETC", "Shredded Lettuce", Ingredient.Type.VEGGIES));
            Ingredient after = client.getIngredientById("LETC");
            log.info("AFTER:  " + after);
        };
    }

    @Bean
    public CommandLineRunner addAnIngredient(ShawarmaRestClient client) {
        return args -> {
            log.info("----------------------- POST -------------------------");
            Ingredient chix = new Ingredient("CHIX", "Shredded Chicken", Ingredient.Type.PROTEIN);
            Ingredient chixAfter = client.postIngredient(chix);
            log.info("AFTER=1:  " + chixAfter);
//      Ingredient beefFajita = new Ingredient("BFFJ", "Beef Fajita", Ingredient.Type.PROTEIN);
//      URI uri = tacoCloudClient.createIngredient(beefFajita);
//      log.info("AFTER-2:  " + uri);
//      Ingredient shrimp = new Ingredient("SHMP", "Shrimp", Ingredient.Type.PROTEIN);
//      Ingredient shrimpAfter = tacoCloudClient.createIngredient(shrimp);
//      log.info("AFTER-3:  " + shrimpAfter);
        };
    }


    @Bean
    public CommandLineRunner deleteAnIngredient(ShawarmaRestClient client) {
        return args -> {
            log.info("----------------------- DELETE -------------------------");
            // start by adding a few ingredients so that we can delete them later...
            Ingredient beefFajita = new Ingredient("BFFJ", "Beef Fajita", Ingredient.Type.PROTEIN);
            client.postIngredient(beefFajita);
            Ingredient shrimp = new Ingredient("SHMP", "Shrimp", Ingredient.Type.PROTEIN);
            client.postIngredient(shrimp);


            Ingredient before = client.getIngredientById("CHIX");
            log.info("BEFORE:  " + before);
            client.deleteIngredient(before);
            Ingredient after = client.getIngredientById("CHIX");
            log.info("AFTER:  " + after);
            before = client.getIngredientById("BFFJ");
            log.info("BEFORE:  " + before);
            client.deleteIngredient(before);
            after = client.getIngredientById("BFFJ");
            log.info("AFTER:  " + after);
            before = client.getIngredientById("SHMP");
            log.info("BEFORE:  " + before);
            client.deleteIngredient(before);
            after = client.getIngredientById("SHMP");
            log.info("AFTER:  " + after);
        };
    }
}
