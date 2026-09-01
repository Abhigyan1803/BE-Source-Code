package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CadetCampCoyCdrResult;
import com.example.demo.model.CadetCampPlCdrResult;

@Repository
public interface CadetCampCoyCdrRepo extends JpaRepository<CadetCampCoyCdrResult,Long> {

	Optional<CadetCampCoyCdrResult> findByServiceIdAndTermIdAndCampAttributesId(String serviceId, Long termId, Integer id);

	List<CadetCampCoyCdrResult> findByServiceIdAndTermId(String serviceId, Long termId);

	
	
}
