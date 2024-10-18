package com.springstudy.shawarma_cloud;

import com.springstudy.shawarma_cloud.model.Ingredient;
import com.springstudy.shawarma_cloud.model.Shawarma;
import com.springstudy.shawarma_cloud.repository.IngredientRepository;
import com.springstudy.shawarma_cloud.repository.ShawarmaRepository;
import com.springstudy.shawarma_cloud.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class ShawarmaCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShawarmaCloudApplication.class, args);
	}

	@Bean
	@Profile("!prod")
	public CommandLineRunner dataLoader(IngredientRepository repository, UserRepository userRepository,
										PasswordEncoder encoder, ShawarmaRepository shawarmaRepository) {
		return args -> {
			repository.deleteAll();
			Ingredient orla = new Ingredient("ORLA", "Origin Lavash", Ingredient.Type.WRAP);
			Ingredient chla = new Ingredient("CHLA", "Cheese Lavash", Ingredient.Type.WRAP);
			Ingredient cola = new Ingredient("COLA", "Corn Lavash", Ingredient.Type.WRAP);
			Ingredient grbf = new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN);
			Ingredient chfi = new Ingredient("CHFI", "Chicken Fillet", Ingredient.Type.PROTEIN);
			Ingredient pork = new Ingredient("PORK", "Pork", Ingredient.Type.PROTEIN);
			Ingredient tmto = new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES);
			Ingredient whca = new Ingredient("WHCA", "White Cabbage", Ingredient.Type.VEGGIES);
			Ingredient pota = new Ingredient("POTA", "Potato", Ingredient.Type.VEGGIES);
			Ingredient pick = new Ingredient("PICK", "Pickle", Ingredient.Type.VEGGIES);
			Ingredient frcu = new Ingredient("FRCU", "Fresh Cucumber", Ingredient.Type.VEGGIES);
			Ingredient mayo = new Ingredient("MAYO", "Mayo", Ingredient.Type.SAUCE);
			Ingredient ketc = new Ingredient("KETC", "Ketchup", Ingredient.Type.SAUCE);

			repository.save(orla);
			repository.save(chla);
			repository.save(cola);
			repository.save(grbf);
			repository.save(chfi);
			repository.save(pork);
			repository.save(tmto);
			repository.save(whca);
			repository.save(pota);
			repository.save(pick);
			repository.save(frcu);
			repository.save(mayo);
			repository.save(ketc);

			Shawarma shawarma1 = new Shawarma();
			shawarma1.setName("Супер-пупер");
			shawarma1.setIngredients(Arrays.asList(
					orla, grbf, tmto, whca, pick, mayo
			));
			shawarmaRepository.save(shawarma1);

			Shawarma shawarma2 = new Shawarma();
			shawarma2.setName("Только не Кетчуп");
			shawarma2.setIngredients(Arrays.asList(
					orla, grbf, ketc
			));
			shawarmaRepository.save(shawarma2);

			Shawarma shawarma3 = new Shawarma();
			shawarma3.setName("Ультра");
			shawarma3.setIngredients(Arrays.asList(
					orla, grbf, tmto, whca, pick, mayo,
					chla, cola, chfi, pork
			));
			shawarmaRepository.save(shawarma3);
		};
	}
}
