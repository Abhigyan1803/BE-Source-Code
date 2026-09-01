package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CadetWeaponTrainingResult1;

@Repository
public interface WeaponTrainingResultRepo1 extends JpaRepository<CadetWeaponTrainingResult1, Long> {

}
