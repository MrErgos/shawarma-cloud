package com.springstudy.shawarmacloud_kitchen.kitchen.messaging.rabbitmq;

import com.springstudy.shawarmacloud_kitchen.kitchen.OrderReceiver;
import com.springstudy.shawarmacloud_kitchen.model.ShawarmaOrder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

@Profile("rabbit-template")
@Component
public class RabbitOrderReceiver implements OrderReceiver {
    private RabbitTemplate rabbitTemplate;
    private MessageConverter messageConverter;

    public RabbitOrderReceiver(RabbitTemplate rabbitTemplate, MessageConverter messageConverter) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageConverter = messageConverter;
    }

    @Override
    public ShawarmaOrder receiveOrder() {
        return rabbitTemplate.receiveAndConvert("shawarmacloud.order.queue",
                new ParameterizedTypeReference<ShawarmaOrder>(){});

    }
}

