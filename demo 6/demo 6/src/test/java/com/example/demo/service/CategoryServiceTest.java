package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CategoryServiceTest {

  @Mock
  private CategoryRepository categoryRepository;

  @InjectMocks
  private CategoryService categoryService;

  private Category category;

  @BeforeEach
  public void setUp() {
    category = new Category();
    category.setId(1L);
    category.setName("Groceries");
    category.setDescription("Expenses related to food");
  }

  @Test
  public void testAddCategory() {
    when(categoryRepository.save(category)).thenReturn(category);

    Category result = categoryService.addCategory(category);

    assertNotNull(result);
    assertEquals("Groceries", result.getName());
    verify(categoryRepository, times(1)).save(category);
  }

  @Test
  public void testGetUserCategories() {
    when(categoryRepository.findByUserId(1L)).thenReturn(List.of(category));

    List<Category> result = categoryService.getUserCategories(1L);

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals("Groceries", result.get(0).getName());
    verify(categoryRepository, times(1)).findByUserId(1L);
  }

  @Test
  public void testUpdateCategory_NotFound() {
    when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
      categoryService.updateCategory(1L, category);
    });
    assertEquals("Category not found with id: 1", thrown.getMessage());
    verify(categoryRepository, never()).save(any());
  }

  @Test
  public void testDeleteCategory() {
    when(categoryRepository.existsById(1L)).thenReturn(true);

    categoryService.deleteCategory(1L);

    verify(categoryRepository, times(1)).deleteById(1L);
  }

  @Test
  public void testDeleteCategory_NotFound() {
    when(categoryRepository.existsById(1L)).thenReturn(false);

    RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
      categoryService.deleteCategory(1L);
    });
    assertEquals("Category not found with id: 1", thrown.getMessage());
    verify(categoryRepository, never()).deleteById(any());
  }
}
