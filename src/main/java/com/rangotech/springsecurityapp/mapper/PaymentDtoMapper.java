package com.rangotech.springsecurityapp.mapper;

import com.rangotech.springsecurityapp.persistence.entity.Payment;
import com.rangotech.springsecurityapp.service.dto.PaymentDto;
import org.springframework.stereotype.Component;

@Component
public class PaymentDtoMapper implements IMapper<Payment, PaymentDto>{
    @Override
    public PaymentDto map(Payment in) {
        return new PaymentDto(
                in.getPaymentId(),
                in.getPaymentMethod()
        );
    }
}
