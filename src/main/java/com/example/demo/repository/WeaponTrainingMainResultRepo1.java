package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CadetWeaponTrainingMainResult1;
import com.example.demo.model.WeaponTrainingResult;

@Repository
public interface WeaponTrainingMainResultRepo1 extends JpaRepository<CadetWeaponTrainingMainResult1, Long> {

	
}
