package com.example.payment_service.service;

import com.example.payment_service.dto.ErrorResponse;
import com.example.payment_service.dto.OrderResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService implements PaymentServiceInterface {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public PaymentService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    @CircuitBreaker(name = "orderService", fallbackMethod = "orderFallBack")
    @Retry(name = "orderService")
    public ResponseEntity<?> getOrderDetailsService(int orderId) {

        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        "http://ORDER-SERVICE/api/orders/" + orderId,
                        String.class
                );

        if (response.getStatusCode().is4xxClientError()) {
            ErrorResponse error = parse(response.getBody(), ErrorResponse.class);
            return ResponseEntity
                    .status(response.getStatusCode())
                    .body(error);
        }

        OrderResponse order = parse(response.getBody(), OrderResponse.class);
        return ResponseEntity.ok(order);
    }

    private <T> T parse(String body, Class<T> clazz) {
        try {
            return objectMapper.readValue(body, clazz);
        } catch (Exception e) {
            throw new RuntimeException("JSON parsing failed for " + clazz.getSimpleName(), e);
        }
    }

    // Fallback (only infra failures)
    public ResponseEntity<OrderResponse> orderFallBack(int orderId, Exception ex) {

        OrderResponse fallback = new OrderResponse();
        fallback.setId(orderId);
        fallback.setItem("fallback-item");
        fallback.setPrice(0);
        fallback.setInstance("fallback-response");

        return ResponseEntity.status(503).body(fallback);
    }
}
