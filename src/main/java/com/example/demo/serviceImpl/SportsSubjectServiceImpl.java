package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SportsSubject;
import com.example.demo.repository.SportsSubjectRepo;
import com.example.demo.service.SportsSubjectService;

@Service
public class SportsSubjectServiceImpl implements SportsSubjectService {
	@Autowired
	public SportsSubjectRepo repo;

	@Override
	public List<SportsSubject> getByStatus(Integer status) {
		return repo.findByStatus(status);
	}

	@Override
	public Optional<SportsSubject> getSubjectById(Long subjectId) {
		return repo.findById(subjectId);
	}

	@Override
	public List<SportsSubject> getByStatusAndTermSession(Integer status, String termSession) {
		return repo.findByStatusAndTermSession(status, termSession);
	}
}
