package com.springstudy.shawarmacloud_kitchen.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Ingredient {

    private String name;
    private Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, SAUCE
    }
}
