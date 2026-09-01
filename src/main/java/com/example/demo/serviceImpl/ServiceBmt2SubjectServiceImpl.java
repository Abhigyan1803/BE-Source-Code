package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ServiceBmt2Subject;
import com.example.demo.repository.ServiceBmt2SubjectRepository;
import com.example.demo.service.ServiceBmt2SubjectService;

@Service
public class ServiceBmt2SubjectServiceImpl implements ServiceBmt2SubjectService {

	@Autowired
	private ServiceBmt2SubjectRepository repo;

	@Override
	public List<ServiceBmt2Subject> getByStatus(Integer status) {
		// TODO Auto-generated method stub
		List<ServiceBmt2Subject> result = repo.findByStatus(status);
		return result;
	}

	@Override
	public ServiceBmt2Subject getSubjectById(Long subjectId) {
		// TODO Auto-generated method stub
		Optional<ServiceBmt2Subject> result = repo.findById(subjectId);
		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}

	@Override
	public List<ServiceBmt2Subject> getByStatusAndTermId(Integer status, Long termId) {
		List<ServiceBmt2Subject> result = repo.findByStatusAndTermId(status, termId);
		return result;
	}
}