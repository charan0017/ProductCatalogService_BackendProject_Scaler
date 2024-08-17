package org.example.backendproject_restapi.services.ProductsService;

import jakarta.validation.constraints.NotNull;
import org.example.backendproject_restapi.enums.StatusEnum;
import org.example.backendproject_restapi.models.Product;
import org.example.backendproject_restapi.repos.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StorageProductsService implements IProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public Product getById(String id) {
        return this.productsRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAll(
            Optional<Integer> page,
            Optional<Integer> size,
            Optional<String> sortBy,
            Optional<Sort.Direction> sortDirection
    ) {
        // filtering for status
        Product product = new Product();
        product.setStatus(StatusEnum.ACTIVE);
        // Customize the ExampleMatcher to ignore null values and specific fields
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnorePaths("id", "name", "description", "price", "quantity", "imageUrl", "category"); // Ignore other fields
        Example<Product> example = Example.of(product, matcher);

        // implement sorting by name in ascending order
        Sort sort = Sort.by(sortDirection.orElse(Sort.Direction.ASC), sortBy.orElse("name"));

        // implement pagination with limit 10 and offset 0
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10), sort);

        return this.productsRepository.findAll(example, pageable).toList();
    }

    @Override
    public Product save(@Validated @NotNull(message = "Product cannot be null") Product product) {
        return this.productsRepository.save(product);
    }

    @Override
    public Product delete(@Validated @NotNull(message = "Product cannot be null") Product product) {
        product.setStatus(StatusEnum.DELETED);
        return this.save(product);
    }
}
