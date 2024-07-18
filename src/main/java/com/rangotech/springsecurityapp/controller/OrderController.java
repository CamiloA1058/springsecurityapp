package com.rangotech.springsecurityapp.controller;

import com.rangotech.springsecurityapp.service.dto.OrderDto;
import com.rangotech.springsecurityapp.service.impl.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/public/users/{usernameId}/carts/{cartId}/payments/{paymentMethod}/order")
    public ResponseEntity<OrderDto> orderProducts(@PathVariable String usernameId, @PathVariable Long cartId, @PathVariable String paymentMethod) {
        OrderDto order = orderService.placeOrder(usernameId, cartId, paymentMethod);

        return new ResponseEntity<OrderDto>(order, HttpStatus.CREATED);
    }
}
