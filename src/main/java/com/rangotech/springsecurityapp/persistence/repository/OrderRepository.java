package com.rangotech.springsecurityapp.persistence.repository;

import com.rangotech.springsecurityapp.persistence.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
