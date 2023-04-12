package com.example.deliverymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.deliverymanagementsystem.entity.InventoryItem;
import com.example.deliverymanagementsystem.entity.Warehouse;
import com.example.deliverymanagementsystem.exception.WarehouseNotFoundException;
import com.example.deliverymanagementsystem.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    // a. Create a new warehouse
    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    // b. Update warehouse details (name, address, etc.)
    public Warehouse updateWarehouse(Long id, Warehouse updatedWarehouse) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isPresent()) {
            Warehouse warehouse = optionalWarehouse.get();

            // Update the warehouse properties
            warehouse.setName(updatedWarehouse.getName());
            warehouse.setAddress(updatedWarehouse.getAddress());
            // Add any other properties you want to update

            // Save the updated warehouse
            Warehouse savedWarehouse = warehouseRepository.save(warehouse);

            return savedWarehouse;
        } else {
            throw new WarehouseNotFoundException("Warehouse with ID " + id + " not found.");
        }
    }

    public Optional<Warehouse> findWarehouseById(Long id) {
        return warehouseRepository.findById(id);
    }

    // c. Delete a warehouse
    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }

    // d. List all warehouses
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    // e. View the inventory of a specific warehouse
    public Set<InventoryItem> getWarehouseInventory(Long id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isPresent()) {
            Warehouse warehouse = optionalWarehouse.get();
            return warehouse.getInventoryItems();
        } else {
            throw new WarehouseNotFoundException("Warehouse with ID " + id + " not found.");
        }
    }

}
