package com.shopproject.technicalTest.repository;

import com.shopproject.technicalTest.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository <Sale, Long> {
}
