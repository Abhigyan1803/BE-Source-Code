package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AdvCellSops;

@Repository
public interface AdvCellSopsRepo extends JpaRepository<AdvCellSops, Long>{

	List<AdvCellSops> findByStatus(int status);
	
	List<AdvCellSops> findAllByOrderByIdDesc();

}
