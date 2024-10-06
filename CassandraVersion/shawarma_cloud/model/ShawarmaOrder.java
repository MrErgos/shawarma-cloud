package com.springstudy.shawarma_cloud.model;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.springstudy.shawarma_cloud.udt.ShawarmaUDT;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Table("orders")
public class ShawarmaOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @PrimaryKey
    private UUID id = Uuids.timeBased();

    private Date placedAt = new Date();

    @NotBlank(message = "Имя обязательно")
    private String deliveryName;
    @NotBlank(message = "Улица обязательна")
    private String deliveryStreet;
    @NotBlank(message = "Город обязателен")
    private String deliveryCity;
    @NotBlank(message = "Область обязательна")
    private String deliveryState;
    @NotBlank(message = "Индекс обязателен")
    private String deliveryZip;

    @CreditCardNumber(message = "Такого номера карты не существует")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
    message = "Формат даты должен быть ММ/ГГ")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Неверный CVV")
    private String ccCVV;

    @Column("shawarmas")
    private List<ShawarmaUDT> shawarmas = new ArrayList<>();

    public void addShawarma(ShawarmaUDT shawarma) {
        this.shawarmas.add(shawarma);
    }
}
