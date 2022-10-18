package com.emresahna.loginwithreact.Controller;

import com.emresahna.loginwithreact.Dto.CategoryRequest;
import com.emresahna.loginwithreact.Entity.Category;
import com.emresahna.loginwithreact.Service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequest category){
        Category returnedCategory = categoryService.create(category);
        return new ResponseEntity<>(returnedCategory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategories(){
        List<Category> returnedCategoryList = categoryService.getAll();
        return new ResponseEntity<>(returnedCategoryList, HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequest category){
        Category updatedCategory = categoryService.update(categoryId,category);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long categoryId){
        Category deletedCategory = categoryService.delete(categoryId);
        return new ResponseEntity<>(deletedCategory, HttpStatus.OK);
    }
}
