package org.example.backendproject_restapi.services.CategoriesService;

import jakarta.validation.constraints.NotNull;
import org.example.backendproject_restapi.enums.StatusEnum;
import org.example.backendproject_restapi.models.Category;
import org.example.backendproject_restapi.repos.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StorageCategoriesService implements ICategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public Category getById(String id) {
        return this.categoriesRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> getAll(
            Optional<Integer> page,
            Optional<Integer> size,
            Optional<String> sortBy,
            Optional<Sort.Direction> sortDirection
    ) {
        // filtering for status
        Category category = new Category();
        category.setStatus(StatusEnum.ACTIVE);
        // Customize the ExampleMatcher to ignore null values and specific fields
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnorePaths("id", "name", "description"); // Ignore other fields
        Example<Category> example = Example.of(category, matcher);

        // implement sorting by name in ascending order
        Sort sort = Sort.by(sortDirection.orElse(Sort.Direction.ASC), sortBy.orElse("name"));

        // implement pagination with limit 10 and offset 0
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10), sort);

        return this.categoriesRepository.findAll(example, pageable).toList();
    }

    @Override
    public Category save(@Validated @NotNull(message = "Category cannot be null") Category category) {
        return this.categoriesRepository.save(category);
    }

    @Override
    public Category delete(@Validated @NotNull(message = "Category cannot be null") Category category) {
        category.setStatus(StatusEnum.DELETED);
        return this.save(category);
    }
}
