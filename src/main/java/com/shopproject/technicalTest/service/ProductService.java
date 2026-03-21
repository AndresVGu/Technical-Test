package com.shopproject.technicalTest.service;

import com.shopproject.technicalTest.dto.ProductDTO;
import com.shopproject.technicalTest.exception.NotFoundException;
import com.shopproject.technicalTest.mapper.Mapper;
import com.shopproject.technicalTest.model.Product;
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

    //Vamos a usar un patron como DTO
    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        //hacer el mapo creando una variale auxiliar
        Product prod = Product.builder()
                .name(productDTO.getName())
                .category(productDTO.getCategory())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .build();
        //guarda y retorna como parte del valor
        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {

        //vamos a buscar si existe el producto
        Product prod = repo.findById(id)
                //vamos a crear nuestras exepciones personalizadas
                //creo un nuevo paquete llamado exceptions
                .orElseThrow(()-> new NotFoundException("Product Not Found."));

        prod.setName(productDTO.getName());
        prod.setCategory(productDTO.getCategory());
        prod.setStock(productDTO.getStock());
        prod.setPrice(productDTO.getPrice());

        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public void deleteProduct(Long id) {
        //En este caso no hacemo el borrado logico
        //vmos a ver si existe
        if (!repo.existsById(id)){
            throw  new NotFoundException("Product Not Found to DELETE");
        }

        repo.deleteById(id);

    }
}
