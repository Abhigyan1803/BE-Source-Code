package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BDO;
import com.example.demo.model.CadetCampBnCdrResult;

@Repository
public interface CadetCampBnRepo extends JpaRepository<CadetCampBnCdrResult, Long>{

	Optional<CadetCampBnCdrResult> findByServiceIdAndTermIdAndCampAttributesId(String serviceId, Long termId, Integer id);

	List<CadetCampBnCdrResult> findByServiceIdAndTermId(String serviceId, Long termId);

}
