package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CampSubjectDetails;

public interface CampSubjectDetailsService {

	CampSubjectDetails createSubject(CampSubjectDetails campSubjectDetails);

	// Set<CampSubjectDetails> getSubjectByTerm(Long termId);

	CampSubjectDetails getSubjectById(Long id);

	List<CampSubjectDetails> getAllSubjectByStatus(Integer status);

	CampSubjectDetails updateSubject(CampSubjectDetails campSubjectDetails);

	CampSubjectDetails findbySubject(String subjectName);

	CampSubjectDetails validateSubjectExist(CampSubjectDetails campSubjectDetails);

	List<CampSubjectDetails> getAllSubjectByStatusOrderByIdDesc(Integer status);

}
