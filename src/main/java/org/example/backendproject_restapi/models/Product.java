package org.example.backendproject_restapi.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    private String name;

    private String description;

    private double price;

    private int quantity;

    private Category category;
}
