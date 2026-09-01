package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EdossierPtSubject;

@Repository
public interface EdossierPtSubjectRepository extends JpaRepository<EdossierPtSubject, Long> {

	List<EdossierPtSubject> findAllByStatusAndSubjectTypeAndTermId(Integer status, String subjectType, Long termId);

	List<EdossierPtSubject> findByStatus(Integer status);

}
