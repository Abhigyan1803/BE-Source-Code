package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CadetWeaponTrainingResult;

@Repository
public interface WeaponTrainingResultRepo extends JpaRepository<CadetWeaponTrainingResult, Long> {

	List<CadetWeaponTrainingResult> findByServiceIdAndTermId(String serviceId, Long termId);

	Optional<CadetWeaponTrainingResult> findByServiceIdAndTermIdAndAttributesId(String serviceId, Long termId,
			Long long1);

}
