package com.springstudy.shawarmacloud_kitchen.kitchen;

import com.springstudy.shawarmacloud_kitchen.model.ShawarmaOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KitchenUI {
    public void displayOrder(ShawarmaOrder order) {
        log.info("НОВЫЙ ЗАКАЗ:" + order);
    }
}
