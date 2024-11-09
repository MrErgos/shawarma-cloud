package com.springstudy.api;

import com.springstudy.data.ShawarmaRepository;
import com.springstudy.model.Shawarma;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/shawarmas", produces = "application/json")
@CrossOrigin(origins = "http://shawarmacloud:8080")
public class ShawarmaApiController {
    private ShawarmaRepository shawarmaRepository;

    public ShawarmaApiController(ShawarmaRepository shawarmaRepository) {
        this.shawarmaRepository = shawarmaRepository;
    }

    @GetMapping(params = "recent")
    public Flux<Shawarma> recentShawarmas() {
        return Flux.fromIterable(shawarmaRepository.findAll()).take(12);
    }

    @GetMapping("/{id}")
    public Mono<Shawarma> shawarmaById(@PathVariable("id") Long id) {
        return Mono.just(shawarmaRepository.findById(id).get());
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Shawarma> postShawarma(@RequestBody Mono<Shawarma> monoShawarma) {
        return monoShawarma
                .flatMap(shawarma -> Mono.just(shawarmaRepository.save(shawarma)));
    }

}
