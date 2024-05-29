package com.rangotech.springsecurityapp.service;

import com.rangotech.springsecurityapp.persistence.entity.Category;
import com.rangotech.springsecurityapp.service.dto.CategoryDto;

import java.util.List;

public interface ICategoryService {
    List<CategoryDto> findAll();

    CategoryDto save(Category category);

    Category findById(Long categoryId);

    CategoryDto updateCategory(Category category, Long categoryId);

    String deleteCategory(Long categoryId);
}
