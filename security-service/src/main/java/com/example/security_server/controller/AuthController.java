package com.example.security_server.controller;

import com.example.security_server.dto.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/validate")
    public ResponseEntity<AuthResponse> validate(
            @AuthenticationPrincipal Jwt jwt) {

        AuthResponse response = new AuthResponse(
                true,
                jwt.getSubject(),
                jwt.getClaimAsString("scope")
        );

        return ResponseEntity.ok(response);
    }
}

