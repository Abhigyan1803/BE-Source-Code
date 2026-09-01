package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.IntellectualSkillsSubject;

@Service
public interface IntellectualSkillsSubjectService {

	List<IntellectualSkillsSubject> getByStatusAndTermId(Integer status, Long termId);

	IntellectualSkillsSubject getSubjectById(Long subjectId);

}
