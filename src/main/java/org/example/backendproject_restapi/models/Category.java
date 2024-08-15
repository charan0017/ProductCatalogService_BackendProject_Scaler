package org.example.backendproject_restapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    @Column(length = 100)
    @NotBlank(message = "Category name is mandatory")
    private String name;

    @Column(length = 2000)
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @Override
    public Category clone() {
        Category category = (Category) super.clone();
        category.setProducts(
                Optional
                        .ofNullable(this.getProducts())
                        .map(products -> products.stream()
                                .map(Product::clone)
                                .toList())
                        .orElse(List.of())
        );
        return category;
    }
}
