package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public Category addCategory(Category category) {
    return categoryRepository.save(category);
  }

  public List<Category> getUserCategories(Long userId) {
    return categoryRepository.findByUserId(userId);
  }

  public Category updateCategory(Long categoryId, Category categoryDetails) {
    Optional<Category> existingCategory = categoryRepository.findById(categoryId);
    if (existingCategory.isPresent()) {
      Category category = existingCategory.get();
      category.setName(categoryDetails.getName()); // Update other fields as needed
      return categoryRepository.save(category);
    } else {
      throw new RuntimeException("Category not found with id: " + categoryId);
    }
  }

  public void deleteCategory(Long categoryId) {
    if (categoryRepository.existsById(categoryId)) {
      categoryRepository.deleteById(categoryId);
    } else {
      throw new RuntimeException("Category not found with id: " + categoryId);
    }
  }
}