package com.springstudy.shawarmacloud_kitchen.kitchen.messaging.rabbitmq.listener;

import com.springstudy.shawarmacloud_kitchen.kitchen.KitchenUI;
import com.springstudy.shawarmacloud_kitchen.model.ShawarmaOrder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("rabbit-template")
@Component
public class OrderListener {
    private KitchenUI kitchenUI;

    @Autowired
    public OrderListener(KitchenUI kitchenUI) {
        this.kitchenUI = kitchenUI;
    }


    @RabbitListener(queues = "shawarmacloud.order.queue")
    public void receiveOrder(ShawarmaOrder order) {
        kitchenUI.displayOrder(order);
    }
}
