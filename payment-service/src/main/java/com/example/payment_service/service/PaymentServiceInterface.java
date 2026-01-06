package com.example.payment_service.service;

import org.springframework.http.ResponseEntity;

public interface PaymentServiceInterface {
    ResponseEntity<?> getOrderDetailsService(int orderId);
}
