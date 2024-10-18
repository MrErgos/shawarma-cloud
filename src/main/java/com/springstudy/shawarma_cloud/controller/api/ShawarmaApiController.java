package com.springstudy.shawarma_cloud.controller.api;

import com.springstudy.shawarma_cloud.model.Shawarma;
import com.springstudy.shawarma_cloud.repository.ShawarmaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Iterable<Shawarma> recentShawarmas() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return shawarmaRepository.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shawarma> shawarmaById(@PathVariable("id") Long id) {
        Optional<Shawarma> shawarma = shawarmaRepository.findById(id);
        if (shawarma.isPresent()) {
            return new ResponseEntity<>(shawarma.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Shawarma postShawarma(@RequestBody Shawarma shawarma) {
        return shawarmaRepository.save(shawarma);
    }

}
