package org.example.backendproject_restapi.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.backendproject_restapi.models.Product;

@Setter
@Getter
public class ProductDto extends BaseDto {
    private String name;

    private String description;

    private Double price;

    private String imageUrl;

    private CategoryDto category;

    public static Product toProduct(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setCategory(CategoryDto.toCategory(productDto.getCategory()));
        return product;
    }

    public static ProductDto toProductDto(Product product) {
        if (product == null) {
            return null;
        }
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setCategory(CategoryDto.toCategoryDto(product.getCategory()));
        return productDto;
    }
}
