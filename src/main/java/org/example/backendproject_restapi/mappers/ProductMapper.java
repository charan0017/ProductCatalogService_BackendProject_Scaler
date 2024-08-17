package org.example.backendproject_restapi.mappers;

import org.example.backendproject_restapi.dtos.ProductDto;
import org.example.backendproject_restapi.models.Category;
import org.example.backendproject_restapi.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends EntityMapper<ProductDto, Product> {
    @Override
    public Product toEntity(ProductDto dto) {
        Product product = super.toEntity(dto);
        product.setId(null);
        return product;
    }

    @Override
    public ProductDto toDto(Product entity) {
        ProductDto productDto = super.toDto(entity);
        if (entity.getCategory() != null) {
            productDto.setCategoryId(entity.getCategory().getId());
        }
        return productDto;
    }

    public Product createEntity(ProductDto dto, Category productCategory) {
        Product product = super.createEntity(dto);
        product.setId(null);
        product.setCategory(productCategory);
        return product;
    }

    public Product mergeEntity(ProductDto dto, Product product, Category productCategory) {
        Product resultProduct = super.mergeEntity(dto, product);
        resultProduct.setId(product.getId());
        resultProduct.setCreatedAt(product.getCreatedAt());
        resultProduct.setCategory(productCategory);
        return resultProduct;
    }

    public Product replaceEntity(ProductDto dto, Product product, Category productCategory) {
        Product resultProduct = super.replaceEntity(dto, product);
        resultProduct.setId(product.getId());
        resultProduct.setCreatedAt(product.getCreatedAt());
        resultProduct.setCategory(productCategory);
        return resultProduct;
    }
}
