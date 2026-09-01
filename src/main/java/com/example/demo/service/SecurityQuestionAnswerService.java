package com.example.demo.service;

import java.util.List;

import com.example.demo.model.AuthTable;
import com.example.demo.model.ForgetPasswordAnswers;

public interface SecurityQuestionAnswerService {

	List<ForgetPasswordAnswers> createAnswer(List<ForgetPasswordAnswers> answer);

	ForgetPasswordAnswers updateAnswer(ForgetPasswordAnswers answer);

	AuthTable updatePassword(AuthTable password);

	ForgetPasswordAnswers checkAnswer(ForgetPasswordAnswers securityAnswer);

}
