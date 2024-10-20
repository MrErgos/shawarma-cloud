package com.springstudy.shawarma_cloud.messaging_service;

import com.springstudy.shawarma_cloud.model.ShawarmaOrder;

public interface OrderMessagingService {
    void sendOrder(ShawarmaOrder order);
}
