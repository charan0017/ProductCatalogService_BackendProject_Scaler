package org.example.backendproject_restapi.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    @Column(length = 500)
    @NotBlank(message = "Product name is mandatory")
    private String name;

    @Column(length = 10000)
    private String description;

    private Double price;

    @URL(message = "Product image url is in invalid format")
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    @Override
    public Product clone() {
        return (Product) super.clone();
    }
}
