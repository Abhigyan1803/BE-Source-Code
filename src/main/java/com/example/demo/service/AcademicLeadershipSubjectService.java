package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.AcademicLeadershipSubject;

@Service
public interface AcademicLeadershipSubjectService {

	public List<AcademicLeadershipSubject> getBystatus(Integer status);

	public AcademicLeadershipSubject getSubjectById(Long subjectId);

	public List<AcademicLeadershipSubject> getBystatusOrderByIdDesc(Integer status);

}
