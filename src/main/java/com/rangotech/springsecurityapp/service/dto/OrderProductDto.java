package com.rangotech.springsecurityapp.service.dto;

public record OrderProductDto(
        Long orderProductId,
        ProductDto product,
        Integer quantity,
        double orderedProductPrice) {

}
