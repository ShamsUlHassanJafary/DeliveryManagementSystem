package com.example.deliverymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.deliverymanagementsystem.entity.Customer;
import com.example.deliverymanagementsystem.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // a. Create a new customer
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // b. Update customer details (first name, last name, email, phone, address,
    // etc.)
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // c. Delete a customer
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    // d. List all customers
    public List<Customer> listAllCustomers() {
        return customerRepository.findAll();
    }

    // e. View customer details, including orders
    public Optional<Customer> viewCustomerDetails(Long customerId) {
        return customerRepository.findById(customerId);
    }
}
