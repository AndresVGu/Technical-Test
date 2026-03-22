package com.shopproject.technicalTest.controller;


import com.shopproject.technicalTest.dto.SaleDTO;
import com.shopproject.technicalTest.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    @Autowired
    private ISaleService saleService;

    @GetMapping
    public ResponseEntity<List<SaleDTO>> getSales(){
        return ResponseEntity.ok(saleService.getSales());
    }

    @PostMapping
    public ResponseEntity<SaleDTO> createSale(@RequestBody SaleDTO dto){
        SaleDTO s = saleService.createSale(dto);
        return ResponseEntity.created(URI.create("/api/sales" + s.getId()))
                .body(s);
    }

    @PutMapping("/{id}") //check the difference between ResponseEntity and saleService.updateSale(id, dto)
    public SaleDTO updateSale(@PathVariable Long id, @RequestBody SaleDTO dto){
        return  saleService.updateSale(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id){
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}
