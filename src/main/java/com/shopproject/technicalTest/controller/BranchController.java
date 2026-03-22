package com.shopproject.technicalTest.controller;

import com.shopproject.technicalTest.dto.BranchDTO;
import com.shopproject.technicalTest.service.IBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private IBranchService branchService;

    @GetMapping
    public ResponseEntity<List<BranchDTO>> getBranches(){
        return  ResponseEntity.ok(branchService.getBranches());
    }

    @PostMapping
    public  ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO dto){
        BranchDTO b = branchService.createBranch(dto);
        return ResponseEntity.created(URI.create("/api/branches/" + b.getId()))
                .body(b);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable Long id, @RequestBody BranchDTO dto){
        return  ResponseEntity.ok(branchService.updateBranch(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch (@PathVariable Long id){
        branchService.deleteBranch(id);

        return ResponseEntity.noContent().build();
    }
}
