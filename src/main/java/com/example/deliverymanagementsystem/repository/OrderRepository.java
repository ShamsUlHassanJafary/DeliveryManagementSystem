package com.example.deliverymanagementsystem.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.deliverymanagementsystem.entity.Customer;
import com.example.deliverymanagementsystem.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(Customer customer);

    List<Order> findByOrderDateBetween(Date startDate, Date endDate);

    List<Order> findByStatus(String status);
}
