package com.ufpso.api.services.servImpl;

import com.ufpso.api.Messages;
import com.ufpso.api.dtos.UpdateCategoryRequestDto;
import com.ufpso.api.exception.AlreadyExists;
import com.ufpso.api.exception.NotFoundException;
import com.ufpso.api.models.Category;
import com.ufpso.api.repository.CategoryRepository;
import com.ufpso.api.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    public final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException(Messages.CATEGORY_NOT_FOUND.getMessage()));
    }

    public List<Category> getAllCategory() {
        return (List<Category>) categoryRepository.findAll();
    }

    public Category createCategory(Category category) {
        this.validateCategory(category.getCategoryName());
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long categoryId, UpdateCategoryRequestDto category) {
        Category category1 = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException(Messages.CATEGORY_NOT_EXISTS.getMessage()));
        Optional<Category> categoryPresent = this.categoryRepository.findByCategoryNameAndCategoryIdNot(category.getCategoryName(), categoryId);

        if(categoryPresent.isPresent()){
            throw new AlreadyExists(Messages.CATEGORY_ALREADY_EXISTS.getMessage());
        }

        category1.updateCategory(category.getCategoryName());
        return categoryRepository.save(category1);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category1 = categoryRepository.findById(categoryId).orElseThrow(() ->  new NotFoundException(Messages.CATEGORY_DELETE_NOT_EXISTS.getMessage()));
        categoryRepository.deleteById(category1.getCategoryId());
    }

    private void validateCategory(String name){
        if (categoryRepository.existsByCategoryName(name)){
            throw new AlreadyExists(Messages.CATEGORY_ALREADY_EXISTS.getMessage());
        }
    }
}
