package com.shopproject.technicalTest.service;

import com.shopproject.technicalTest.dto.BranchDTO;
import com.shopproject.technicalTest.exception.NotFoundException;
import com.shopproject.technicalTest.mapper.Mapper;
import com.shopproject.technicalTest.model.Branch;
import com.shopproject.technicalTest.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService implements IBranchService{

    @Autowired
    private BranchRepository repo;

    @Override
    public List<BranchDTO> getBranches() {
        return repo.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public BranchDTO createBranch(BranchDTO branchDTO) {
        Branch branch = Branch.builder()
                .name(branchDTO.getName())
                .address(branchDTO.getAddress())
                .build();
        return Mapper.toDTO(repo.save(branch));
    }

    @Override
    public BranchDTO updateBranch(Long id, BranchDTO branchDTO) {
        //Buscar Branches
        Branch branch = repo.findById(id)
                .orElseThrow(()-> new NotFoundException("Branch Not Found."));
        
        branch.setName(branchDTO.getName());
        branch.setAddress(branchDTO.getAddress());
        
        return Mapper.toDTO(repo.save(branch));
    }

    @Override
    public void deleteBranch(Long id) {
        if (!repo.existsById(id)){
            throw new NotFoundException("Branch Not Found to DELETE.");
        }

        repo.deleteById(id);

    }
}
