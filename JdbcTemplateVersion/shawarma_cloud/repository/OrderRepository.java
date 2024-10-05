package com.springstudy.shawarma_cloud.repository;

import com.springstudy.shawarma_cloud.model.ShawarmaOrder;

public interface OrderRepository {
    ShawarmaOrder save(ShawarmaOrder order);
}
