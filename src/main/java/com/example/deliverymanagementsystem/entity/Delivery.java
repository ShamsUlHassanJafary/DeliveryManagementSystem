package com.example.deliverymanagementsystem.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "deliveries")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @Column(nullable = false)
    private Date deliveryDate;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String trackingNumber;

    // Getters and setters
}
