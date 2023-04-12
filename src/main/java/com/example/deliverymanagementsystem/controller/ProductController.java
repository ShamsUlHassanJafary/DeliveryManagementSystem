package com.example.deliverymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.deliverymanagementsystem.entity.Product;
import com.example.deliverymanagementsystem.service.ProductService;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestParam("categoryId") Long categoryId,
            @Valid @RequestBody Product product) {
        Product createdProduct = productService.createProduct(product, categoryId);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId,
            @RequestBody Product updatedProduct) {
        Product updated = productService.updateProduct(productId, updatedProduct);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Product>> listAllProducts() {
        List<Product> products = productService.listAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "SKU", required = false) String SKU,
            @RequestParam(value = "categoryId", required = false) Long categoryId) {
        List<Product> products = productService.searchProducts(name, SKU, categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
