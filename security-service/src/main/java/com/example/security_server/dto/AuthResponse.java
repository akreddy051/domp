package com.example.security_server.dto;

public record AuthResponse(
        boolean valid,
        String subject,
        String scope
) {}

