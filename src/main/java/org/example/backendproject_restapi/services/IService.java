package org.example.backendproject_restapi.services;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    public T getById(String id);

    default public boolean isExists(String id) {
        return getById(id) != null;
    }

    public List<T> getAll(
            Optional<Integer> page,
            Optional<Integer> size,
            Optional<String> sortBy,
            Optional<Sort.Direction> sortDirection
    );

    public T save(@Validated @NotNull(message = "Entity cannot be null") T entity);

    public T delete(@Validated @NotNull(message = "Entity cannot be null") T entity);
}
