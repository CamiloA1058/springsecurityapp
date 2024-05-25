package com.rangotech.springsecurityapp.service;

import com.rangotech.springsecurityapp.persistence.entity.Cart;
import com.rangotech.springsecurityapp.service.dto.CartDto;

public interface ICartService {
    Cart save(Cart cart);
    CartDto addProductToCart(Long productId, Long cartId, Integer quantity);
}
