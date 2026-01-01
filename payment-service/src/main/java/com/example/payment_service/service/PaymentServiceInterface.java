package com.example.payment_service.service;

import com.example.payment_service.dto.OrderResponse;

public interface PaymentServiceInterface {
    OrderResponse getOrderDetailsService(int orderId);
}
