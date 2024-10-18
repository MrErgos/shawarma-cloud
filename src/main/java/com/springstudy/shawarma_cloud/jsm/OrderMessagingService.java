package com.springstudy.shawarma_cloud.jsm;

import com.springstudy.shawarma_cloud.model.ShawarmaOrder;

public interface OrderMessagingService {
    void sendOrder(ShawarmaOrder order);
}
