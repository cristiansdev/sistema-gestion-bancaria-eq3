package com.bankcore.ms_customers.service;

import com.bankcore.ms_customers.dto.request.CustomerRegisterRequest;
import com.bankcore.ms_customers.dto.response.CustomerResponse;

public interface CustomerService {
    CustomerResponse register(CustomerRegisterRequest request);
}
