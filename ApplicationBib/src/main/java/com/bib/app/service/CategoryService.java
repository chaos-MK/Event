package com.bib.app.service;

import com.bib.app.entities.Category;
import com.bib.app.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(String id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> updateCategory(String id, Category categoryDetails) {
        return categoryRepository.findById(id)
                .map(existing -> {
                    existing.setType(categoryDetails.getType());
                    return categoryRepository.save(existing);
                });
    }

    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }
}