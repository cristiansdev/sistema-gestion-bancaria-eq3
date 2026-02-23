package com.bankcore.ms_customers.controller;

import com.bankcore.ms_customers.dto.request.LoginRequest;
import com.bankcore.ms_customers.dto.response.LoginResponse;
import com.bankcore.ms_customers.service.impl.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}