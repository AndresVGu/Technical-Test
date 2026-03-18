package com.shopproject.technicalTest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class SaleDTO {
    private Long id;
    private LocalDate date;
    private String state;

    //Branch data, we don't need all branch data, in this case we only use branchid
    private  Long branchId;

    //Details list
    private List<DetailSaleDTO> detail;

    //Total
    private Double total;

}
