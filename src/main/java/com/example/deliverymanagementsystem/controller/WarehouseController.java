package com.example.deliverymanagementsystem.controller;

import com.example.deliverymanagementsystem.entity.InventoryItem;
import com.example.deliverymanagementsystem.entity.Warehouse;
import com.example.deliverymanagementsystem.exception.ResourceNotFoundException;
import com.example.deliverymanagementsystem.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        Warehouse createdWarehouse = warehouseService.createWarehouse(warehouse);
        return new ResponseEntity<>(createdWarehouse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Long id, @RequestBody Warehouse updatedWarehouse) {
        Warehouse updated = warehouseService.updateWarehouse(id, updatedWarehouse);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        return new ResponseEntity<>(warehouses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {
        Warehouse warehouse = warehouseService.findWarehouseById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with ID: " + id));
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

    @GetMapping("/{id}/inventory")
    public ResponseEntity<Set<InventoryItem>> getInventoryByWarehouseId(@PathVariable Long id) {
        Set<InventoryItem> inventoryItems = warehouseService.getWarehouseInventory(id);
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }
}
