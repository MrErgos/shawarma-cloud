package com.springstudy.data;

import com.springstudy.model.ShawarmaOrder;
import com.springstudy.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<ShawarmaOrder, Long> {
    List<ShawarmaOrder> findByDeliveryZip(String deliveryZip);
    List<ShawarmaOrder> findByUserOrderByPlacedAtDesc(User user);
}
