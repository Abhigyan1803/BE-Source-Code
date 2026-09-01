package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AdjutantDetails;

@Repository
public interface AdjutantDetailsRepo extends JpaRepository<AdjutantDetails, Long> {

	List<AdjutantDetails> findAllByOrderByIdDesc();

	List<AdjutantDetails> findByAdjutantBranchIdAndStatusOrderByIdDesc(Long id, int status);

	List<AdjutantDetails> findByAdjutantBranchIdOrderByIdDesc(Long id);

	AdjutantDetails findByNameAndAdjutantBranchId(String name, Long id);

	List<AdjutantDetails> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

	//////
	List<AdjutantDetails> findByAdjutantBranchIdAndStatusAndFlagOrderByIdDesc(Long id, int status,boolean flag);

	List<AdjutantDetails> findByAdjutantBranchIdAndFlagOrderByIdDesc(Long id, boolean flag);
}
