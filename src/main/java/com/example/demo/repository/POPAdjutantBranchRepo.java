package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.POPAdjutantBranch;

@Repository
public interface POPAdjutantBranchRepo extends JpaRepository<POPAdjutantBranch, Long> {

	List<POPAdjutantBranch> findAllByStatusOrderByIdDesc(int status);

	List<POPAdjutantBranch> findAllByOrderByIdDesc();

}
