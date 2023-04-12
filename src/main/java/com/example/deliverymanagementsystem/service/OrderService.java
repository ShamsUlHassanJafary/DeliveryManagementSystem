package com.example.deliverymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.deliverymanagementsystem.entity.Customer;
import com.example.deliverymanagementsystem.entity.Order;
import com.example.deliverymanagementsystem.repository.OrderRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // a. Create a new order for a customer
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    // b. Update order details (status, shipping address, etc.)
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    // c. Cancel an order
    public Order cancelOrder(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setStatus("Cancelled");
            return orderRepository.save(order);
        }

        return null;
    }

    // d. List all orders
    public List<Order> listAllOrders() {
        return orderRepository.findAll();
    }

    // e. View order details, including order items and customer information
    public Optional<Order> viewOrderDetails(Long orderId) {
        return orderRepository.findById(orderId);
    }

    // f. Search for orders by customer, date range, or status
    public List<Order> searchOrdersByCustomer(Customer customer) {
        return orderRepository.findByCustomer(customer);
    }

    public List<Order> searchOrdersByDateRange(Date startDate, Date endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate);
    }

    public List<Order> searchOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }
}
