package com.springstudy.data;

import com.springstudy.model.Shawarma;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ShawarmaRepository extends PagingAndSortingRepository<Shawarma, Long> {
    Shawarma save(Shawarma shawarma);

    Optional<Shawarma> findById(Long id);
}
