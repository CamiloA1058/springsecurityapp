package com.rangotech.springsecurityapp.controller;

import com.rangotech.springsecurityapp.persistence.entity.Category;
import com.rangotech.springsecurityapp.service.ICategoryService;
import com.rangotech.springsecurityapp.service.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @PostMapping("/admin/category")
    public ResponseEntity<CategoryDto> save(@RequestBody Category category){
        CategoryDto savedCategory = categoryService.save(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }
    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody Category category,
                                                      @PathVariable Long categoryId) {
        CategoryDto categoryDTO = categoryService.updateCategory(category, categoryId);

        return new ResponseEntity<CategoryDto>(categoryDTO, HttpStatus.OK);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        String status = categoryService.deleteCategory(categoryId);

        return new ResponseEntity<String>(status, HttpStatus.OK);
    }
}
