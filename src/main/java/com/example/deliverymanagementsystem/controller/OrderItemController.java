package com.example.deliverymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.deliverymanagementsystem.entity.Order;
import com.example.deliverymanagementsystem.entity.OrderItem;
import com.example.deliverymanagementsystem.entity.Product;
import com.example.deliverymanagementsystem.service.OrderItemService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    // Create a new order item
    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem savedOrderItem = orderItemService.createOrderItem(orderItem);
        return new ResponseEntity<>(savedOrderItem, HttpStatus.CREATED);
    }

    // Update an existing order item
    @PutMapping("/{orderItemId}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long orderItemId, @RequestBody OrderItem orderItem) {
        orderItem.setId(orderItemId);
        OrderItem updatedOrderItem = orderItemService.updateOrderItem(orderItem);
        return new ResponseEntity<>(updatedOrderItem, HttpStatus.OK);
    }

    // Delete an order item by ID
    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long orderItemId) {
        orderItemService.deleteOrderItem(orderItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // List all order items
    @GetMapping
    public ResponseEntity<List<OrderItem>> listAllOrderItems() {
        List<OrderItem> orderItems = orderItemService.listAllOrderItems();
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    // View order item details by ID
    @GetMapping("/{orderItemId}")
    public ResponseEntity<OrderItem> viewOrderItemDetails(@PathVariable Long orderItemId) {
        Optional<OrderItem> orderItemOptional = orderItemService.viewOrderItemDetails(orderItemId);
        if (orderItemOptional.isPresent()) {
            return new ResponseEntity<>(orderItemOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Find order items by order ID
    @GetMapping("/by-order/{orderId}")
    public ResponseEntity<List<OrderItem>> findOrderItemsByOrderId(@PathVariable Long orderId) {
        Order order = new Order();
        order.setId(orderId);
        List<OrderItem> orderItems = orderItemService.findOrderItemsByOrder(order);
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    // Find order items by product ID
    @GetMapping("/by-product/{productId}")
    public ResponseEntity<List<OrderItem>> findOrderItemsByProductId(@PathVariable Long productId) {
        Product product = new Product();
        product.setId(productId);
        List<OrderItem> orderItems = orderItemService.findOrderItemsByProduct(product);
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }
}
