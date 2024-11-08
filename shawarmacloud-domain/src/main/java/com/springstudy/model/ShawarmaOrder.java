package com.springstudy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.rest.core.annotation.RestResource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@RestResource(rel = "orders", path = "orders")
public class ShawarmaOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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

    @OneToMany(cascade = CascadeType.ALL)
    private List<Shawarma> shawarmas = new ArrayList<>();

    @ManyToOne
    private User user;

    public void addShawarma(Shawarma shawarma) {
        this.shawarmas.add(shawarma);
    }
}
