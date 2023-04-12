package com.example.deliverymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.deliverymanagementsystem.entity.Customer;
import com.example.deliverymanagementsystem.service.CustomerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // a. Create a new customer
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    // b. Update customer details (first name, last name, email, phone, address,
    // etc.)
    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId, @RequestBody Customer customer) {
        customer.setId(customerId);
        Customer updatedCustomer = customerService.updateCustomer(customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    // c. Delete a customer
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // d. List all customers
    @GetMapping
    public ResponseEntity<List<Customer>> listAllCustomers() {
        List<Customer> customers = customerService.listAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // e. View customer details, including orders
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> viewCustomerDetails(@PathVariable Long customerId) {
        Optional<Customer> customerOptional = customerService.viewCustomerDetails(customerId);
        if (customerOptional.isPresent()) {
            return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
