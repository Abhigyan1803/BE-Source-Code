package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ReceptionAdjutantBranch;

@Repository
public interface ReceptionAdjutantBranchRepo extends JpaRepository<ReceptionAdjutantBranch, Long>{

	List<ReceptionAdjutantBranch> findAllByStatusOrderByIdDesc(int status);

	List<ReceptionAdjutantBranch> findAllByOrderByIdDesc();

}
