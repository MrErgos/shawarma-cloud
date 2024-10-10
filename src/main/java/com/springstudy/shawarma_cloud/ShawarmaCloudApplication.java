package com.springstudy.shawarma_cloud;

import com.springstudy.shawarma_cloud.model.Ingredient;
import com.springstudy.shawarma_cloud.repository.IngredientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class ShawarmaCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShawarmaCloudApplication.class, args);
	}

	@Bean
	@Profile("!prod")
	public CommandLineRunner dataLoader(IngredientRepository repository) {
		return args -> {
			repository.deleteAll();
			repository.save(new Ingredient("ORLA", "Origin Lavash", Ingredient.Type.WRAP));
			repository.save(new Ingredient("CHLA", "Cheese Lavash", Ingredient.Type.WRAP));
			repository.save(new Ingredient("COLA", "Corn Lavash", Ingredient.Type.WRAP));
			repository.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
			repository.save(new Ingredient("CHFI", "Chicken Fillet", Ingredient.Type.PROTEIN));
			repository.save(new Ingredient("PORK", "Pork", Ingredient.Type.PROTEIN));
			repository.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
			repository.save(new Ingredient("WHCA", "White Cabbage", Ingredient.Type.VEGGIES));
			repository.save(new Ingredient("POTA", "Potato", Ingredient.Type.VEGGIES));
			repository.save(new Ingredient("PICK", "Pickle", Ingredient.Type.VEGGIES));
			repository.save(new Ingredient("FRCU", "Fresh Cucumber", Ingredient.Type.VEGGIES));
			repository.save(new Ingredient("MAYO", "Mayo", Ingredient.Type.SAUCE));
			repository.save(new Ingredient("KETC", "Ketchup", Ingredient.Type.SAUCE));
		};
	}
}
