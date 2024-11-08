package com.springstudy.shawarmacloud_kitchen.kitchen.messaging.jms;

import com.springstudy.shawarmacloud_kitchen.kitchen.OrderReceiver;
import com.springstudy.shawarmacloud_kitchen.model.ShawarmaOrder;
import jakarta.jms.Destination;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

//@Profile("jms-template")
@Component
public class JmsOrderReceiver implements OrderReceiver {
    
    private JmsTemplate jmsTemplate;
    private Destination destination;

    public JmsOrderReceiver(JmsTemplate jmsTemplate, Destination destination) {
        this.jmsTemplate = jmsTemplate;
        this.destination = destination;
    }

    @Override
    public ShawarmaOrder receiveOrder() throws MessageConversionException {
        return (ShawarmaOrder) jmsTemplate.receiveAndConvert(destination);
    }
}
