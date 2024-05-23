package com.rangotech.springsecurityapp.service.impl;

import com.rangotech.springsecurityapp.exceptions.ResourceAlreadyExistException;
import com.rangotech.springsecurityapp.exceptions.ResourceNotFoundException;
import com.rangotech.springsecurityapp.mapper.CategoryDtoMappper;
import com.rangotech.springsecurityapp.persistence.entity.Category;
import com.rangotech.springsecurityapp.persistence.repository.CategoryRepository;
import com.rangotech.springsecurityapp.service.ICategoryService;
import com.rangotech.springsecurityapp.service.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDtoMappper categoryDtoMappper;

    @Override
    public List<CategoryDto> findAll() {


        return null;
    }

    @Override
    public CategoryDto save(Category category) {
        categoryRepository
                .findByCategoryName(category.getCategoryName())
                .ifPresent(p -> {
                    throw new ResourceAlreadyExistException("La categoria ya se encuentra guardada en la base de datos");
                });
        Category savedCategory = categoryRepository.save(category);
        return categoryDtoMappper.map(savedCategory);
    }

    @Override
    public Category findById(Long categoryId) {
        return categoryRepository
                .findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("la categoria con el id: " + " no se encuentra en nuestra base de datos"
                        ));
    }

    @Override
    public CategoryDto update(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
