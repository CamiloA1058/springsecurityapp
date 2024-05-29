package com.rangotech.springsecurityapp.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Long orderId;

    @Email
    @Column(nullable = false)
    private String username;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    private LocalDateTime orderDate;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    private Double totalAmount;
    private String orderStatus;
}
