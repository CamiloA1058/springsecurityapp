package com.rangotech.springsecurityapp.persistence.repository;

import com.rangotech.springsecurityapp.persistence.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

    @Query("SELECT cp FROM CartProduct cp WHERE cp.cart.id = :cartId AND cp.product.id = :productId")
    Optional<CartProduct> findCartProductByCartIdAndProductId(@Param("cartId") Long cartId,@Param("productId") Long productId);

    @Modifying
    @Query("DELETE FROM CartProduct cp WHERE cp.cart.id = :cartId AND cp.product.id = :productId")
    void deleteCartProductByProductIdAndCartId(@Param("productId") Long productId,@Param("cartId") Long cartId);
}
