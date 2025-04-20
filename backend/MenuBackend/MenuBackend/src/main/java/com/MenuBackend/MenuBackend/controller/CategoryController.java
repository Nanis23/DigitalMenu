package com.MenuBackend.MenuBackend.controller;

import com.MenuBackend.MenuBackend.DTO.CategoryDTO;
import com.MenuBackend.MenuBackend.services.Category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/createCategory")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) {
        if (categoryService.categoryNameExist(categoryDTO.getCatName())) {
            return new ResponseEntity<>("Category already exists!", HttpStatus.NOT_ACCEPTABLE);
        }

        CategoryDTO createdCategoryDTO = categoryService.createCategory(categoryDTO);

        if (createdCategoryDTO == null) {
            return new ResponseEntity<>("Category not created  try again!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdCategoryDTO, HttpStatus.CREATED);
    }

    @PutMapping("/updateCategory/{catId}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Long catId) {
        try {
            CategoryDTO updatedCategoryDTO = categoryService.updateCategory(catId, categoryDTO);
            return new ResponseEntity<>(updatedCategoryDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/deleteCategory/{catId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long catId) {
        categoryService.deleteCategory(catId);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/categoryList")
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/category/{catId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long catId) {
        CategoryDTO categoryDTO = categoryService.getCategoryById(catId);
        return ResponseEntity.ok(categoryDTO);
    }
}
