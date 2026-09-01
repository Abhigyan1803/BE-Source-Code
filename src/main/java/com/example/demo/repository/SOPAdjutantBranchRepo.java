package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SOPAdjutantBranch;

@Repository
public interface SOPAdjutantBranchRepo extends JpaRepository<SOPAdjutantBranch, Long>{

	List<SOPAdjutantBranch> findAllByStatusOrderByIdDesc(int status);

	List<SOPAdjutantBranch> findAllByOrderByIdDesc();

}
