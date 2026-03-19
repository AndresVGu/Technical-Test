package com.shopproject.technicalTest.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SaleDTO {
    private Long id;
    private LocalDate date;
    private String status;

    //Branch data, we don't need all branch data, in this case we only use branchid
    private  Long branchId;

    //Details list
    private List<DetailSaleDTO> detail;

    //Total
    private Double total;

}
