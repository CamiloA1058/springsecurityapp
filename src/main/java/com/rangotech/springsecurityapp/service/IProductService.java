package com.rangotech.springsecurityapp.service;

import com.rangotech.springsecurityapp.persistence.entity.Product;

import java.util.List;

public interface IProductService{
    List<Product> findAll();

    Product findById(Long id);

    void deleteById(Long id);

    Product save(Product product);

    List<Product> searchByCategory();
}
