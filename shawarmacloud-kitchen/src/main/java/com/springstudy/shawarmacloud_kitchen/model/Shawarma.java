package com.springstudy.shawarmacloud_kitchen.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Shawarma {
    private Date createdAt = new Date();

    private String name;

    private List<Ingredient> ingredients = new ArrayList<>();

}
