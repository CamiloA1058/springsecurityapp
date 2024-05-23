package com.rangotech.springsecurityapp.controller;

import com.rangotech.springsecurityapp.persistence.entity.Product;
import com.rangotech.springsecurityapp.service.IProductService;
import com.rangotech.springsecurityapp.service.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;

    @GetMapping("public/products")
    public List<ProductDto> findAll(){
        return productService.findAll();
    }
    @PostMapping("admin/category/{categoryId}/product")
    public ResponseEntity<ProductDto> save(@RequestBody Product product, @PathVariable Long categoryId){
        ProductDto savedProduct = productService.save(product, categoryId);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    };
}
