package shawarma.web.restapi;

import com.springstudy.api.ShawarmaApiController;
import com.springstudy.data.ShawarmaRepository;
import com.springstudy.model.Ingredient;
import com.springstudy.model.Shawarma;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShawarmaApiControllerTest {

    @Test
    public void shouldReturnShawarmas() {
        Shawarma[] shawarmas = {
                testShawarma(1L),testShawarma(2L),
                testShawarma(3L),testShawarma(4L),
                testShawarma(5L),testShawarma(6L),
                testShawarma(7L),testShawarma(8L),
                testShawarma(9L),testShawarma(10L),
                testShawarma(11L),testShawarma(12L),
                testShawarma(13L),testShawarma(14L),
                testShawarma(15L),testShawarma(16L)
        };

        Flux<Shawarma> shawarmaFlux = Flux.just(shawarmas);

        ShawarmaRepository repo = Mockito.mock(ShawarmaRepository.class);
        Mockito.when(repo.findAll()).thenReturn(Arrays.stream(shawarmas).toList());

        WebTestClient webTestClient = WebTestClient
                .bindToController(new ShawarmaApiController(repo))
                .build();

        webTestClient.get().uri("http://localhost:8080/api/shawarmas?recent")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Shawarma.class)
                .contains(Arrays.copyOf(shawarmas, 12));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldSaveRecentShawarmas() {
        ShawarmaRepository repo = Mockito.mock(ShawarmaRepository.class);
        WebTestClient webTestClient = WebTestClient.bindToController(new ShawarmaApiController(repo))
                .build();

        Mono<Shawarma> unsavedMonoShawarma = Mono.just(testShawarma(1L));
        Shawarma savedShawarma = testShawarma(1L);
        Flux<Shawarma> saveShawarmaMono = Flux.just(savedShawarma);

        Mockito.when(repo.save(ArgumentMatchers.any())).thenReturn(savedShawarma);
        webTestClient.post()
                .uri("http://localhost:8080/api/shawarmas")
                .contentType(MediaType.APPLICATION_JSON)
                .body(unsavedMonoShawarma, Shawarma.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Shawarma.class)
                .isEqualTo(savedShawarma);
    }

    private Shawarma testShawarma(Long num) {
        Shawarma shawarma = new Shawarma();
        shawarma.setId(num != null ? num : 0L);
        shawarma.setName("Shawarma " + num);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(
                new Ingredient("INGA", "Ingedient A", Ingredient.Type.WRAP)
        );
        ingredients.add(
                new Ingredient("INGB", "Ingedient B", Ingredient.Type.PROTEIN)
        );
        shawarma.setIngredients(ingredients);
        return shawarma;

    }
}
