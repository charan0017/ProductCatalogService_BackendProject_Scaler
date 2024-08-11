package org.example.backendproject_restapi.services;

import org.example.backendproject_restapi.api_clients.FakeStoreApiClient;
import org.example.backendproject_restapi.dtos.CategoryDto;
import org.example.backendproject_restapi.dtos.ProductDto;
import org.example.backendproject_restapi.models.Category;
import org.example.backendproject_restapi.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeStoreProductsService implements IProductsService {
    @Autowired
    private FakeStoreApiClient fakeStoreApiClient;

    public Product getProductById(Long id) {
        return ProductDto.toProduct(
                this.fakeStoreApiClient.getProductById(id)
        );
    }

    public List<Product> getAllProducts(String sort, int limit) {
        return List.of(
                this.fakeStoreApiClient
                        .getAllProducts(sort, limit)
                        .stream()
                        .map(ProductDto::toProduct)
                        .toArray(Product[]::new)
        );
    }

    public Product createProduct(Product product) {
        return ProductDto.toProduct(
                this.fakeStoreApiClient.createProduct(ProductDto.toProductDto(product))
        );
    }

    public Product replaceProduct(Product product, Long id) {
        if (this.isProductNull(id)) {
            throw new IllegalArgumentException("Product not found");
        }
        return ProductDto.toProduct(
                this.fakeStoreApiClient.replaceProduct(ProductDto.toProductDto(product), id)
        );
    }

    public Product updateProduct(Product product, Long id) {
        if (this.isProductNull(id)) {
            throw new IllegalArgumentException("Product not found");
        }
        return ProductDto.toProduct(
                this.fakeStoreApiClient.updateProduct(ProductDto.toProductDto(product), id)
        );
    }

    public Product deleteProductById(Long id) {
        if (this.isProductNull(id)) {
            throw new IllegalArgumentException("Product not found");
        }
        return ProductDto.toProduct(
                this.fakeStoreApiClient.deleteProductById(id)
        );
    }

    public List<Category> getAllCategories() {
        return List.of(
                this.fakeStoreApiClient
                        .getAllCategories()
                        .stream()
                        .map(CategoryDto::toCategory)
                        .toArray(Category[]::new)
        );
    }

    public List<Product> getProductsByCategoryName(String categoryName) {
        return List.of(
                this.fakeStoreApiClient
                        .getProductsByCategoryName(categoryName)
                        .stream()
                        .map(ProductDto::toProduct)
                        .toArray(Product[]::new)
        );
    }
}
