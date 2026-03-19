package com.shopproject.technicalTest.service;

import com.shopproject.technicalTest.dto.BranchDTO;


import java.util.List;

public interface IBranchService {

    //Abstract methods:
    //Get
    List<BranchDTO> getBranches();
    //create
    BranchDTO createBranch(BranchDTO branchDTO);
    //update
    BranchDTO updateBranch(Long id, BranchDTO branchDTO);
    //delete
    void deleteBranch(Long id);

}
