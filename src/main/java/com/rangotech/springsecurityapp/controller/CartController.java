package com.rangotech.springsecurityapp.controller;

import com.rangotech.springsecurityapp.service.dto.CartDto;
import com.rangotech.springsecurityapp.service.impl.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @PostMapping("public/cart/{productId}/{cartId}/{quantity}")
    public ResponseEntity<CartDto> addProductToCart(
            @PathVariable Long productId,
            @PathVariable Long cartId,
            @PathVariable Integer quantity
    ){
        CartDto cartDto = cartService.addProductToCart(productId,cartId,quantity);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
}
