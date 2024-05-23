package com.rangotech.springsecurityapp.mapper;

import com.rangotech.springsecurityapp.persistence.entity.Cart;
import com.rangotech.springsecurityapp.service.dto.CartDto;
import org.springframework.stereotype.Component;

@Component
public class CartDtoMapper implements IMapper<Cart, CartDto>{
    @Override
    public CartDto map(Cart in) {
        return new CartDto(
                in.getCartId(),
                in.getUser().getName(),
                in.getTotalPrice(),
                in.getCartProducts()
        );
    }
}
