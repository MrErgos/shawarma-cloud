package com.springstudy.shawarma_cloud.repository;

import com.springstudy.shawarma_cloud.model.ShawarmaOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<ShawarmaOrder, String> {
    List<ShawarmaOrder> findByDeliveryZip(String deliveryZip);
}
