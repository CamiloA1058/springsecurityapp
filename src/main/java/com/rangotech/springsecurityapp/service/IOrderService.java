package com.rangotech.springsecurityapp.service;

import com.rangotech.springsecurityapp.service.dto.OrderDto;

import java.util.List;

public interface IOrderService {
    OrderDto placeOrder(String emailId, Long cartId, String paymentMethod);

    List<OrderDto> getOrdersByUser(String emailId);

    OrderDto updateOrder(String emailId, Long orderId, String orderStatus);
}
