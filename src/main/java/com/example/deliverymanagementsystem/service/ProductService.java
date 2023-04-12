package com.example.deliverymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.deliverymanagementsystem.entity.Category;
import com.example.deliverymanagementsystem.entity.Product;
import com.example.deliverymanagementsystem.repository.CategoryRepository;
import com.example.deliverymanagementsystem.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Product createProduct(Product product, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long productId, Product updatedProduct) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setSKU(updatedProduct.getSKU());
        return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        productRepository.delete(product);
    }

    @Transactional(readOnly = true)
    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Product> searchProducts(String name, String SKU, Long categoryId) {
        List<Product> products = productRepository.findAll();
        if (name != null) {
            products = products.stream()
                    .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (SKU != null) {
            products = products.stream()
                    .filter(product -> product.getSKU().toLowerCase().contains(SKU.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (categoryId != null) {
            products = products.stream()
                    .filter(product -> product.getCategory() != null
                            && product.getCategory().getId().equals(categoryId))
                    .collect(Collectors.toList());
        }
        // Fetch the category information for each product
        for (Product product : products) {
            product.setCategory(categoryRepository.findById(product.getCategory().getId()).orElse(null));
        }
        return products;
    }

    public Optional<Product> findProductById(Long productId) {
        return productRepository.findById(productId);
    }

}
