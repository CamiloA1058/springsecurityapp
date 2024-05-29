package com.rangotech.springsecurityapp.persistence.repository;

import com.rangotech.springsecurityapp.persistence.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
