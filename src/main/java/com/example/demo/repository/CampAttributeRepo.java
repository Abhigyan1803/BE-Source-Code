package com.example.demo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.CampAttribute;
import com.example.demo.model.CampDetails;

@Repository
public interface CampAttributeRepo extends JpaRepository<CampAttribute,Long> {

	Set<CampDetails> findByTermId(Long termId);

}
