package com.rangotech.springsecurityapp.controller;

import com.rangotech.springsecurityapp.service.dto.CartDto;
import com.rangotech.springsecurityapp.service.impl.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @PostMapping("public/carts/{cartId}/products/{productId}/quantity/{quantity}")
    public ResponseEntity<CartDto> addProductToCart(
            @PathVariable Long cartId,
            @PathVariable Long productId,
            @PathVariable Integer quantity
    ){
        CartDto cartDto = cartService.addProductToCart(cartId,productId,quantity);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
    @PutMapping("public/carts/{cartId}/products/{productId}/quantity/{quantity}")
    public ResponseEntity<CartDto> updateProductQuantityInCart(
            @PathVariable Long cartId,
            @PathVariable Long productId,
            @PathVariable Integer quantity
    ){
        CartDto cartDto = cartService.updateProductQuantityInCart(cartId,productId,quantity);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
}
