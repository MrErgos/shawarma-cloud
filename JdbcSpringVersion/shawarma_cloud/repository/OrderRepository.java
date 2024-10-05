package com.springstudy.shawarma_cloud.repository;

import com.springstudy.shawarma_cloud.model.ShawarmaOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<ShawarmaOrder, Long> {
}
