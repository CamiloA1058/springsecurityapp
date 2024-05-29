package com.rangotech.springsecurityapp.service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long orderId;
    private String username;
    private List<OrderProductDto> orderProducts = new ArrayList<>();
    private LocalDateTime orderDate;
    private PaymentDto payment;
    private Double totalAmount;
    private String orderStatus;
}
