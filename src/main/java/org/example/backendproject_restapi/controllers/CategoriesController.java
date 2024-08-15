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
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private StorageCategoriesService storageCategoriesService;

    @GetMapping
    public ResponseEntity<Object> getAllCategories(
            @Valid @RequestParam("page") Optional<Integer> page,
            @Valid @RequestParam("size") Optional<Integer> size,
            @Valid @RequestParam("sortBy") Optional<String> sortBy,
            @Valid @RequestParam("orderBy") Optional<String> orderBy
    ) {
        Assert.isTrue(size.isEmpty() || size.get() <= 100, "Size should be less than or equal to 100");
        Sort.Direction sortDirection = orderBy.map(Sort.Direction::fromString).orElse(Sort.Direction.ASC);
        List<Category> categories = storageCategoriesService.getAll(page, size, sortBy, Optional.of(sortDirection));
        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>() {{
                    put("categories", categories.stream().map(categoryMapper::toDto).toList());
                }}
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryById(@Valid @PathVariable UUID id) {
        Category category = storageCategoriesService.getById(id);
        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>() {{
                    put("category", categoryMapper.toDto(category));
                }}
        );
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@Validated({OnCreate.class, CommonValidation.class}) @RequestBody CategoryDto categoryDto) {
        Category categoryEntity = categoryMapper.createEntity(categoryDto);
        Category category = storageCategoriesService.save(categoryEntity);
        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>() {{
                    put("category", categoryMapper.toDto(category));
                }}
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateCategory(@Valid @PathVariable UUID id, @Validated({OnUpdate.class, CommonValidation.class}) @RequestBody CategoryDto categoryDto) throws JsonProcessingException {
        if (categoryDto.getId() != null) {
            Assert.isTrue(categoryDto.getId().equals(id), "Category Id mismatch");
        }

        Category sourceCategory = storageCategoriesService.getById(id);
        Assert.notNull(sourceCategory, "Category not found");

        Category categoryEntity = categoryMapper.mergeEntity(categoryDto, sourceCategory);
        Category category = storageCategoriesService.save(categoryEntity);

        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>() {{
                    put("category", categoryMapper.toDto(category));
                }}
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> replaceCategory(@Valid @PathVariable UUID id, @Validated({OnReplace.class, CommonValidation.class}) @RequestBody CategoryDto categoryDto) {
        if (categoryDto.getId() != null) {
            Assert.isTrue(categoryDto.getId().equals(id), "Category Id mismatch");
        }

        Category sourceCategory = storageCategoriesService.getById(id);
        Assert.notNull(sourceCategory, "Category not found");

        Category categoryEntity = categoryMapper.replaceEntity(categoryDto, sourceCategory);
        Category category = storageCategoriesService.save(categoryEntity);

        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>() {{
                    put("category", categoryMapper.toDto(category));
                }}
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@Valid @PathVariable UUID id) {
        Category sourceCategory = storageCategoriesService.getById(id);
        Assert.notNull(sourceCategory, "Category not found");

        Category category = storageCategoriesService.delete(sourceCategory);

        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>() {{
                    put("category", categoryMapper.toDto(category));
                }}
        );
    }
}
