package com.example.deliverymanagementsystem.controller;

import com.example.deliverymanagementsystem.entity.InventoryItem;
import com.example.deliverymanagementsystem.entity.Product;
import com.example.deliverymanagementsystem.entity.Warehouse;
import com.example.deliverymanagementsystem.exception.ResourceNotFoundException;
import com.example.deliverymanagementsystem.service.InventoryItemService;
import com.example.deliverymanagementsystem.service.ProductService;
import com.example.deliverymanagementsystem.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryItemController {

    @Autowired
    private InventoryItemService inventoryItemService;

    @Autowired
    private ProductService productService;

    @Autowired
    private WarehouseService warehouseService;

    // a. Add or update inventory items for a product in a warehouse
    @PostMapping("/addOrUpdate")
    public ResponseEntity<InventoryItem> addOrUpdateInventoryItem(
            @RequestParam("productId") Long productId,
            @RequestParam("warehouseId") Long warehouseId,
            @RequestParam("quantity") int quantity,
            @RequestParam("minimumQuantity") int minimumQuantity) {

        Product product = productService.findProductById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));

        Warehouse warehouse = warehouseService.findWarehouseById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with ID: " + warehouseId));

        InventoryItem inventoryItem = inventoryItemService.addOrUpdateInventoryItem(product, warehouse, quantity,
                minimumQuantity);
        return new ResponseEntity<>(inventoryItem, HttpStatus.OK);
    }

    // b. Remove inventory items for a product in a warehouse
    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeInventoryItem(@RequestParam("productId") Long productId,
            @RequestParam("warehouseId") Long warehouseId) {
        Product product = productService.findProductById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));

        Warehouse warehouse = warehouseService.findWarehouseById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with ID: " + warehouseId));

        inventoryItemService.removeInventoryItem(product, warehouse);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // c. List inventory items for all warehouses
    @GetMapping("/list")
    public ResponseEntity<List<InventoryItem>> listAllInventoryItems() {
        List<InventoryItem> inventoryItems = inventoryItemService.listAllInventoryItems();
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    // d. List inventory items for a specific warehouse
    @GetMapping("/list/{warehouseId}")
    public ResponseEntity<List<InventoryItem>> listInventoryItemsForWarehouse(
            @PathVariable("warehouseId") Long warehouseId) {
        Warehouse warehouse = warehouseService.findWarehouseById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with ID: " + warehouseId));

        List<InventoryItem> inventoryItems = inventoryItemService.listInventoryItemsForWarehouse(warehouse);
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    // e. View low stock items (items below the minimum quantity) in a specific
    // warehouse
    @GetMapping("/lowStock/{warehouseId}")
    public ResponseEntity<List<InventoryItem>> listLowStockItems(@PathVariable("warehouseId") Long warehouseId) {
        Warehouse warehouse = warehouseService.findWarehouseById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with ID: " + warehouseId));

        List<InventoryItem> inventoryItems = inventoryItemService.listLowStockItems(warehouse);
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }
}
