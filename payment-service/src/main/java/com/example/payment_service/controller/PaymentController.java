package com.example.payment_service.controller;

import com.example.payment_service.dto.OrderResponse;
import com.example.payment_service.service.PaymentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/payments/order")
public class PaymentController {

    @Autowired
    PaymentServiceInterface paymentServiceInterface;

    @GetMapping("/{orderId}")
    public OrderResponse retrieveOrderDetails(@PathVariable("orderId") int orderId){
        return paymentServiceInterface.getOrderDetailsService(orderId);
    }
}
