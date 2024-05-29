package com.rangotech.springsecurityapp.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    private Long productId;
    @Column(nullable = false)
    @NotBlank
    @Size(min = 3, max = 15, message = "El nombre del producto debe estar contenindo entre 3 y 15 caracteres")
    private String productName;
    @Column(nullable = false)
    @NotBlank
    @Size(min = 6, max = 500, message = "La descripcion del producto debe contener entre 6 y 500 caracteres")
    private String description;
    private Double price;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartProduct> cartProducts = new ArrayList<>();
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<OrderProduct> orderItems = new ArrayList<>();
}
