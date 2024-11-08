package com.springstudy.shawarmacloud_kitchen.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ShawarmaOrder {
    private Date placedAt = new Date();
    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;

    private List<Shawarma> shawarmas = new ArrayList<>();
}
