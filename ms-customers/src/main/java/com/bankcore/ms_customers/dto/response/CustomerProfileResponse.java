package com.bankcore.ms_customers.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerProfileResponse(
        UUID id,
        String dni,
        String firstName,
        String lastName,
        String email,
        String phone,
        String address,
        String status,
        LocalDateTime createdAt
) {}