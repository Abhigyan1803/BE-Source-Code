package com.example.demo.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CampDetails;
import com.example.demo.model.OqSubjectDetails;

@Repository
public interface OqSubjectDetailsRepo extends JpaRepository<OqSubjectDetails, Long> {

	Set<OqSubjectDetails> findByOqSubjectAttributeTermId(Long termId);

}
