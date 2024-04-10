package com.ufpso.api.repository;

import com.ufpso.api.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
