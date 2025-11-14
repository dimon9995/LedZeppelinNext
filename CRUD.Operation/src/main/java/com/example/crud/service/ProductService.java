package com.example.crud.service;

import com.example.crud.product.Product;
import com.example.crud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(String name, Double price) {
        Product product = new Product(name, price);
        return productRepository.save(product);
    }

    public Product update(Long id, String name, Double price) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(name);
        product.setPrice(price);
        return productRepository.save(product);

    }

    public List<Product> readAll() {
        return productRepository.findAll();
    }

    public Optional<Product> getReadId(Long id) {
        return productRepository.findById(id);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }


}
