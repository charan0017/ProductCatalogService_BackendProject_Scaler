package org.example.backendproject_restapi.repos;

import org.example.backendproject_restapi.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, String> {
}
