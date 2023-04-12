package com.example.deliverymanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.deliverymanagementsystem.entity.InventoryItem;
import com.example.deliverymanagementsystem.entity.Product;
import com.example.deliverymanagementsystem.entity.Warehouse;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    InventoryItem findByProductAndWarehouse(Product product, Warehouse warehouse);

    List<InventoryItem> findByWarehouse(Warehouse warehouse);
}
