package com.rangotech.springsecurityapp.persistence.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private Long productId;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private String description;
    private Double price;
    private Integer quantity;

}
