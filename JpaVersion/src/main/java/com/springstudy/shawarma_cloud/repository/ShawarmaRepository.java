package com.springstudy.shawarma_cloud.repository;

import com.springstudy.shawarma_cloud.model.Shawarma;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ShawarmaRepository extends PagingAndSortingRepository<Shawarma, Long> {
    Shawarma save(Shawarma shawarma);

    Optional<Shawarma> findById(Long id);
}
