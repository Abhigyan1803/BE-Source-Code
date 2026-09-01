package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TRG_EQTNSubject;
import com.example.demo.repository.TRG_EQTNSubjectRepo;
import com.example.demo.service.TRG_EQTNSubjectService;

@Service
public class TRG_EQTNSubjectServiceImpl implements TRG_EQTNSubjectService {

	@Autowired
	public TRG_EQTNSubjectRepo repo;

//	@Override
//	public List<TRG_EQTNSubject> getBystatus(Integer status) {
//		// TODO Auto-generated method stub
//		List<TRG_EQTNSubject> result = repo.findBystatus(status);
//
//		return result;
//	}
	@Override
	public List<TRG_EQTNSubject> getByStatusAndTermId(Integer status, Long termId) {
		// return repo.findByStatusAndTermIdOrderByIdDesc(status, termId);
		return repo.findByStatusAndTermId(status, termId);

	}

	@Override
	public TRG_EQTNSubject getSubjectById(Long subjectId) {
		// TODO Auto-generated method stub
		Optional<TRG_EQTNSubject> result = repo.findById(subjectId);
		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}

}
