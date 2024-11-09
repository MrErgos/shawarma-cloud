package com.springstudy.data;

import com.springstudy.model.Shawarma;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ShawarmaRepository extends CrudRepository<Shawarma, Long> {
}
