package com.ufpso.api.controllers;

import com.ufpso.api.enums.Messages;
import com.ufpso.api.dtos.GenericRequest;
import com.ufpso.api.dtos.GenericResponse;
import com.ufpso.api.dtos.Response;
import com.ufpso.api.dtos.UpdateCategoryRequestDto;
import com.ufpso.api.models.Category;
import com.ufpso.api.services.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public GenericResponse<List<Category>> getAllArticles() {
        return new GenericResponse<>(new Response<>(this.categoryService.getAllCategory(), HttpStatus.OK.value()));
    }

    @GetMapping("/{categoryId}")
    public GenericResponse<Category> getCategoryById(@PathVariable("categoryId") @Positive Long categoryId) {
        return new GenericResponse<>(new Response<>(this.categoryService.getCategoryById(categoryId), HttpStatus.OK.value()));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<?>> createCategory(@RequestBody @Valid GenericRequest<Category> category) {
        this.categoryService.createCategory(category.requestMessage().getData());
        GenericResponse<?> response = new GenericResponse<>(new Response<>(Messages.CATEGORY_CREATED.getMessage(), HttpStatus.CREATED.value()));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public GenericResponse<Category> updateCategoryById(@PathVariable("categoryId") @Positive Long categoryId, @RequestBody @Valid GenericRequest<UpdateCategoryRequestDto>  category) {
        return new GenericResponse<>(new Response<>(this.categoryService.updateCategory(categoryId, category.requestMessage().getData()), HttpStatus.OK.value()));
    }

    @DeleteMapping("/{categoryId}")
    public GenericResponse<?> deleteCategoryById(@PathVariable("categoryId") @Positive Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new GenericResponse<>(new Response<>(Messages.ARTICLE_DELETE.getMessage(), HttpStatus.OK.value()));
    }
}
