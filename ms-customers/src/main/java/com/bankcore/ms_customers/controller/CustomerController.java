package com.bankcore.ms_customers.controller;

import com.bankcore.ms_customers.dto.request.CustomerRegisterRequest;
import com.bankcore.ms_customers.dto.response.CustomerProfileResponse;
import com.bankcore.ms_customers.dto.response.CustomerResponse;
import com.bankcore.ms_customers.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/register")
    public CustomerResponse register(@Valid @RequestBody CustomerRegisterRequest request) {
        return customerService.register(request);
    }
    @GetMapping("/me")
    public CustomerProfileResponse getMyProfile(Authentication authentication) {

        UUID customerId = UUID.fromString(authentication.getName());

        return customerService.getAuthenticatedCustomer(customerId);
    }
}
