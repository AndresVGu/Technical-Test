package com.shopproject.technicalTest.mapper;

import com.shopproject.technicalTest.dto.BranchDTO;
import com.shopproject.technicalTest.dto.DetailSaleDTO;
import com.shopproject.technicalTest.dto.ProductDTO;
import com.shopproject.technicalTest.dto.SaleDTO;
import com.shopproject.technicalTest.model.Branch;
import com.shopproject.technicalTest.model.DetailSale;
import com.shopproject.technicalTest.model.Product;
import com.shopproject.technicalTest.model.Sale;

import java.util.stream.Collectors;

public class Mapper {

    //Mapeo del product a ProductDTO
    public  static ProductDTO toDTO(Product p){
        if (p == null) return null;

        return ProductDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .category(p.getCategory())
                .price(p.getPrice())
                .build();
    }

    //MApeo de Sale a SaleDTO

    public static SaleDTO toDTO(Sale s){
        if(s == null) return null;

        //Traemos de la venta el detalle, que es la lista del detalle, para posteriormente hacemos el mapo
        var detail = s.getDetail().stream().map(detailSale ->
                DetailSaleDTO.builder() //es una maqueta, debemos usar el mismo nombre de los DTOs
                        .id(detailSale.getProduct().getId())
                        .productName(detailSale.getProduct().getName())
                        .productQuantity(detailSale.getProdQuantity())
                        .unitPrice(detailSale.getUnitPrice())
                        .subTotal(detailSale.getUnitPrice() * detailSale.getProdQuantity()) //el multiply no se toma porque es propio de los bigdecimal
                        //.multiply(Double.valueof(detail.getProdQuantity)) la ventaja es que no tenems que transformar a double
                        .build()
        ).collect(Collectors.toList()); //collertor recolecta todos los valores, en este caso en una lista

        //Para calcular el total, de cada uno de los valore vamos a hacer la sumatorio
        var total = detail.stream()
                .map(DetailSaleDTO::getSubTotal)
                .reduce(0.0, Double::sum);
                //.reduce(Double.ZERO, Double::add) este se usa cuando trabajamos con big decimals

        return  SaleDTO.builder()
                .id(s.getId())
                .date(s.getDate())
                .branchId(s.getBranch().getId())
                .status(s.getStatus())
                .detail(detail)
                .total(total)
                .build();
    }
    //Mapeo de Branch to BranchDTO
    public static BranchDTO toDTO(Branch b){
        if (b == null) return null;

        return BranchDTO.builder()
                .id(b.getId())
                .name(b.getName())
                .address(b.getAddress())
                .build();

    }
}
