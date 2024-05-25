package com.rangotech.springsecurityapp.service;

import com.rangotech.springsecurityapp.persistence.entity.Product;
import com.rangotech.springsecurityapp.service.dto.ProductDto;

import java.util.List;

public interface IProductService{
    List<ProductDto> findAll();

    ProductDto findById(Long id);

    void deleteById(Long id);

    ProductDto save(Product product, Long categoryId);
    List<ProductDto> findByNameLike(String productName);

    List<ProductDto> findByCategory(Long categoryId);
    ProductDto update(Product product);
}
