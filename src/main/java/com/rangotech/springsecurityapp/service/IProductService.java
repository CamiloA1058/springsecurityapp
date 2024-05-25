package com.rangotech.springsecurityapp.service;

import com.rangotech.springsecurityapp.persistence.entity.Product;
import com.rangotech.springsecurityapp.service.dto.ProductDto;

import java.util.List;

public interface IProductService{
    List<ProductDto> findAll();

    ProductDto save(Product product, Long categoryId);

    ProductDto findById(Long id);

    ProductDto update(Product product);

    void deleteById(Long id);

    List<ProductDto> findByNameLike(String productName);

    List<ProductDto> findByCategory(Long categoryId);
}
