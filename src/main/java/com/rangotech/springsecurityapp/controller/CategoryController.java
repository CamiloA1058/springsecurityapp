package com.rangotech.springsecurityapp.controller;

import com.rangotech.springsecurityapp.persistence.entity.Category;
import com.rangotech.springsecurityapp.service.ICategoryService;
import com.rangotech.springsecurityapp.service.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
