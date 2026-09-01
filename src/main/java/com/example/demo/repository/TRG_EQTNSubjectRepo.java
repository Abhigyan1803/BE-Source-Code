package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.TRG_EQTNSubject;

public interface TRG_EQTNSubjectRepo extends JpaRepository<TRG_EQTNSubject, Long> {

	List<TRG_EQTNSubject> findBystatus(Integer status);

	List<TRG_EQTNSubject> findByStatusAndTermIdOrderByIdDesc(Integer status, Long termId);

	List<TRG_EQTNSubject> findByStatusAndTermId(Integer status, Long termId);
}
