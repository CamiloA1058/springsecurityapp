package com.rangotech.springsecurityapp.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "order_products")
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct {

    @Id
    @GeneratedValue
    private Long orderProductId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer quantity;

    private Double orderedProductPrice;
}
