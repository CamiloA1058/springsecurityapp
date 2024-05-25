package com.rangotech.springsecurityapp.service.impl;

import com.rangotech.springsecurityapp.exceptions.ApiException;
import com.rangotech.springsecurityapp.exceptions.ResourceAlreadyExistException;
import com.rangotech.springsecurityapp.exceptions.ResourceNotFoundException;
import com.rangotech.springsecurityapp.mapper.CartDtoMapper;
import com.rangotech.springsecurityapp.persistence.entity.Cart;
import com.rangotech.springsecurityapp.persistence.entity.CartProduct;
import com.rangotech.springsecurityapp.persistence.entity.Product;
import com.rangotech.springsecurityapp.persistence.repository.CartProductRepository;
import com.rangotech.springsecurityapp.persistence.repository.CartRepository;
import com.rangotech.springsecurityapp.persistence.repository.ProductRepository;
import com.rangotech.springsecurityapp.service.ICartService;
import com.rangotech.springsecurityapp.service.dto.CartDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Service
@AllArgsConstructor
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartProductRepository cartProductRepository;
    private final CartDtoMapper cartDtoMapper;

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
    @Override
    public CartDto addProductToCart(Long productId, Long cartId, Integer quantity){

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("El producto no se encuentra en nuestra base de datos"));

        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("El carrito no se encuentra en nuestra base de datos"));

        cartProductRepository
                .findCartProductByCartIdAndProductId(cartId, productId)
                .ifPresent(cp -> {
                    throw new ResourceAlreadyExistException("El producto ya se encuentra en el carrito de compras");
                });
        if(product.getQuantity() == 0 ){
            throw new ApiException("El producto no esta disponible");
        }
        if (product.getQuantity() < quantity){
            throw new ApiException("Porfavor haz una orden menor o igual a la cantidad en stock del producto");
        }

        CartProduct cartProduct = new CartProduct();

        cartProduct.setCart(cart);
        cartProduct.setProduct(product);
        cartProduct.setQuantity(quantity);


        cartProductRepository.save(cartProduct);

        product.setQuantity(product.getQuantity() - quantity);


        cart.setTotalPrice(cart.getTotalPrice() + (product.getPrice() * quantity));


        return cartDtoMapper.map(cart);
    };















}
