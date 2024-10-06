package com.springstudy.shawarma_cloud.udt;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.List;

@Data
@UserDefinedType("shawarma")
public class ShawarmaUDT {
    private final String name;
    private final List<IngredientUDT> ingredients;
}
