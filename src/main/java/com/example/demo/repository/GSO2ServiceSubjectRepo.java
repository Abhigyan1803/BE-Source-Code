package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GSO2ServiceSubject;

@Repository
public interface GSO2ServiceSubjectRepo extends JpaRepository<GSO2ServiceSubject, Long> {

	List<GSO2ServiceSubject> findAllByOrderByIdDesc();

	List<GSO2ServiceSubject> findAllByTypeAndSubTypeAndTermIdOrderByIdDesc(String type, String subType, Long termId);

}
