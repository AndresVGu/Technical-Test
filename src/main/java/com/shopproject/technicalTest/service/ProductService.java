package com.shopproject.technicalTest.service;

import com.shopproject.technicalTest.dto.ProductDTO;
import com.shopproject.technicalTest.mapper.Mapper;
import com.shopproject.technicalTest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//La capa service es intermetaria entre base de datos y contolador
@Service
public class ProductService implements IProductService{
    //Inyeccion de dependecias

    @Autowired
    private ProductRepository repo;

    @Override
    public List<ProductDTO> getProducts() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        return null;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
