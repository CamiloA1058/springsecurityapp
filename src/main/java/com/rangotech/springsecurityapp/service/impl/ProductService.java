package com.rangotech.springsecurityapp.service.impl;

import com.rangotech.springsecurityapp.exceptions.ResourceAlreadyExistException;
import com.rangotech.springsecurityapp.exceptions.ResourceNotFoundException;
import com.rangotech.springsecurityapp.mapper.ProductDtoMapper;
import com.rangotech.springsecurityapp.persistence.entity.Category;
import com.rangotech.springsecurityapp.persistence.entity.Product;
import com.rangotech.springsecurityapp.persistence.repository.ProductRepository;
import com.rangotech.springsecurityapp.service.ICategoryService;
import com.rangotech.springsecurityapp.service.IProductService;
import com.rangotech.springsecurityapp.service.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductDtoMapper productDtoMapper;
    private final ICategoryService categoryService;


    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(productDtoMapper::map)
                .toList();
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("El producto no se encuentra en nuesta base de datos")
        );
        return productDtoMapper.map(product);
    }

    @Override
    public void deleteById(Long id) { // Por implementar

    }

    @Override
    public ProductDto save(Product product, Long categoryId) {

        Category category = categoryService.findById(categoryId);

        productRepository.findById(product.getProductId()).ifPresent(p -> {
            throw new ResourceAlreadyExistException("El producto " + p.getProductName() + " ya se encuentra registrado");
        });


        product.setCategory(category);
        Product savedProduct = productRepository.save(product);

        return productDtoMapper.map(savedProduct);
    }

    @Override
    public List<ProductDto> findByNameLike(String productName) {
        return productRepository
                .findByNameLike(productName)
                .stream()
                .map(productDtoMapper::map)
                .toList();
    }

    @Override
    public List<ProductDto> findByCategory(Long categoryId) {

        Category category = categoryService.findById(categoryId);

        return productRepository.findByCategory(categoryId)
                .stream().map(productDtoMapper::map)
                .toList();
    }

    @Override
    public ProductDto update(Product product) { // Se necesita agregar mas funcionalidad
        Product productFromDB = productRepository
                .findById(product.getProductId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("El producto al que desea actualizar no se encuentra en nuesta base de datos"));
        Product updatedProduct = productRepository.save(product);

        return productDtoMapper.map(updatedProduct);
    }
}
