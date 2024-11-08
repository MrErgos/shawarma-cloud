package com.springstudy.shawarmacloud_kitchen.kitchen.messaging.jms.listener;

import com.springstudy.shawarmacloud_kitchen.kitchen.KitchenUI;
import com.springstudy.shawarmacloud_kitchen.model.ShawarmaOrder;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Profile("jms-listener")
@Component
public class OrderListener {
    private KitchenUI ui;

    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    @JmsListener(destination = "shawarmacloud.order.queue")
    public void receiveOrder(ShawarmaOrder order) {
        ui.displayOrder(order);;
    }
}
