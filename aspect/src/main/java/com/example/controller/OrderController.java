package com.example.controller;

import com.example.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/process-order")
    public String processOrder(@RequestParam String orderId) {
        orderService.processOrder(orderId);
        return "Order processed successfully";
    }
}
