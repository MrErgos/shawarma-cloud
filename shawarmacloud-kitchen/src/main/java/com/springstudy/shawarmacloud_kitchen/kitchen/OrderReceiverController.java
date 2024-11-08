package com.springstudy.shawarmacloud_kitchen.kitchen;

import com.springstudy.shawarmacloud_kitchen.model.ShawarmaOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//@Profile({"jms-template", "rabbitmq-template"})
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderReceiverController {
    private OrderReceiver receiver;
    @Autowired
    public OrderReceiverController(OrderReceiver receiver) {
        this.receiver = receiver;
    }

    @GetMapping("/receive")
    public String receiveOrder(Model model){
        ShawarmaOrder order = receiver.receiveOrder();
        if (order != null) {
            model.addAttribute("order", order);
            return "receiveOrder";
        }
        return "noOrder";
    }
    
}
