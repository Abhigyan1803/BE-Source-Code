package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.AcademicCreditForExcellenceSubject;

@Service
public interface AcademicCreditForExcellenceSubjectService {

	public List<AcademicCreditForExcellenceSubject> getBystatus(Integer status);

	public AcademicCreditForExcellenceSubject getSubjectById(Long subjectId);

}
