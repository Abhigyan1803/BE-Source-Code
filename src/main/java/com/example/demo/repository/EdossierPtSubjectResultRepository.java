package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EdossierPtSubjectResult;

@Repository
public interface EdossierPtSubjectResultRepository extends JpaRepository<EdossierPtSubjectResult, Long>{

	EdossierPtSubjectResult getSubResultById(Long id);


	
}
