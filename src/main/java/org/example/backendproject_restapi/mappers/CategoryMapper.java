package org.example.backendproject_restapi.mappers;

import org.example.backendproject_restapi.dtos.CategoryDto;
import org.example.backendproject_restapi.models.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper extends EntityMapper<CategoryDto, Category> {
    public Category createEntity(CategoryDto categoryDto) {
        Category category = super.createEntity(categoryDto);
        category.setId(null);
        return category;
    }

    public Category replaceEntity(CategoryDto categoryDto, Category category) {
        Category resultCategory = super.replaceEntity(categoryDto, category);
        resultCategory.setId(category.getId());
        resultCategory.setCreatedAt(category.getCreatedAt());
        return resultCategory;
    }
}
