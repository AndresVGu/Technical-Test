package com.shopproject.technicalTest.service;

import com.shopproject.technicalTest.dto.DetailSaleDTO;
import com.shopproject.technicalTest.dto.SaleDTO;
import com.shopproject.technicalTest.exception.NotFoundException;
import com.shopproject.technicalTest.mapper.Mapper;
import com.shopproject.technicalTest.model.Branch;
import com.shopproject.technicalTest.model.DetailSale;
import com.shopproject.technicalTest.model.Product;
import com.shopproject.technicalTest.model.Sale;
import com.shopproject.technicalTest.repository.BranchRepository;
import com.shopproject.technicalTest.repository.ProductRepository;
import com.shopproject.technicalTest.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//un poco mas compleja debido a que esta relacionado con todo
public class SaleService implements ISaleService {
    @Autowired
    private SaleRepository saleRepo;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private BranchRepository branchRepo;

    @Override
    public List<SaleDTO> getSales() {
        List<Sale> sales = saleRepo.findAll();
        List<SaleDTO> salesDTO = new ArrayList<>();
        SaleDTO dto;

        //forma clasica
        for (Sale s : sales){
            dto = Mapper.toDTO(s);
            salesDTO.add(dto);
        }

        return  salesDTO;
    }

    //asegurarnos que la saleDto no sea null, tenga surcursal y detalle
    @Override
    public SaleDTO createSale(SaleDTO saleDTO) {
        //Check data
        if(saleDTO == null) throw new RuntimeException("SaleDTO is NULL");
        if(saleDTO.getBranchId() == null) throw new RuntimeException("Must indicate Branch");
        if(saleDTO.getDetail() == null || saleDTO.getDetail().isEmpty())
            throw new RuntimeException("Must Include at last One Product");

        //Find Branch
        Branch branch = branchRepo.findById(saleDTO.getBranchId())
                .orElse(null);
        if (branch == null){
            throw  new NotFoundException("Branch Not Foud.");
        }

        //Crate Sale
        Sale s = new Sale();
        s.setDate(saleDTO.getDate());
        s.setStatus(saleDTO.getStatus());
        s.setBranch(branch);
        s.setTotal(saleDTO.getTotal());

        //Details List
        //--> Here are the products
        List<DetailSale> detailsList = new ArrayList<>();

        for (DetailSaleDTO detDTO : saleDTO.getDetail()){
            //Search product by id (your DTO use id as a product id)
            //In this case we crate a custom method, in product repository. All of this just for avoid filter by ID
            //we are filtering by name, filter by name is not a good practice because we can have 2 products with the same name
            Product p = productRepo.findByName(detDTO.getProductName())
                    .orElse(null);
            if (p == null){
                throw new RuntimeException("Product Not Found: " + detDTO.getProductName());
            }

            //Create Detail
            DetailSale detailS = new DetailSale();
            detailS.setProduct(p);
            detailS.setUnitPrice(detDTO.getUnitPrice());
            detailS.setProdQuantity(detDTO.getProductQuantity());
            detailS.setSale(s);

            detailsList.add(detailS);
        }

        //Set details sale list
        s.setDetail(detailsList);

        //Save
        saleRepo.save(s);

        //Mapping
        SaleDTO saleOutput = Mapper.toDTO(s);

        return saleOutput;
    }

    @Override
    public SaleDTO updateSale(Long id, SaleDTO saleDTO) {
        Sale s = saleRepo.findById(id).orElse(null);
        if (s == null) throw new RuntimeException("Sale not found.");

        //validate data
        if (saleDTO.getDate()!= null){
            s.setDate(saleDTO.getDate());
        }
        if (saleDTO.getStatus()!=null){
            s.setStatus(saleDTO.getStatus());
        }
        if (saleDTO.getTotal()!=null){
            s.setTotal(saleDTO.getTotal());
        }
        if (saleDTO.getBranchId()!=null){
            Branch branch = branchRepo.findById(saleDTO.getBranchId())
                    .orElse(null);
            if (branch == null) throw  new NotFoundException("Branch not found");
            s.setBranch(branch);
        }

        saleRepo.save(s);
        //change to dto format
        SaleDTO saleOutput = Mapper.toDTO(s);

        return saleOutput;
    }

    @Override
    public void deleteSale(Long id) {
        //Find sale
        Sale s = saleRepo.findById(id).orElse(null);
        if (s == null) throw  new RuntimeException("Sale not found");

        saleRepo.delete(s);
    }
}
