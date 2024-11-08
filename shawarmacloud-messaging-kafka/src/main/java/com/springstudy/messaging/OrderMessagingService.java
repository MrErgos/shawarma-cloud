package com.springstudy.messaging;

import com.springstudy.model.ShawarmaOrder;

public interface OrderMessagingService {
    void sendOrder(ShawarmaOrder order);
}
