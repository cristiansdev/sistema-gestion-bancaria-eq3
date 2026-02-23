package com.bankcore.ms_customers.service.impl;

import com.bankcore.ms_customers.dto.request.LoginRequest;
import com.bankcore.ms_customers.dto.response.LoginResponse;
import com.bankcore.ms_customers.exception.InvalidCredentialsException;
import com.bankcore.ms_customers.entity.Customer;
import com.bankcore.ms_customers.repository.CustomerRepository;
import com.bankcore.ms_customers.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(CustomerRepository customerRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {

        Customer customer = customerRepository
                .findByEmail(request.email())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

        if (!passwordEncoder.matches(request.password(), customer.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        String token = jwtService.generateToken(customer);

        return new LoginResponse(
                token,
                "Bearer",
                jwtService.getExpirationTime(),
                customer.getId().toString()
        );
    }
}