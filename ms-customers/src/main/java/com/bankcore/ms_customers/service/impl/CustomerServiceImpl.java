package com.bankcore.ms_customers.service.impl;

import com.bankcore.ms_customers.dto.request.CustomerRegisterRequest;
import com.bankcore.ms_customers.dto.response.CustomerProfileResponse;
import com.bankcore.ms_customers.dto.response.CustomerResponse;
import com.bankcore.ms_customers.entity.Customer;
import com.bankcore.ms_customers.exception.ResourceAlreadyExistsException;
import com.bankcore.ms_customers.repository.CustomerRepository;
import com.bankcore.ms_customers.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CustomerResponse register(CustomerRegisterRequest request) {
        if (customerRepository.existsByEmail(request.email())) {
            throw new ResourceAlreadyExistsException("Email already registered");
        }

        if (customerRepository.existsByDni(request.dni())) {
            throw new ResourceAlreadyExistsException("DNI already registered");
        }

        String encryptedPassword = passwordEncoder.encode(request.password());

        Customer customer = new Customer(
                request.dni(),
                request.firstName(),
                request.lastName(),
                request.email(),
                encryptedPassword
        );

        Customer saved = customerRepository.save(customer);

        return new CustomerResponse(
                saved.getId(),
                saved.getDni(),
                saved.getFirstName(),
                saved.getLastName(),
                saved.getEmail(),
                saved.getStatus().name(),
                saved.getCreatedAt()
        );
    }

    public CustomerProfileResponse getAuthenticatedCustomer(UUID customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return new CustomerProfileResponse(
                customer.getId(),
                customer.getDni(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getStatus().name(),
                customer.getCreatedAt()
        );
    }
}
