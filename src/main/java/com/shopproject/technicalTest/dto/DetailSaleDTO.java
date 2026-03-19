package com.shopproject.technicalTest.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class DetailSaleDTO {
    private Long id;
    private String productName;
    private int productQuantity;
    private Double unitPrice;
    private Double subTotal;

}
