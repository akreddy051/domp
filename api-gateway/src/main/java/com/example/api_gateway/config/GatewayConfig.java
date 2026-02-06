package com.example.api_gateway.config;

import com.example.api_gateway.filter.AuthFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRoutes(
            RouteLocatorBuilder builder,
            AuthFilter authFilter) {

        return builder.routes()

                // ===============================
                // Order Service Route
                // ===============================
                .route("order-service", r -> r
                        .path("/api/orders/**")
                        .filters(f -> f
                                .filter(authFilter)
                                .rewritePath(
                                        "/api/orders/(?<segment>.*)",
                                        "/api/orders/${segment}"
                                )
                        )
                        .uri("lb://ORDER-SERVICE")
                )

                // ===============================
                // Payment Service Route
                // ===============================
                .route("payment-service", r -> r
                        .path("/api/payments/order/{id}")
                        .and()
                        .method(HttpMethod.GET)
                        .filters(f -> f
                                .filter(authFilter)
                                .rewritePath(
                                        "/api/payments/order/(?<segment>.*)",
                                        "/api/payments/order/${segment}"
                                )
                        )
                        .uri("lb://PAYMENT-SERVICE")
                )

                .build();
    }
}
