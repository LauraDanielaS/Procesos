package com.ufpso.api.controllers;

import com.ufpso.api.dtos.UpdateCategoryRequestDto;
import com.ufpso.api.models.Category;
import com.ufpso.api.services.CategoryService;
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

    /**
     * Traer todos las categorias
     **/
    @GetMapping
    public ResponseEntity<List<Category>> getAllArticles() {
        return ResponseEntity.ok(this.categoryService.getAllCategory());
    }

    /**
     * Traer categoria por Id
     **/
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(this.categoryService.getCategoryById(categoryId));
    }

    /**
     * Guardar categoria.
     **/
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(this.categoryService.createCategory(category));
    }

    /**
     * Actualizar categoria por ID.
     **/
    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategoryById(@PathVariable("categoryId") Long categoryId, @RequestBody UpdateCategoryRequestDto category) {
        return ResponseEntity.ok(this.categoryService.updateCategory(categoryId, category));
    }

    /**
     * Eliminar categoria por ID.
     **/
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable("categoryId") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
