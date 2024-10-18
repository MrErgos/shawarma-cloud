package com.springstudy.shawarma_cloud.repository;

import com.springstudy.shawarma_cloud.model.ShawarmaOrder;
import com.springstudy.shawarma_cloud.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<ShawarmaOrder, Long> {
    List<ShawarmaOrder> findByDeliveryZip(String deliveryZip);
    List<ShawarmaOrder> findByUserOrderByPlacedAtDesc(User user);
}
