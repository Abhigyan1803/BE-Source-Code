package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AcademicOqSubject;
import com.example.demo.repository.AcademicOqSubjectRepository;
import com.example.demo.service.AcademicOqSubjectService;

@Service
public class AcademicOqSubjectServiceImpl implements AcademicOqSubjectService {
	@Autowired
	private AcademicOqSubjectRepository repo;

	@Override
	public List<AcademicOqSubject> getAcademicOqSubjectList(Integer status) {
		// TODO Auto-generated method stub
		List<AcademicOqSubject> list = null;
		if (status == 1) {
			Integer[] deletedStatus = { 2 };
			list = repo.findAllByStatusAndStatusNotIn(status, deletedStatus);
		} else {
			list = null;
		}
		if (list.size() == 0) {
			list = null;
		}
		return list;
	}

	@Override
	public AcademicOqSubject getSubjectById(Long id) {
		// TODO Auto-generated method stub
		Optional<AcademicOqSubject> result = repo.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}
}
