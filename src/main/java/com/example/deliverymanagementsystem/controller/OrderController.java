package com.example.deliverymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.deliverymanagementsystem.entity.Customer;
import com.example.deliverymanagementsystem.entity.Order;
import com.example.deliverymanagementsystem.service.CustomerService;
import com.example.deliverymanagementsystem.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    // a. Create a new order for a customer
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.createOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    // b. Update order details (status, shipping address, etc.)
    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderId, @RequestBody Order order) {
        order.setId(orderId);
        Order updatedOrder = orderService.updateOrder(order);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    // c. Cancel an order
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId) {
        Order cancelledOrder = orderService.cancelOrder(orderId);
        return new ResponseEntity<>(cancelledOrder, HttpStatus.OK);
    }

    // d. List all orders
    @GetMapping
    public ResponseEntity<List<Order>> listAllOrders() {
        List<Order> orders = orderService.listAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // e. View order details, including order items and customer information
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> viewOrderDetails(@PathVariable Long orderId) {
        Optional<Order> orderOptional = orderService.viewOrderDetails(orderId);
        if (orderOptional.isPresent()) {
            return new ResponseEntity<>(orderOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // f. Search for orders by customer, date range, or status
    @GetMapping("/search")
    public ResponseEntity<List<Order>> searchOrders(
            @RequestParam(required = false) Customer customer,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate,
            @RequestParam(required = false) String status) {

        List<Order> orders;

        if (customer != null) {
            orders = orderService.searchOrdersByCustomer(customer);
        } else if (startDate != null && endDate != null) {
            orders = orderService.searchOrdersByDateRange(startDate, endDate);
        } else if (status != null) {
            orders = orderService.searchOrdersByStatus(status);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/create-order")
    public ResponseEntity<Order> createOrderWithCustomer(@RequestParam Long customerId, @RequestBody Order order) {
        Optional<Customer> customerOptional = customerService.viewCustomerDetails(customerId);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            order.setCustomer(customer);
            Order savedOrder = orderService.createOrder(order);
            return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
