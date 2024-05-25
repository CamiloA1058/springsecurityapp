package com.rangotech.springsecurityapp.service.dto;



public record ProductDto(Long productId,
                         String productName,
                         String description,
                         Double price,
                         Integer quantity
        ) {
}
