package com.springstudy.shawarma_cloud.controller;

import com.springstudy.shawarma_cloud.model.ShawarmaOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("shawarmaOrder")
public class OrderController {

    @GetMapping("/current")
    public String currentOrderFrom() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid ShawarmaOrder order, Errors errors,
                               SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        log.info("Заказ принят: {}", order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
