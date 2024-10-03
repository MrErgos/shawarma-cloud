package com.springstudy.shawarma_cloud.model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShawarmaOrder {
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

    private List<Shawarma> shawarmas = new ArrayList<>();

    public void addShawarma(Shawarma shawarma) {
        this.shawarmas.add(shawarma);
    }
}
