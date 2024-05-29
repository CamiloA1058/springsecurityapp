package com.rangotech.springsecurityapp.service;

import com.rangotech.springsecurityapp.service.dto.CartDto;

public interface ICartService {

    CartDto addProductToCart(Long cartId, Long productId,  Integer quantity);
    CartDto updateProductQuantityInCart(Long cartId, Long productId, Integer quantity);
    String deleteProductFromCart(Long cartId, Long productId);
}
