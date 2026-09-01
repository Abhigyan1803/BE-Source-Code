package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Syllabus;

@Repository
public interface AdminSyllabusRepo extends JpaRepository<Syllabus, Integer> {

	List<Syllabus> findAllByStatusAndSyllabusType(Integer one, String type);

	List<Syllabus> findAllByStatusAndSyllabusTypeOrderByIdDesc(Integer one, String type);

	List<Syllabus> findAllBySyllabusTypeOrderByIdDesc(String type);

	List<Syllabus> findAllByStatusAndSyllabusTypeAndTermIdOrderByIdDesc(Integer status, String type, Long termId);

	List<Syllabus> findAllBySyllabusTypeAndTermIdOrderByIdDesc(String type, Long termId);

}
