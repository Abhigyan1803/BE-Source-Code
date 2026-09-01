package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.TransportDemand;

public interface TransportDemandRepo extends JpaRepository<TransportDemand, Long> {

	Page<TransportDemand> findAllByOrderByIdDesc(Pageable pagedData);

	List<TransportDemand> findAllByOrderByIdDesc();
	
}
