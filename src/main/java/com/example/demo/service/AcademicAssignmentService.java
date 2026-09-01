package com.example.demo.service;

import java.util.List;

import com.example.demo.model.AcademicAssignment;

public interface AcademicAssignmentService {

	public AcademicAssignment addAcademicAssignment(AcademicAssignment academicAssignment);

	public AcademicAssignment getById(Long id);

	public List<AcademicAssignment> getAcademicAssignment(String paper, String assignmentType, Long termId,
			Integer status);

	public AcademicAssignment updateAcademicAssignment(AcademicAssignment academicAssignment);

	public List<AcademicAssignment> getAcademicAssignmentAndAnswer(String paper, String assignmentType, Long termId,
			Integer status, String serviceId);

	public List<AcademicAssignment> getByTermId(Long termId, Integer status);

}
