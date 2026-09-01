package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CadetWeaponTrainingMainResult;

@Repository
public interface WeaponTrainingMainResultRepo extends JpaRepository<CadetWeaponTrainingMainResult, Long> {

	List<CadetWeaponTrainingMainResult> findByServiceIdAndTermId(String serviceId, Long termId);

}
