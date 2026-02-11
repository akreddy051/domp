package com.example.order_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Order Management API")
                        .version("1.0.0")
                        .description("API for managing orders in the Flipkart family")
                        .contact(new Contact()
                                .name("Singadiwar Akshay Reddy")
                                .email("akshay.singadiwar@gmail.com")
                                .url("https://akreddy.com")));
    }
}
