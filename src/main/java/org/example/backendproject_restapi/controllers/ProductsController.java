package org.example.backendproject_restapi.controllers;

import jakarta.validation.Valid;
import org.example.backendproject_restapi.dtos.ProductDto;
import org.example.backendproject_restapi.dtos.validator.groups.CommonValidation;
import org.example.backendproject_restapi.dtos.validator.groups.OnCreate;
import org.example.backendproject_restapi.dtos.validator.groups.OnReplace;
import org.example.backendproject_restapi.dtos.validator.groups.OnUpdate;
import org.example.backendproject_restapi.mappers.ProductMapper;
import org.example.backendproject_restapi.models.Category;
import org.example.backendproject_restapi.models.Product;
import org.example.backendproject_restapi.services.CategoriesService.StorageCategoriesService;
import org.example.backendproject_restapi.services.ProductsService.StorageProductsService;
import org.example.backendproject_restapi.utils.ResponseEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private StorageProductsService storageProductsService;
    @Autowired
    private StorageCategoriesService storageCategoriesService;

    @GetMapping
    public ResponseEntity<?> getProducts(
            @Valid @RequestParam("page") Optional<Integer> page,
            @Valid @RequestParam("size") Optional<Integer> size,
            @Valid @RequestParam("sortBy") Optional<String> sortBy,
            @Valid @RequestParam("orderBy") Optional<String> orderBy
    ) {
        Assert.isTrue(size.isEmpty() || size.get() <= 100, "Size should be less than or equal to 100");
        Sort.Direction sortDirection = orderBy.map(Sort.Direction::fromString).orElse(Sort.Direction.ASC);
        List<Product> products = this.storageProductsService.getAll(page, size, sortBy, Optional.of(sortDirection));
        List<ProductDto> productDtoList = products.stream().map(this.productMapper::toDto).toList();

        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>(){{
                    put("products", productDtoList);
                }}
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@Valid @PathVariable String id) {
        Product product = this.storageProductsService.getById(id);
        ProductDto productDtoResponse = this.productMapper.toDto(product);

        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>(){{
                    put("product", productDtoResponse);
                }}
        );
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@Validated({OnCreate.class, CommonValidation.class}) @RequestBody ProductDto productDto) {
        Category productCategory = this.storageCategoriesService.getById(productDto.getCategoryId());
        Assert.notNull(productCategory, "Category not found");

        Product productEntity = this.productMapper.createEntity(productDto, productCategory);
        Product product = this.storageProductsService.save(productEntity);
        ProductDto productDtoResponse = this.productMapper.toDto(product);

        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>(){{
                    put("product", productDtoResponse);
                }}
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProduct(@Valid @PathVariable String id, @Validated({OnUpdate.class, CommonValidation.class}) @RequestBody ProductDto productDto) {
        if (productDto.getId() != null) {
            Assert.isTrue(productDto.getId().equals(id), "Product Id mismatch");
        }

        Category productCategory = null;
        if (productDto.getCategoryId() != null) {
            productCategory = this.storageCategoriesService.getById(productDto.getCategoryId());
            Assert.notNull(productCategory, "Category not found");
        }

        Product sourceProduct = this.storageProductsService.getById(id);
        Assert.notNull(sourceProduct, "Product not found");

        productCategory = productCategory == null ? sourceProduct.getCategory() : productCategory;
        Product productEntity = this.productMapper.mergeEntity(productDto, sourceProduct, productCategory);
        Product product = this.storageProductsService.save(productEntity);
        ProductDto productDtoResponse = this.productMapper.toDto(product);

        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>(){{
                    put("product", productDtoResponse);
                }}
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceProduct(@Valid @PathVariable String id, @Validated({OnReplace.class, CommonValidation.class}) @RequestBody ProductDto productDto) {
        if (productDto.getId() != null) {
            Assert.isTrue(productDto.getId().equals(id), "Product Id mismatch");
        }

        Category productCategory = this.storageCategoriesService.getById(productDto.getCategoryId());
        Assert.notNull(productCategory, "Category not found");

        Product sourceProduct = this.storageProductsService.getById(id);
        Assert.notNull(sourceProduct, "Product not found");

        Product productEntity = this.productMapper.replaceEntity(productDto, sourceProduct, productCategory);
        Product product = this.storageProductsService.save(productEntity);
        ProductDto productDtoResponse = this.productMapper.toDto(product);

        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>(){{
                    put("product", productDtoResponse);
                }}
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@Valid @PathVariable String id) {
        Product sourceProduct = this.storageProductsService.getById(id);
        Assert.notNull(sourceProduct, "Product not found");

        Product product = this.storageProductsService.delete(sourceProduct);
        ProductDto productDtoResponse = this.productMapper.toDto(product);

        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>(){{
                    put("product", productDtoResponse);
                }}
        );
    }
}
