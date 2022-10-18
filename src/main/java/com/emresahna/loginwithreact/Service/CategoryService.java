package com.emresahna.loginwithreact.Service;

import com.emresahna.loginwithreact.Dto.CategoryRequest;
import com.emresahna.loginwithreact.Entity.Category;
import com.emresahna.loginwithreact.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category create(CategoryRequest category) {
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setType(category.getType());
        newCategory.setColor(category.getColor());
        return categoryRepository.save(newCategory);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category update(Long categoryId, CategoryRequest category) {
        Category updatedCategory = categoryRepository.findById(categoryId).get();
        updatedCategory.setName(category.getName());
        updatedCategory.setType(category.getType());
        updatedCategory.setColor(category.getColor());
        return categoryRepository.save(updatedCategory);
    }

    public Category delete(Long categoryId) {
        Category deletedCategory = categoryRepository.findById(categoryId).get();
        categoryRepository.delete(deletedCategory);
        return deletedCategory;
    }
}
