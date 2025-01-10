package Finance.demo.service;

import Finance.demo.models.Category;
import Finance.demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  public Category addCategory(Category category) {
    return categoryRepository.save(category);
  }

  public List<Category> getCategories() {
    return categoryRepository.findAll();
  }

  public void deleteCategory(Long id) {
    categoryRepository.deleteById(id);
  }
}
