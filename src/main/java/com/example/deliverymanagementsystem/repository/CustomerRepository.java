package com.example.deliverymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.deliverymanagementsystem.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
