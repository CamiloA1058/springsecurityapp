package com.rangotech.springsecurityapp.service.impl;

import com.rangotech.springsecurityapp.exceptions.ResourceAlreadyExistException;
import com.rangotech.springsecurityapp.exceptions.ResourceNotFoundException;
import com.rangotech.springsecurityapp.mapper.CategoryDtoMappper;
import com.rangotech.springsecurityapp.mapper.ProductDtoMapper;
import com.rangotech.springsecurityapp.persistence.entity.Cart;
import com.rangotech.springsecurityapp.persistence.entity.Category;
import com.rangotech.springsecurityapp.persistence.entity.Product;
import com.rangotech.springsecurityapp.persistence.repository.CartRepository;
import com.rangotech.springsecurityapp.persistence.repository.CategoryRepository;
import com.rangotech.springsecurityapp.persistence.repository.ProductRepository;
import com.rangotech.springsecurityapp.service.ICartService;
import com.rangotech.springsecurityapp.service.ICategoryService;
import com.rangotech.springsecurityapp.service.IProductService;
import com.rangotech.springsecurityapp.service.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDtoMappper categoryDtoMappper;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final ICartService cartService;

    @Override
    public List<CategoryDto> findAll() {


        return null;
    }

    @Override
    public CategoryDto save(Category category) {
        categoryRepository
                .findByCategoryName(category.getCategoryName())
                .ifPresent(p -> {
                    throw new ResourceAlreadyExistException("La categoria ya se encuentra guardada en la base de datos");
                });
        Category savedCategory = categoryRepository.save(category);
        return categoryDtoMappper.map(savedCategory);
    }

    @Override
    public Category findById(Long categoryId) {
        return categoryRepository
                .findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("la categoria con el id: " + " no se encuentra en nuestra base de datos"
                        ));
    }

    @Override
    public CategoryDto updateCategory(Category category, Long categoryId) {
        findById(categoryId);

        category.setCategoryId(categoryId);

        Category savedCategory = categoryRepository.save(category);

        return categoryDtoMappper.map(savedCategory);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = findById(categoryId);

        List<Product> products = category.getProducts();

        products.forEach(p -> {
            Long id = p.getProductId();
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("El producto no se encuentra en la bd"));

            List<Cart> carts = cartRepository.findCartsByProductId(id);

            carts.forEach(cart -> cartService.deleteProductFromCart(cart.getCartId(), id));

            productRepository.delete(product);
        });

        categoryRepository.delete(category);

        return "La categoria con el id: " + categoryId + " se elimino correctamente !!!";
    }

}
