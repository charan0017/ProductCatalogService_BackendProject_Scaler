package org.example.backendproject_restapi.controllers;

import org.example.backendproject_restapi.enums.SortEnum;
import org.example.backendproject_restapi.models.Product;
import org.example.backendproject_restapi.services.FakeStoreProductsService;
import org.example.backendproject_restapi.utils.ResponseEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    FakeStoreProductsService fakeStoreProductsService;

    @GetMapping
    public ResponseEntity<Object> getProducts(
            @RequestParam(required = false, defaultValue = "asc") String sort,
            @RequestParam(required = false, defaultValue = "10") String limit
    ) {
        SortEnum sortEnum = SortEnum.fromString(sort);
        if (sortEnum == null) {
            throw new IllegalArgumentException("Sort is invalid");
        }
        int limitValue = Optional.ofNullable(limit).map(Integer::parseInt).orElse(10);
        if (limitValue <= 0 || limitValue > 100) {
            throw new IllegalArgumentException("Limit is invalid");
        }
        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>(){{
                    put("products", fakeStoreProductsService.getAllProducts(sort, limitValue));
                }}
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable("id") Long productId) {
        if (productId <= 0) {
            throw new IllegalArgumentException("Product Id is invalid");
        }
        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>(){{
                    put("product", fakeStoreProductsService.getProductById(productId));
                }}
        );
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException("Product Id should not be provided");
        }
        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>(){{
                    put("product", fakeStoreProductsService.createProduct(product));
                }}
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> replaceProduct(@RequestBody Product product, @PathVariable("id") Long productId) {
        if (productId <= 0) {
            throw new IllegalArgumentException("Product Id is invalid");
        }
        if (product.getId() == null || !product.getId().equals(productId)) {
            throw new IllegalArgumentException("Product Id unmatched");
        }
        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>(){{
                    put("product", fakeStoreProductsService.replaceProduct(product, productId));
                }}
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable("id") Long productId) {
        if (productId <= 0) {
            throw new IllegalArgumentException("Product Id is invalid");
        }
        if (product.getId() == null || !product.getId().equals(productId)) {
            throw new IllegalArgumentException("Product Id unmatched");
        }
        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>(){{
                    put("product", fakeStoreProductsService.updateProduct(product, productId));
                }}
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long productId) {
        if (productId <= 0) {
            throw new IllegalArgumentException("Product Id is invalid");
        }
        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>(){{
                    put("product", fakeStoreProductsService.deleteProductById(productId));
                }}
        );
    }

    @GetMapping("/categories")
    public ResponseEntity<Object> getCategories() {
        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>(){{
                    put("categories", fakeStoreProductsService.getAllCategories());
                }}
        );
    }

    @GetMapping("/categories/{categoryName}")
    public ResponseEntity<Object> getProductsByCategoryName(@PathVariable("categoryName") String categoryName) {
        if (categoryName == null || categoryName.isBlank()) {
            throw new IllegalArgumentException("Category Name is invalid");
        }
        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>(){{
                    put("products", fakeStoreProductsService.getProductsByCategoryName(categoryName));
                }}
        );
    }
}
