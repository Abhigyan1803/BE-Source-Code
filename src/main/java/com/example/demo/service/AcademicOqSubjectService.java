package com.example.demo.service;

import java.util.List;

import com.example.demo.model.AcademicOqSubject;

public interface AcademicOqSubjectService {

	List<AcademicOqSubject> getAcademicOqSubjectList(Integer status);

	public AcademicOqSubject getSubjectById(Long id);

}
