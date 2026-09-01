package com.example.demo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.CampDetails;

@Repository
public interface CampDetailsRepo extends JpaRepository<CampDetails,Long> {

	Set<CampDetails> findByCampAtributeTermId(Long termId);
	
	
}
