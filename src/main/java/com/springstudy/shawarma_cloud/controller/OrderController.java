package com.springstudy.shawarma_cloud.controller;

import com.springstudy.shawarma_cloud.jsm.JmsOrderMessagingService;
import com.springstudy.shawarma_cloud.model.ShawarmaOrder;
import com.springstudy.shawarma_cloud.model.User;
import com.springstudy.shawarma_cloud.repository.OrderRepository;
import jakarta.validation.Valid;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@ConfigurationProperties(prefix = "shawarma.orders")
public class OrderController {

    private OrderRepository orderRepository;
    private JmsOrderMessagingService jmsOrderMessagingService;

    @Autowired
    public OrderController(OrderRepository orderRepository, JmsOrderMessagingService jmsOrderMessagingService) {
        this.orderRepository = orderRepository;
        this.jmsOrderMessagingService = jmsOrderMessagingService;
    }

    @Setter
    private int pageSize = 20;

    @GetMapping
    public String orderForUser(@AuthenticationPrincipal User user,
                               Model model) {
        Pageable pageable = PageRequest.of(0, pageSize);
        model.addAttribute("orders",
                orderRepository.findByUserOrderByPlacedAtDesc(user));
        return "orderList";
    }

    @GetMapping("/current")
    public String currentOrderFrom() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid ShawarmaOrder order, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {

        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);
        orderRepository.save(order);
        jmsOrderMessagingService.sendOrder(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
