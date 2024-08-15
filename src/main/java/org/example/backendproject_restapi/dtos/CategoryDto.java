package org.example.backendproject_restapi.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.example.backendproject_restapi.dtos.validator.groups.CommonValidation;
import org.example.backendproject_restapi.dtos.validator.groups.OnCreate;
import org.example.backendproject_restapi.dtos.validator.groups.OnReplace;

@Getter
@Setter
public class CategoryDto extends BaseDto {
    @Valid

    @NotNull(groups = {OnCreate.class, OnReplace.class}, message = "Category name is mandatory")
    @NotBlank(groups = {OnCreate.class, OnReplace.class}, message = "Category name is mandatory")
    @Size(min = 3, max = 100, message = "Category name should be between 3 and 100 characters")
    private String name;

    @Size(groups = CommonValidation.class, max = 2000, message = "Category description should have at most 2000 characters")
    private String description;
}
