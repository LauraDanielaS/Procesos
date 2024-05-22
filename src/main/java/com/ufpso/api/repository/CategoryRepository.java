package com.ufpso.api.repository;

import com.ufpso.api.models.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    boolean existsByCategoryName(String name);

    Optional<Category> findByCategoryNameAndCategoryIdNot(String categoryName, Long CategoryId);

}
