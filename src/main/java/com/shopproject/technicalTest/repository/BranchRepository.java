package com.shopproject.technicalTest.repository;

import com.shopproject.technicalTest.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

//Make sure that is an interface instead a class.
public interface BranchRepository extends JpaRepository <Branch, Long> {
}
