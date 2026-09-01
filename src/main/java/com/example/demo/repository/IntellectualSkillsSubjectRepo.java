package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.IntellectualSkillsSubject;

@Repository
public interface IntellectualSkillsSubjectRepo extends JpaRepository<IntellectualSkillsSubject, Long> {

	List<IntellectualSkillsSubject> findByStatusAndTermIdOrderById(Integer status, Long termId);

}
