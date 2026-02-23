package com.bankcore.ms_customers.controller;

import com.bankcore.ms_customers.dto.request.CustomerRegisterRequest;
import com.bankcore.ms_customers.dto.response.CustomerResponse;
import com.bankcore.ms_customers.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @PostMapping("/register")
    public CustomerResponse register(@Valid @RequestBody CustomerRegisterRequest request) {
        return service.register(request);
    }
}
