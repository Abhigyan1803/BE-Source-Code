package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SectionHospital;

@Repository
public interface SectionHospitalRepo extends JpaRepository<SectionHospital, Long> {

	List<SectionHospital> findAllByStatusOrderByIdDesc(int status);

	List<SectionHospital> findAllByOrderByIdDesc();

}
