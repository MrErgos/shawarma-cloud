package com.springstudy.shawarma_cloud.messaging_service.jsm;

import com.springstudy.shawarma_cloud.messaging_service.OrderMessagingService;
import com.springstudy.shawarma_cloud.model.ShawarmaOrder;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Profile("jms-template")
@Service
public class JmsOrderMessagingService implements OrderMessagingService {
    private JmsTemplate jms;
    private Destination orderQueue;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jms, Destination orderQueue) {
        this.jms = jms;
        this.orderQueue = orderQueue;
    }

    @Override
    public void sendOrder(ShawarmaOrder order) {
        jms.convertAndSend(
                orderQueue, //shawarmacloud.order.queue
                order,
                this::setStringProperty);
    }

    private Message setStringProperty(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }
}
