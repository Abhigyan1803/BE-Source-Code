package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ObsnSheet;

@Repository
public interface ObsnSheetRepo extends JpaRepository<ObsnSheet, Long>{


	List<ObsnSheet> findByStatusAndServiceId(Integer status, String serviceId);

	List<ObsnSheet> findAllById(Long id);
}
