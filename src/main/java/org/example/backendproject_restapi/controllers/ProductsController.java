package org.example.backendproject_restapi.controllers;

import org.example.backendproject_restapi.enums.SortEnum;
import org.example.backendproject_restapi.models.Product;
import org.example.backendproject_restapi.services.ProductsService.StorageProductsService;
import org.example.backendproject_restapi.utils.ResponseEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    StorageProductsService storageProductsService;

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
                    put("products", storageProductsService.getAll(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
                }}
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable("id") UUID productId) {
        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>(){{
                    put("product", storageProductsService.getById(productId));
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
                    put("product", storageProductsService.save(product));
                }}
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> replaceProduct(@RequestBody Product product, @PathVariable("id") UUID productId) {
        if (product.getId() == null || !product.getId().equals(productId)) {
            throw new IllegalArgumentException("Product Id unmatched");
        }
        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>(){{
                    put("product", storageProductsService.save(product));
                }}
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable("id") UUID productId) {
        if (product.getId() == null || !product.getId().equals(productId)) {
            throw new IllegalArgumentException("Product Id unmatched");
        }
        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>(){{
                    put("product", storageProductsService.save(product));
                }}
        );
    }
}
