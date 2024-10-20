package com.springstudy.shawarma_cloud.messaging_service.kafka;

import com.springstudy.shawarma_cloud.messaging_service.OrderMessagingService;
import com.springstudy.shawarma_cloud.model.ShawarmaOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Profile("kafka-template")
@Service
public class KafkaOrderMessagingService implements OrderMessagingService {
    private KafkaTemplate<String, ShawarmaOrder> template;

    @Autowired
    public KafkaOrderMessagingService(KafkaTemplate<String, ShawarmaOrder> template) {
        this.template = template;
    }

    @Override
    public void sendOrder(ShawarmaOrder order) {
        template.send("shawarmacloud.order.topic", order);
    }
}
