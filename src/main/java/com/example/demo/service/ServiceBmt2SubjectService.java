package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ServiceBmt2Subject;

public interface ServiceBmt2SubjectService {

	List<ServiceBmt2Subject> getByStatus(Integer status);

	ServiceBmt2Subject getSubjectById(Long subjectId);

	List<ServiceBmt2Subject> getByStatusAndTermId(Integer status, Long termId);

}
