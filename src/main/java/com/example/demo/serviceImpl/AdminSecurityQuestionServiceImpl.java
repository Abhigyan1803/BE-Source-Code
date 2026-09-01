package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SecurityQuestion;
import com.example.demo.repository.AdminSecurityQuestionRepo;
import com.example.demo.service.AdminSecurityQuestionService;

@Service
public class AdminSecurityQuestionServiceImpl implements AdminSecurityQuestionService {

	@Autowired
	AdminSecurityQuestionRepo questionRepo;

	@Override
	public List<SecurityQuestion> getAllQuestionList() {
		List<SecurityQuestion> list = questionRepo.findAllByOrderByIdDesc();
		return list;
	}

	@Override
	public SecurityQuestion getQuestionById(Long id) {
		Optional<SecurityQuestion> list = questionRepo.findById(id);
		return list.get();
	}

}
