package com.example.payment_service.service;

import com.example.payment_service.dto.OrderResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService implements PaymentServiceInterface{

    private final RestTemplate restTemplate;

    public PaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @CircuitBreaker(name = "orderService",fallbackMethod = "orderFallBack")
    @Retry(name = "orderService")
    public OrderResponse getOrderDetailsService(int orderId) {
        return restTemplate.getForObject("http://ORDER-SERVICE/api/orders/"+orderId,OrderResponse.class);
    }

    // Fallback method
    public OrderResponse orderFallBack(int orderId, Exception ex){
        System.out.println("Fallback triggered due to: " + ex.getMessage());
        OrderResponse fallbackOrder = new OrderResponse();
        fallbackOrder.setId(orderId);
        fallbackOrder.setItem("fallback-item");
        fallbackOrder.setPrice(0);
        fallbackOrder.setInstance("fallback-response"); // indicates fallback response
        return fallbackOrder;
    }
}
