package org.example.backendproject_restapi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto extends BaseDto {
    private String title;

    private Double price;

    private String description;

    private String category;

    private String image;

    public static ProductDto toProductDto(FakeStoreProductDto fakeStoreProductDto) {
        if (fakeStoreProductDto == null) {
            return null;
        }
        ProductDto productDto = new ProductDto();
        productDto.setId(fakeStoreProductDto.getId());
        productDto.setName(fakeStoreProductDto.getTitle());
        productDto.setPrice(fakeStoreProductDto.getPrice());
        productDto.setDescription(fakeStoreProductDto.getDescription());
        productDto.setImageUrl(fakeStoreProductDto.getImage());
        if (fakeStoreProductDto.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(fakeStoreProductDto.getCategory());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }

    public static FakeStoreProductDto toFakeStoreProductDto(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(productDto.getId());
        fakeStoreProductDto.setTitle(productDto.getName());
        fakeStoreProductDto.setPrice(productDto.getPrice());
        fakeStoreProductDto.setDescription(productDto.getDescription());
        fakeStoreProductDto.setImage(productDto.getImageUrl());
        if (productDto.getCategory() != null) {
            fakeStoreProductDto.setCategory(productDto.getCategory().getName());
        }
        return fakeStoreProductDto;
    }
}
