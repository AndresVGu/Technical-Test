package com.shopproject.technicalTest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class DetailSaleDTO {
    private Long id;
    private String productName;
    private int productQuantity;
    private Double unitPrice;
    private Double subTotal;

}
