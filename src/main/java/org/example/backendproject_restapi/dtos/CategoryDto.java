package org.example.backendproject_restapi.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.backendproject_restapi.models.Category;

@Setter
@Getter
public class CategoryDto extends BaseDto {
    private String name;

    private String description;

    public static Category toCategory(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return category;
    }

    public static CategoryDto toCategoryDto(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }

    public static CategoryDto fromCategoryName(String categoryName) {
        if (categoryName == null) {
            return null;
        }
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(categoryName);
        return categoryDto;
    }
}
