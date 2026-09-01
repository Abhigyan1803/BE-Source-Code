package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AcademicLeadershipSubject;
import com.example.demo.repository.AcademicLeadershipSubjectRepository;
import com.example.demo.service.AcademicLeadershipSubjectService;

@Service
public class AcademicLeadershipSubjectServiceImpl implements AcademicLeadershipSubjectService {
	@Autowired
	public AcademicLeadershipSubjectRepository repo;

	@Override
	public List<AcademicLeadershipSubject> getBystatus(Integer status) {
		// TODO Auto-generated method stub
		List<AcademicLeadershipSubject> result = repo.findBystatus(status);

		return result;
	}

	@Override
	public AcademicLeadershipSubject getSubjectById(Long subjectId) {
		// TODO Auto-generated method stub
		Optional<AcademicLeadershipSubject> result = repo.findById(subjectId);
		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}

	@Override
	public List<AcademicLeadershipSubject> getBystatusOrderByIdDesc(Integer status) {
		List<AcademicLeadershipSubject> result = repo.findBystatusOrderByIdDesc(status);
		return result;
	}

}
