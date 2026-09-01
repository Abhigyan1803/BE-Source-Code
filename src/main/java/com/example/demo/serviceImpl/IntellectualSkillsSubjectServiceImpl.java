package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.IntellectualSkillsSubject;
import com.example.demo.repository.IntellectualSkillsSubjectRepo;
import com.example.demo.service.IntellectualSkillsSubjectService;

@Service
public class IntellectualSkillsSubjectServiceImpl implements IntellectualSkillsSubjectService {
	@Autowired
	public IntellectualSkillsSubjectRepo repo;

	@Override
	public List<IntellectualSkillsSubject> getByStatusAndTermId(Integer status, Long termId) {
		return repo.findByStatusAndTermIdOrderById(status, termId);
	}

	@Override
	public IntellectualSkillsSubject getSubjectById(Long subjectId) {
		Optional<IntellectualSkillsSubject> result = repo.findById(subjectId);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

}
