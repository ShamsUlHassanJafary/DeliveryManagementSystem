package com.example.deliverymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.deliverymanagementsystem.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
