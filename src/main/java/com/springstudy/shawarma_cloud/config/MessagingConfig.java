package com.springstudy.shawarma_cloud.config;

import com.springstudy.shawarma_cloud.model.ShawarmaOrder;
import jakarta.jms.Destination;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class MessagingConfig {

    @Profile("rabbit-template")
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Profile("jms-template")
    @Bean
    public Destination orderQueue() {
        return new ActiveMQQueue("shawarmacloud.order.queue");
    }

    @Profile("jms-template")
    @Bean
    public MappingJackson2MessageConverter mappingJackson2MessageConverter() {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");

        Map<String, Class<?>> typeIdMapping = new HashMap<>();
        typeIdMapping.put("order", ShawarmaOrder.class);
        messageConverter.setTypeIdMappings(typeIdMapping);

        return messageConverter;
    }
}
