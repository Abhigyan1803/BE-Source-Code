package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.WeaponTrainingResult;

@Repository
public interface CadetWeaponTrainingResultRepo extends JpaRepository<WeaponTrainingResult, Long> {

	WeaponTrainingResult findByServiceIdAndTermId(String serviceId, Long termId);

	List<WeaponTrainingResult> findByServiceIdOrderByTermId(String serviceId);

}
