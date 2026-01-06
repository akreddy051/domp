package com.example.payment_service.config;

import com.example.payment_service.handler.CustomResponseErrorHandler;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
       RestTemplate restTemplate = new RestTemplate();
       restTemplate.setErrorHandler(new CustomResponseErrorHandler());
       return restTemplate;
    }
}
