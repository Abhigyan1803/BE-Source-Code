package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EdossierPtSubject;
import com.example.demo.repository.EdossierPtSubjectRepository;
import com.example.demo.service.EdossierPtSubjectService;

@Service
public class EdossierPtSubjectServiceImpl implements EdossierPtSubjectService {

	@Autowired
	private EdossierPtSubjectRepository repo;

	@Override
	public List<EdossierPtSubject> getPtSubjectList(Integer status, String subjectType, Long termId) {
		// TODO Auto-generated method stub
		List<EdossierPtSubject> list = null;
		if (status == 1) {
			list = repo.findAllByStatusAndSubjectTypeAndTermId(status, subjectType, termId);
		} else {
			list = null;
		}
		if (list.size() == 0) {
			list = null;
		}
		return list;
	}

	@Override
	public EdossierPtSubject getSubjectById(Long id) {
		// TODO Auto-generated method stub
		Optional<EdossierPtSubject> result = repo.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public List<EdossierPtSubject> getAllSubjectByStatus(Integer status) {
		// TODO Auto-generated method stub
		List<EdossierPtSubject> list = null;
		if (status == 1) {
			list = repo.findByStatus(status);
		} else {
			list = repo.findAll();
		}
		return list;

	}

}
