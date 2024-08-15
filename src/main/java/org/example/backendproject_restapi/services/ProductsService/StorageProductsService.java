package org.example.backendproject_restapi.services.ProductsService;

import jakarta.validation.constraints.NotNull;
import org.example.backendproject_restapi.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Primary
public class StorageProductsService implements IProductsService {
    @Override
    public Product getById(UUID id) {
        return null;
    }

    @Override
    public List<Product> getAll(
            Optional<Integer> page,
            Optional<Integer> size,
            Optional<String> sortBy,
            Optional<Sort.Direction> sortDirection
    ) {
        return List.of();
    }

    @Override
    public Product save(@Validated @NotNull(message = "Product cannot be null") Product entity) {
        return null;
    }

    @Override
    public Product delete(@Validated @NotNull(message = "Product cannot be null") Product entity) {
        return null;
    }
}
