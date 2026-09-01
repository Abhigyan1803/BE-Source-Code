package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AcademicCreditForExcellenceSubject;
import com.example.demo.repository.AcademicCreditForExcellenceSubjectRepository;
import com.example.demo.service.AcademicCreditForExcellenceSubjectService;

@Service
public class AcademicCreditForExcellenceSubjectServiceImpl implements AcademicCreditForExcellenceSubjectService {
	@Autowired
	public AcademicCreditForExcellenceSubjectRepository repo;

	@Override
	public List<AcademicCreditForExcellenceSubject> getBystatus(Integer status) {
		// TODO Auto-generated method stub
		List<AcademicCreditForExcellenceSubject> result = repo.findBystatus(status);

		return result;
	}

	@Override
	public AcademicCreditForExcellenceSubject getSubjectById(Long subjectId) {
		// TODO Auto-generated method stub
		Optional<AcademicCreditForExcellenceSubject> result = repo.findById(subjectId);
		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}

}
