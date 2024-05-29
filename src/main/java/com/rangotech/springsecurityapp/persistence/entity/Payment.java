package com.rangotech.springsecurityapp.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue
    private Long paymentId;

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    private Order order;

    @NotBlank
    @Size(min = 4, message = "El metodo de pago debe contener al menos 4 caracteres")
    private String paymentMethod;

}
