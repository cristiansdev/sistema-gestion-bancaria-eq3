package com.bankcore.ms_customers.dto.response;

public record LoginResponse(
        String token,
        String tokenType,
        long expiresIn,
        String customerId
) {
}