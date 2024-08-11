package org.example.backendproject_restapi.services;

import org.example.backendproject_restapi.models.Category;
import org.example.backendproject_restapi.models.Product;

import java.util.List;

public interface IProductsService {
    public Product getProductById(Long id);

    public default boolean isProductNull(Long id) {
        return getProductById(id) == null;
    }

    public List<Product> getAllProducts(String sort, int limit);

    public Product createProduct(Product product);

    public Product replaceProduct(Product product, Long id);

    public Product updateProduct(Product product, Long id);

    public Product deleteProductById(Long id);

    public List<Category> getAllCategories();

    public List<Product> getProductsByCategoryName(String categoryName);
}
