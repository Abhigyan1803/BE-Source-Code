package com.example.demo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.OqSubjectAttribute;

@Repository
public interface OqSubjectAttributeRepo  extends JpaRepository<OqSubjectAttribute,Long>{

	Set<OqSubjectAttribute> findByTermId(Long termId);
	
}
