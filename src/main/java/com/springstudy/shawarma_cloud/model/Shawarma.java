package com.springstudy.shawarma_cloud.model;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.springstudy.shawarma_cloud.service.ShawarmaUDRUtils;
import com.springstudy.shawarma_cloud.udt.IngredientUDT;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Table("shawarmas")
public class Shawarma {
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private UUID id = Uuids.timeBased();

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "Название должно содержать минимум 5 символов")
    private String name;

    @Size(min = 1, message = "Вы должны выбрать хотя бы 1 ингредиент")
    @Column("ingredients")
    private List<IngredientUDT> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ShawarmaUDRUtils.toIngredientUDT(ingredient));
    }
}
