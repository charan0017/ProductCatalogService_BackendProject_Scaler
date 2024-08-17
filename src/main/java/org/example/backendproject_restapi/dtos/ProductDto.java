package org.example.backendproject_restapi.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.example.backendproject_restapi.dtos.validator.groups.CommonValidation;
import org.example.backendproject_restapi.dtos.validator.groups.OnCreate;
import org.example.backendproject_restapi.dtos.validator.groups.OnReplace;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto extends BaseDto {
    @Valid

    @NotNull(groups = {OnCreate.class, OnReplace.class}, message = "Product name is mandatory")
    @NotBlank(groups = {OnCreate.class, OnReplace.class}, message = "Product name is mandatory")
    @Size(min = 3, max = 500, groups = CommonValidation.class, message = "Product name should be between 3 and 500 characters")
    private String name;

    @Size(max = 10000, groups = CommonValidation.class, message = "Product description should have at most 10000 characters")
    private String description;

    @NotNull(groups = {OnCreate.class, OnReplace.class}, message = "Product price is mandatory")
    @PositiveOrZero(groups = CommonValidation.class, message = "Product price should be a positive or zero number")
    @Digits(integer = 19, fraction = 2, groups = CommonValidation.class, message = "Product price should have at most 19 digits and 2 fractions")
    private BigDecimal price;

    @NotNull(groups = {OnCreate.class, OnReplace.class}, message = "Product quantity is mandatory")
    @PositiveOrZero(groups = CommonValidation.class, message = "Product quantity should be a positive number")
    private Integer quantity;

    @URL(groups = CommonValidation.class, message = "Product imageUrl is in invalid format")
    private String imageUrl;

    @NotNull(groups = {OnCreate.class, OnReplace.class}, message = "Product categoryId is mandatory")
    @NotBlank(groups = {OnCreate.class, OnReplace.class}, message = "Product categoryId is mandatory")
    @UUID(groups = CommonValidation.class, message = "Product categoryId is in invalid format")
    private String categoryId;

    @Null(groups = CommonValidation.class, message = "Product category should not be provided")
    private CategoryDto category;
}
