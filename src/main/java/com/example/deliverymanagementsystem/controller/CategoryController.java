package com.example.deliverymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.deliverymanagementsystem.entity.Category;
import com.example.deliverymanagementsystem.entity.Product;
import com.example.deliverymanagementsystem.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable("categoryId") Long categoryId,
            @RequestBody Category updatedCategory) {
        Category updated = categoryService.updateCategory(categoryId, updatedCategory);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Category>> listAllCategories() {
        List<Category> categories = categoryService.listAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<Product>> listProductsByCategory(@PathVariable("categoryId") Long categoryId) {
        List<Product> products = categoryService.listProductsByCategory(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
