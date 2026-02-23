package com.bankcore.ms_customers.service.impl;

import com.bankcore.ms_customers.dto.request.CustomerRegisterRequest;
import com.bankcore.ms_customers.dto.response.CustomerResponse;
import com.bankcore.ms_customers.entity.Customer;
import com.bankcore.ms_customers.exception.ResourceAlreadyExistsException;
import com.bankcore.ms_customers.repository.CustomerRepository;
import com.bankcore.ms_customers.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CustomerResponse register(CustomerRegisterRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new ResourceAlreadyExistsException("Email already registered");
        }

        if (repository.existsByDni(request.dni())) {
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

        Customer saved = repository.save(customer);

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
}
