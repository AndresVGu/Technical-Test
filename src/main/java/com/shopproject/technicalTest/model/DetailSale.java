package com.shopproject.technicalTest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//Each element of this class is a line of the recipe or sale of the product
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class DetailSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Sale one sale can have many details
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "saleId")
    private Sale sale;

    //Product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    private int prodQuantity;
    private double unitPrice;



}
