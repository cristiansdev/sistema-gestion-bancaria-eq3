package com.bankcore.ms_customers.repository;

import com.bankcore.ms_customers.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByDni(String dni);

    boolean existsByEmail(String email);

    boolean existsByDni(String dni);
}
