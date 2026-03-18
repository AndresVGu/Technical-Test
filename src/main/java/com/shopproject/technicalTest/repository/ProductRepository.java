package com.shopproject.technicalTest.repository;

import com.shopproject.technicalTest.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Long> {
}
