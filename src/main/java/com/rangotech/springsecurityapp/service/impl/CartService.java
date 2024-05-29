package com.rangotech.springsecurityapp.service.impl;

import com.rangotech.springsecurityapp.exceptions.ApiException;
import com.rangotech.springsecurityapp.exceptions.ResourceAlreadyExistException;
import com.rangotech.springsecurityapp.exceptions.ResourceNotFoundException;
import com.rangotech.springsecurityapp.mapper.CartDtoMapper;
import com.rangotech.springsecurityapp.mapper.ProductDtoMapper;
import com.rangotech.springsecurityapp.persistence.entity.Cart;
import com.rangotech.springsecurityapp.persistence.entity.CartProduct;
import com.rangotech.springsecurityapp.persistence.entity.Product;
import com.rangotech.springsecurityapp.persistence.repository.CartProductRepository;
import com.rangotech.springsecurityapp.persistence.repository.CartRepository;
import com.rangotech.springsecurityapp.persistence.repository.ProductRepository;
import com.rangotech.springsecurityapp.service.ICartService;
import com.rangotech.springsecurityapp.service.dto.CartDto;
import com.rangotech.springsecurityapp.service.dto.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartProductRepository cartProductRepository;
    private final CartDtoMapper cartDtoMapper;
    private final ProductDtoMapper productDtoMapper;

    @Override
    public CartDto addProductToCart(Long cartId, Long productId,  Integer quantity){

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

        List<ProductDto> productsInCart = cart.getCartProducts().stream().map(cp ->
                productDtoMapper.map(cp.getProduct())
        ).toList();

        CartDto cartDto = cartDtoMapper.map(cart);

        cartDto.setProducts(productsInCart);

        return cartDto;
    }

    @Override
    public CartDto updateProductQuantityInCart(Long cartId, Long productId, Integer quantity) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("El carrito no se encuentra registrado en la base de datos"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("El producto no se encuentra registrado en la base de datos"));

        if(product.getQuantity() == 0 ){
            throw new ApiException("El producto no esta disponible");
        }

        CartProduct cartProduct = cartProductRepository
                .findCartProductByCartIdAndProductId(cartId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("El producto no se encuentra disponible en el carrito"));

        cart.setTotalPrice(cart.getTotalPrice() - (cartProduct.getProduct().getPrice() * cartProduct.getQuantity())); //Se reasigna el valor anterior al carrito de compras

        product.setQuantity(product.getQuantity() + cartProduct.getQuantity() - quantity); //se reasigna la cantidad en stock del producto

        cartProduct.setQuantity(quantity);

        cart.setTotalPrice(cart.getTotalPrice() + (product.getPrice() * quantity));

        cartProductRepository.save(cartProduct);

        List<ProductDto> productsInCart = cart.getCartProducts().stream().map(cp ->
            productDtoMapper.map(cp.getProduct())
        ).toList();

        CartDto cartDto = cartDtoMapper.map(cart);

        cartDto.setProducts(productsInCart);

        return cartDto;
    }

    @Override
    public String deleteProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("El carrito de compras no se encuentra en la bd"));

        CartProduct cartProduct = cartProductRepository.findCartProductByCartIdAndProductId(cartId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("El producto no se encuentra en el carrito de compras"));



        cart.setTotalPrice(cart.getTotalPrice() - (cartProduct.getProduct().getPrice() * cartProduct.getQuantity()));

        Product product = cartProduct.getProduct();
        product.setQuantity(product.getQuantity() + cartProduct.getQuantity());

        cartProductRepository.deleteCartProductByProductIdAndCartId(cartId, productId);

        return "El producto " + cartProduct.getProduct().getProductName() + " se removio del carrito de compras !!!";
    }
}
