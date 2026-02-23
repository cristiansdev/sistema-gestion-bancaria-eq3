package com.bankcore.ms_customers.service;

import com.bankcore.ms_customers.dto.request.CustomerRegisterRequest;
import com.bankcore.ms_customers.dto.response.CustomerProfileResponse;
import com.bankcore.ms_customers.dto.response.CustomerResponse;

import java.util.UUID;

public interface CustomerService {
    CustomerResponse register(CustomerRegisterRequest request);
    CustomerProfileResponse getAuthenticatedCustomer(UUID customerId);
}
