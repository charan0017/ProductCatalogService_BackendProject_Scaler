package org.example.backendproject_restapi.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDto extends BaseDto {
    private String name;

    private String description;

    private Double price;

    private String imageUrl;

    private CategoryDto category;
}
