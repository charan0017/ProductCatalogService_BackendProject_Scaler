package org.example.backendproject_restapi.controllers;

import org.example.backendproject_restapi.models.Product;
import org.springframework.web.bind.annotation.*;

@RestController
public class Products {

    @GetMapping("/products")
    public Products[] getProducts() {
        return null;
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") Long productId) {
        Product product = new Product();
        product.setId(productId);
        return product;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return product;
    }
}
