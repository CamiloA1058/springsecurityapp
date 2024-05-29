package com.rangotech.springsecurityapp.persistence.repository;

import com.rangotech.springsecurityapp.persistence.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
