package com.springstudy.shawarma_cloud.repository;

import com.springstudy.shawarma_cloud.model.ShawarmaOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends CrudRepository<ShawarmaOrder, UUID> {
    List<ShawarmaOrder> findByDeliveryZip(String deliveryZip);
}
