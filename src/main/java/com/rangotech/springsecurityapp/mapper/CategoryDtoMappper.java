package com.rangotech.springsecurityapp.mapper;

import com.rangotech.springsecurityapp.persistence.entity.Category;
import com.rangotech.springsecurityapp.service.dto.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoMappper implements IMapper<Category, CategoryDto>{
    @Override
    public CategoryDto map(Category in) {
        return new CategoryDto(
                in.getCategoryId(),
                in.getCategoryName()
        );
    }
}
