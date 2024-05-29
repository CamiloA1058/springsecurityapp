package com.rangotech.springsecurityapp.mapper;

import com.rangotech.springsecurityapp.persistence.entity.OrderProduct;
import com.rangotech.springsecurityapp.service.dto.OrderProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderProductDtoMapper implements IMapper<OrderProduct, OrderProductDto>{
    private final ProductDtoMapper productDtoMapper;
    @Override
    public OrderProductDto map(OrderProduct in) {
        return new OrderProductDto(
                in.getOrderProductId(),
                productDtoMapper.map(in.getProduct()),
                in.getQuantity(),
                in.getOrderedProductPrice()
        );
    }
}
