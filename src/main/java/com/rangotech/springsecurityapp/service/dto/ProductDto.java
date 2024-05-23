package com.rangotech.springsecurityapp.service.dto;


import com.rangotech.springsecurityapp.persistence.entity.Category;

public record ProductDto(Long productId,
                         String productName,
                         String description,
                         Double price,
                         Integer quantity
        ) {
}
