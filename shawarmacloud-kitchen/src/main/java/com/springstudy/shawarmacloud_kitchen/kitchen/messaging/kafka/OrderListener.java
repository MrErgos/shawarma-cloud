package com.springstudy.shawarmacloud_kitchen.kitchen.messaging.kafka;

import com.springstudy.shawarmacloud_kitchen.kitchen.KitchenUI;
import com.springstudy.shawarmacloud_kitchen.model.ShawarmaOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderListener {
    private KitchenUI ui;

    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    @KafkaListener(topics = "shawarmacloud.order.topic", groupId = "shawarmacloud_kitchen")
    public void handle(ShawarmaOrder order, ConsumerRecord<String, ShawarmaOrder> record) {
        log.info("Получено из раздела {} с временной меткой {}",
                record.partition(), record.timestamp());
        ui.displayOrder(order);
    }
}
