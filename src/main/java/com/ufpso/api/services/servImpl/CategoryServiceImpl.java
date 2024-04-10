package com.ufpso.api.services.servImpl;

import com.ufpso.api.models.Category;
import com.ufpso.api.repository.CategoryRepository;
import com.ufpso.api.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    public final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return this.categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
    }

    @Override
    public List<Category> getAllCategory() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        return null;
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {
        return null;
    }
}
