package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AdjutantBranch;

@Repository
public interface AdjutantBranchRepo extends JpaRepository<AdjutantBranch, Long> {

}
