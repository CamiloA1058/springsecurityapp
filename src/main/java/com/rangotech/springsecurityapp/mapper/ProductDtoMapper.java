package com.rangotech.springsecurityapp.mapper;

import com.rangotech.springsecurityapp.persistence.entity.Product;
import com.rangotech.springsecurityapp.service.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper implements IMapper<Product, ProductDto>{

    @Override
    public ProductDto map(Product in) {
        return new ProductDto(
                in.getProductId(),
                in.getProductName(),
                in.getDescription(),
                in.getPrice(),
                in.getQuantity()
        );
    }
}
