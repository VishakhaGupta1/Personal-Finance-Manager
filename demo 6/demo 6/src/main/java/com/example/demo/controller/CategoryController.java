package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @PostMapping
  public ResponseEntity<Category> addCategory(@RequestBody Category category) {
    return ResponseEntity.ok(categoryService.addCategory(category));
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Category>> getUserCategories(@PathVariable Long userId) {
    return ResponseEntity.ok(categoryService.getUserCategories(userId));
  }

  @PutMapping("/{categoryId}")
  public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId, @RequestBody Category categoryDetails) {
    return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryDetails));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
    return ResponseEntity.ok().build();
  }
}
