package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.CadetCampPlCdrResult;


@Repository
public interface CadetCampPlRepo extends JpaRepository<CadetCampPlCdrResult,Long>{

	List<CadetCampPlCdrResult> findByServiceIdAndTermId(String serviceId, Long termId);

	Optional<CadetCampPlCdrResult> findByServiceIdAndTermIdAndCampAttributesId(String serviceId, Long termId, Integer id);

	
	
}
