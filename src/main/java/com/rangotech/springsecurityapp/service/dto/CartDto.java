package com.rangotech.springsecurityapp.service.dto;

import java.util.List;

public record CartDto(
        Integer cartId,
        String user,
        Double totalPrice,
        List<ProductDto> products
        ) {
}
