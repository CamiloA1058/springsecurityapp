package com.rangotech.springsecurityapp.persistence.repository;

import com.rangotech.springsecurityapp.persistence.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE c.user.username = :username AND c.id = :cartId")
    Optional<Cart> findCartByEmailAndCartId(@Param("username") String email,@Param("cartId") Long cartId);

    @Query("SELECT c FROM Cart c JOIN FETCH c.cartProducts cp JOIN FETCH cp.product p WHERE p.id = :id")
    List<Cart> findCartsByProductId(@Param("id")Long productId);
}
