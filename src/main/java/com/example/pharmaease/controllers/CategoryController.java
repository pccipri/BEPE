package com.example.pharmaease.controllers;

import com.example.pharmaease.models.Category;
import com.example.pharmaease.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    Category getCategoryById(@PathVariable Integer id) {

        return categoryRepository.findById(id)
                .orElseThrow();
    }

    @PostMapping
    Category newCategory(@RequestBody Category newCategory) {
        return categoryRepository.save(newCategory);
    }

    @PutMapping("/{id}")
    Category replaceCategory(@RequestBody Category newCategory, @PathVariable Integer id) {

        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(newCategory.getName());
                    return categoryRepository.save(category);
                })
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    void deleteCategory(@PathVariable Integer id) {
        categoryRepository.deleteById(id);
    }

}
