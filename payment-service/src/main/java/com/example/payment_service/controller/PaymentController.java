package com.example.payment_service.controller;

import com.example.payment_service.service.PaymentServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments/order")
public class PaymentController {

    private final PaymentServiceInterface paymentService;

    public PaymentController(PaymentServiceInterface paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> retrieveOrderDetails(
            @PathVariable int orderId) {

        return paymentService.getOrderDetailsService(orderId);
    }
}
