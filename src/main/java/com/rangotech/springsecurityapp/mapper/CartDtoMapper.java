package com.rangotech.springsecurityapp.mapper;

import com.rangotech.springsecurityapp.persistence.entity.Cart;
import com.rangotech.springsecurityapp.service.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartDtoMapper implements IMapper<Cart, CartDto>{
    private final ProductDtoMapper productDtoMapper;

    @Override
    public CartDto map(Cart in) {
        return new CartDto(
                in.getCartId(),
                in.getUser().getName(),
                in.getTotalPrice(),
                in.getCartProducts()
                        .stream()
                        .map(p -> productDtoMapper.map(p.getProduct())).toList()
        );
    }
}
