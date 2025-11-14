package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
    public void processOrder(String orderId) {
        System.out.println("Processing order with ID: " + orderId);
    }
}
