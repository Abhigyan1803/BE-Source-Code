package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AcademicSyllabus;

public interface AcademicSyllabusService {

	AcademicSyllabus addAcademicSyllabus(AcademicSyllabus academicSyllabus, MultipartFile doc);

	AcademicSyllabus getById(Long id);

	List<AcademicSyllabus> getAcademicSyllabusList(Long termId, String paper, String subject, Integer status);

	AcademicSyllabus updateAcademicSyllabus(AcademicSyllabus academicSyllabus, MultipartFile doc);

	List<AcademicSyllabus> getByStatus(Integer status);

}
