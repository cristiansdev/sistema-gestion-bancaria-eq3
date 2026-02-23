package com.bankcore.ms_customers.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerResponse(
        UUID id,
        String dni,
        String firstName,
        String lastName,
        String email,
        String status,
        LocalDateTime createdAt
) {
}
