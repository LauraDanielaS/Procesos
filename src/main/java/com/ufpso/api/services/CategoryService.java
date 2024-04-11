package com.ufpso.api.services;

import com.ufpso.api.dtos.UpdateCategoryRequestDto;
import com.ufpso.api.models.Category;

import java.util.List;

public interface CategoryService {

    Category getCategoryById(Long categoryId);

    List<Category> getAllCategory();

    Category createCategory(Category category);

    Category updateCategory(Long categoryId, UpdateCategoryRequestDto category);

    void deleteCategory(Long categoryId);
}
