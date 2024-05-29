package com.rangotech.springsecurityapp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto{
        private Long cartId;
        private String user;
        private Double totalPrice;
        private List<ProductDto> products = new ArrayList<>();
}
