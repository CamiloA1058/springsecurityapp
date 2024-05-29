package com.rangotech.springsecurityapp.mapper;

import com.rangotech.springsecurityapp.persistence.entity.Order;
import com.rangotech.springsecurityapp.service.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDtoMapper implements IMapper<Order, OrderDto>{
    private final OrderProductDtoMapper orderProductDtoMapper;
    private final PaymentDtoMapper paymentDtoMapper;
    @Override
    public OrderDto map(Order in) {
        return new OrderDto(
                in.getOrderId(),
                in.getUsername(),
                in.getOrderProducts().stream().map(orderProductDtoMapper::map).toList(),
                in.getOrderDate(),
                paymentDtoMapper.map(in.getPayment()),
                in.getTotalAmount(),
                in.getOrderStatus()
        );
    }
}
