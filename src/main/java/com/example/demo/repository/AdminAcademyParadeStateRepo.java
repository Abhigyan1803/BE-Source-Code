package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AcademyParadeState;

@Repository
public interface AdminAcademyParadeStateRepo extends JpaRepository<AcademyParadeState, Integer> {

	List<AcademyParadeState> findAllByOrderByIdDesc();

	List<AcademyParadeState> findByStatusOrderByIdDesc(Integer status);

	List<AcademyParadeState> findByOrderByIdDesc();

}
