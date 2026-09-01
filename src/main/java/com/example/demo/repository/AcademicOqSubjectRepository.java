package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AcademicOqSubject;

@Repository
public interface AcademicOqSubjectRepository extends JpaRepository<AcademicOqSubject, Long> {

	// List<AcademicOqSubject> findAllByStatusOrderByIdDesc(Integer status);

	List<AcademicOqSubject> findAllByStatus(Integer status);

	List<AcademicOqSubject> findAllByStatusAndStatusNotIn(Integer status, Integer[] deletedStatus);

}
