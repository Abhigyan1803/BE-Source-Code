package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.SportsSubject;

@Service
public interface SportsSubjectService {

	List<SportsSubject> getByStatus(Integer status);

	// List<SportsSubject> getByStatusAndTermId(int i, Long termId);

	Optional<SportsSubject> getSubjectById(Long subjectId);

	List<SportsSubject> getByStatusAndTermSession(Integer status, String termSession);

}
