package com.rangotech.springsecurityapp.service.dto;

import com.rangotech.springsecurityapp.persistence.entity.Cart;

public record UserRegisterDto(
        String name,
        String phone,
        String username,
        String password,
        Cart cart
    ) {
}
