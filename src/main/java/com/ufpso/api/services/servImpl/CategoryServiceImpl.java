package com.ufpso.api.services.servImpl;

import com.ufpso.api.dtos.UpdateCategoryRequestDto;
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
        return categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
    }

    @Override
    public List<Category> getAllCategory() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long categoryId, UpdateCategoryRequestDto category) {
        Category category1 = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("La categoria que intentas actualizar no existe"));

        category1.updateCategory(category.getCategoryName());
        return categoryRepository.save(category1);
    }

    @Override
    public void DeleteCategory(Long categoryId) {
        Category category1 = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        categoryRepository.deleteById(category1.getCategoryId());
    }
}
