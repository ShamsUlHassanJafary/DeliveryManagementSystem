package com.example.deliverymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.deliverymanagementsystem.entity.Order;
import com.example.deliverymanagementsystem.entity.OrderItem;
import com.example.deliverymanagementsystem.entity.Product;
import com.example.deliverymanagementsystem.repository.OrderItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    // Create a new order item
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    // Update an existing order item
    public OrderItem updateOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    // Delete an order item by ID
    public void deleteOrderItem(Long orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }

    // List all order items
    public List<OrderItem> listAllOrderItems() {
        return orderItemRepository.findAll();
    }

    // View order item details by ID
    public Optional<OrderItem> viewOrderItemDetails(Long orderItemId) {
        return orderItemRepository.findById(orderItemId);
    }

    // Find order items by order
    public List<OrderItem> findOrderItemsByOrder(Order order) {
        return orderItemRepository.findByOrder(order);
    }

    // Find order items by product
    public List<OrderItem> findOrderItemsByProduct(Product product) {
        return orderItemRepository.findByProduct(product);
    }
}
