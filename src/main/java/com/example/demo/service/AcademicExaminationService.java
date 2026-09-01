package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AcademicExamination;

public interface AcademicExaminationService {

	public AcademicExamination addAcademicExam(AcademicExamination academicExam, MultipartFile doc);

	public AcademicExamination getById(Long id);

	public List<AcademicExamination> getAcademicExamList(String type, Long termId, Integer status);

	public AcademicExamination updateAcademicExamination(AcademicExamination academicExam, MultipartFile doc);
}
