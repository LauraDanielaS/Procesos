package com.ufpso.api.services.servImpl;

import com.ufpso.api.dtos.UpdateCategoryRequestDto;
import com.ufpso.api.exception.AlreadyExists;
import com.ufpso.api.exception.NotFoundException;
import com.ufpso.api.models.Category;
import com.ufpso.api.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class CategoryServiceImplTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Initialize mocks before each test
    }

    @Test
    public void getCategoryById_shouldReturnCategory_whenCategoryIdExists() {
        // Arrange
        Long categoryId;
        categoryId = 1L;
        Category category = new Category(categoryId, "Electronics");
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        // Act
        Category retrievedCategory = categoryService.getCategoryById(categoryId);

        // Assert
        assertThat(retrievedCategory).isNotNull();
        assertThat(retrievedCategory.getCategoryId()).isEqualTo(categoryId);
        assertThat(retrievedCategory.getCategoryName()).isEqualTo("Electronics");
    }


    @Test
    public void getCategoryById_shouldThrowNotFoundException_whenCategoryIdDoesNotExist() {
        // Arrange
        Long categoryId = 1L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> categoryService.getCategoryById(categoryId));
    }


    @Test
    public void getAllCategories_shouldReturnListOfCategories() {
        // Arrange
        List<Category> categories = Arrays.asList(
                new Category(1L, "Electronics"),
                new Category(2L, "Books")
        );
        when(categoryRepository.findAll()).thenReturn(categories);

        // Act
        List<Category> retrievedCategories = categoryService.getAllCategory();

        // Assert
        assertThat(retrievedCategories).isNotNull();
        assertThat(retrievedCategories.size()).isEqualTo(2);
        assertThat(retrievedCategories).containsAll(categories);
    }

    @Test
    public void createCategory_shouldSaveCategory_whenCategoryNameUnique() {
        // Arrange
        Category category = new Category(null, "Toys");

        // Act
        categoryService.createCategory(category);

        // Verify
        verify(categoryRepository).save(category);
    }

    @Test
    public void createCategory_shouldThrowAlreadyExistsException_whenCategoryNameExists() {
        // Arrange
        Category category = new Category(null, "Electronics");
        when(categoryRepository.existsByCategoryName("Electronics")).thenReturn(true);

        // Act & Assert
        assertThrows(AlreadyExists.class, () -> categoryService.createCategory(category));
    }


    @Test
    public void updateCategory_shouldUpdateAndSaveCategory_whenCategoryIdExistsAndCategoryNameUnique() {
        // Arrange
        Long categoryId = 1L;
        Category existingCategory = new Category(categoryId, "Electronics");
        Category updatedCategory = new Category(categoryId, "Laptops");
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));

        // Act
//        categoryService.updateCategory(categoryId, CategoryRequestDto.builder().categoryName("Laptops").build());
        categoryService.updateCategory(categoryId, UpdateCategoryRequestDto.builder().categoryName(updatedCategory.getCategoryName()).build());

        // Verify
        assertThat(existingCategory.getCategoryName()).isEqualTo("Laptops");
        verify(categoryRepository).save(existingCategory);
    }


    @Test
    public void updateCategory_shouldThrowNotFoundException_whenCategoryIdDoesNotExist() {
        // Arrange
        Long categoryId = 1L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> categoryService.updateCategory(categoryId, UpdateCategoryRequestDto.builder().categoryName("Laptops").build()));
    }

    @Test
    public void updateCategory_shouldThrowAlreadyExistsException_whenCategoryNameExistsForDifferentId() {
        // Arrange
        Long categoryId = 1L;
        Category existingCategory = new Category(categoryId, "Electronics");
        Category updatedCategory = new Category(2L, "Electronics"); // Different ID but same name
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.findByCategoryNameAndCategoryIdNot(updatedCategory.getCategoryName(), categoryId)).thenReturn(Optional.of(new Category(2L, "Electronics")));

        // Act & Assert
        assertThrows(AlreadyExists.class, () -> categoryService.updateCategory(categoryId, UpdateCategoryRequestDto.builder().categoryName("Electronics").build()));
    }


    @Test
    public void deleteCategory_shouldDeleteCategory_whenCategoryIdExists() {
        // Arrange
        Long categoryId = 1L;
        Category category = new Category(categoryId, "Electronics");
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        // Act
        categoryService.deleteCategory(categoryId);
    }
}