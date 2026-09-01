package com.example.demo.service;

import java.util.List;

import com.example.demo.model.AcademicTerm;

public interface AcademicTermService {

	public AcademicTerm addAcademicTerm(AcademicTerm academic);

	public AcademicTerm getById(Long id);

	public List<AcademicTerm> getAcademicTermList(String paper, Long termId, String subjectName);
//	public List<AcademicTerm> getAcademicTermList(String paper, String subjectName);
	public AcademicTerm updateAcademicTerm(AcademicTerm academic);

	public List<AcademicTerm> getAcademicTermListByTermIdAndSubject(String paper, Long termId, String subjectName);
}
