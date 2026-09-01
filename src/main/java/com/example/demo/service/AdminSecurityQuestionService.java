package com.example.demo.service;

import java.util.List;

import com.example.demo.model.SecurityQuestion;

public interface AdminSecurityQuestionService {

	List<SecurityQuestion> getAllQuestionList();

	SecurityQuestion getQuestionById(Long id);

}
