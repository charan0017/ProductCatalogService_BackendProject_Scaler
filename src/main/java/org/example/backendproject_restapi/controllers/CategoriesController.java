package org.example.backendproject_restapi.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.example.backendproject_restapi.dtos.CategoryDto;
import org.example.backendproject_restapi.dtos.validator.groups.CommonValidation;
import org.example.backendproject_restapi.dtos.validator.groups.OnCreate;
import org.example.backendproject_restapi.dtos.validator.groups.OnReplace;
import org.example.backendproject_restapi.dtos.validator.groups.OnUpdate;
import org.example.backendproject_restapi.mappers.CategoryMapper;
import org.example.backendproject_restapi.models.Category;
import org.example.backendproject_restapi.services.CategoriesService.StorageCategoriesService;
import org.example.backendproject_restapi.utils.ResponseEntityUtil;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private StorageCategoriesService storageCategoriesService;

    @GetMapping
    public ResponseEntity<?> getAllCategories(
            @Valid @RequestParam("page") Optional<Integer> page,
            @Valid @RequestParam("size") Optional<Integer> size,
            @Valid @RequestParam("sortBy") Optional<String> sortBy,
            @Valid @RequestParam("orderBy") Optional<String> orderBy
    ) {
        Assert.isTrue(size.isEmpty() || size.get() <= 100, "Size should be less than or equal to 100");
        Sort.Direction sortDirection = orderBy.map(Sort.Direction::fromString).orElse(Sort.Direction.ASC);
        List<Category> categories = this.storageCategoriesService.getAll(page, size, sortBy, Optional.of(sortDirection));
        List<CategoryDto> categoryDtoList = categories.stream().map(this.categoryMapper::toDto).toList();

        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>() {{
                    put("categories", categoryDtoList);
                }}
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@Valid @PathVariable String id) {
        Category category = this.storageCategoriesService.getById(id);
        CategoryDto categoryDtoResponse = this.categoryMapper.toDto(category);

        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>() {{
                    put("category", categoryDtoResponse);
                }}
        );
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@Validated({OnCreate.class, CommonValidation.class}) @RequestBody CategoryDto categoryDto) {
        Category categoryEntity = this.categoryMapper.createEntity(categoryDto);
        Category category = this.storageCategoriesService.save(categoryEntity);
        CategoryDto categoryDtoResponse = this.categoryMapper.toDto(category);

        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>() {{
                    put("category", categoryDtoResponse);
                }}
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCategory(@Valid @PathVariable String id, @Validated({OnUpdate.class, CommonValidation.class}) @RequestBody CategoryDto categoryDto) throws JsonProcessingException {
        if (categoryDto.getId() != null) {
            Assert.isTrue(categoryDto.getId().equals(id), "Category Id mismatch");
        }

        Category sourceCategory = this.storageCategoriesService.getById(id);
        Assert.notNull(sourceCategory, "Category not found");

        Category categoryEntity = this.categoryMapper.mergeEntity(categoryDto, sourceCategory);
        Category category = this.storageCategoriesService.save(categoryEntity);
        CategoryDto categoryDtoResponse = this.categoryMapper.toDto(category);

        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>() {{
                    put("category", categoryDtoResponse);
                }}
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceCategory(@Valid @PathVariable String id, @Validated({OnReplace.class, CommonValidation.class}) @RequestBody CategoryDto categoryDto) {
        if (categoryDto.getId() != null) {
            Assert.isTrue(categoryDto.getId().equals(id), "Category Id mismatch");
        }

        Category sourceCategory = this.storageCategoriesService.getById(id);
        Assert.notNull(sourceCategory, "Category not found");

        Category categoryEntity = this.categoryMapper.replaceEntity(categoryDto, sourceCategory);
        Category category = this.storageCategoriesService.save(categoryEntity);
        CategoryDto categoryDtoResponse = this.categoryMapper.toDto(category);

        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>() {{
                    put("category", categoryDtoResponse);
                }}
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@Valid @PathVariable String id) {
        Category sourceCategory = this.storageCategoriesService.getById(id);
        Assert.notNull(sourceCategory, "Category not found");

        Category category = this.storageCategoriesService.delete(sourceCategory);
        CategoryDto categoryDtoResponse = this.categoryMapper.toDto(category);

        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>() {{
                    put("category", categoryDtoResponse);
                }}
        );
    }
}
