package com.example.deliverymanagementsystem.service;

import com.example.deliverymanagementsystem.entity.InventoryItem;
import com.example.deliverymanagementsystem.entity.Product;
import com.example.deliverymanagementsystem.entity.Warehouse;
import com.example.deliverymanagementsystem.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryItemService {

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    // a. Add or update inventory items for a product in a warehouse
    public InventoryItem addOrUpdateInventoryItem(Product product, Warehouse warehouse, int quantity,
            int minimumQuantity) {
        InventoryItem inventoryItem = inventoryItemRepository.findByProductAndWarehouse(product, warehouse);
        if (inventoryItem == null) {
            inventoryItem = new InventoryItem();
            inventoryItem.setProduct(product);
            inventoryItem.setWarehouse(warehouse);
        }
        inventoryItem.setQuantity(quantity);
        inventoryItem.setMinimumQuantity(minimumQuantity);
        inventoryItem.setLastUpdated(new Date());
        return inventoryItemRepository.save(inventoryItem);
    }

    // b. Remove inventory items for a product in a warehouse
    public void removeInventoryItem(Product product, Warehouse warehouse) {
        InventoryItem inventoryItem = inventoryItemRepository.findByProductAndWarehouse(product, warehouse);
        if (inventoryItem != null) {
            inventoryItemRepository.delete(inventoryItem);
        }
    }

    // c. List inventory items for all warehouses
    public List<InventoryItem> listAllInventoryItems() {
        return inventoryItemRepository.findAll();
    }

    // d. List inventory items for a specific warehouse
    public List<InventoryItem> listInventoryItemsForWarehouse(Warehouse warehouse) {
        return inventoryItemRepository.findByWarehouse(warehouse);
    }

    // e. View low stock items (items below the minimum quantity) in a specific
    // warehouse

    public List<InventoryItem> listLowStockItems(Warehouse warehouse) {
        List<InventoryItem> inventoryItems = inventoryItemRepository.findByWarehouse(warehouse);
        List<InventoryItem> lowStockItems = new ArrayList<>();

        for (InventoryItem item : inventoryItems) {
            if (item.getQuantity() < item.getMinimumQuantity()) {
                lowStockItems.add(item);
            }
        }

        return lowStockItems;
    }
}
