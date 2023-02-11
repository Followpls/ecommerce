package com.dotech.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "product_name", nullable = false)
    private String name;
    @Column(name = "product_description", nullable = false)
    private String description;
    @Column(name = "unit_price")
    private double unitPrice;
    @Column(name = "quantity")
    private int quantity;


}
