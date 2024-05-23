package com.rangotech.springsecurityapp.service.dto;

import com.rangotech.springsecurityapp.persistence.entity.CartProduct;

import java.util.List;

public record CartDto(
        Integer cartId,
        String user,
        Double totalPrice,
        List<CartProduct> cartProducts
        ) {
}
