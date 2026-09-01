package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AuthTable;
import com.example.demo.model.ForgetPasswordAnswers;
import com.example.demo.myexception.MyException;
import com.example.demo.service.SecurityQuestionAnswerService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/answer")
public class SecurityQuestionAnswerController {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	SecurityQuestionAnswerService answerService;

	@PostMapping(value = "/add-answer")
	public ResponseEntity<?> addAnswer(@RequestBody List<ForgetPasswordAnswers> answer , ServletRequest request) throws MyException {

		List<ForgetPasswordAnswers> response = answerService.createAnswer(answer);
		 FileWritting.createLog((HttpServletRequest)request ,response.get(0).getServiceId()+ ",add,"+"answer,"+ ConstantMessage.ANSWER_ADDED+","+ new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ANSWER_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@PatchMapping(value = "/update-answer")
	public ResponseEntity<?> updateAnswer(@RequestBody ForgetPasswordAnswers answer,ServletRequest request) throws MyException {
		ForgetPasswordAnswers response = answerService.updateAnswer(answer);
		FileWritting.createLog((HttpServletRequest)request ,response.getId()+ ",update,"+"answer,"+ ConstantMessage.ANSWER_UPDATED+","+ new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ANSWER_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@PatchMapping(value = "/update-password")
	public ResponseEntity<?> updatePassword(@RequestBody AuthTable password,ServletRequest request) throws MyException {
		AuthTable response = answerService.updatePassword(password);
		FileWritting.createLog((HttpServletRequest)request ,response.getLoginId()+ ",update,"+"password,"+ ConstantMessage.PASSWORD_UPDATED+","+ new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.PASSWORD_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@PostMapping(value = "/check-security-answer")
	public ResponseEntity<?> checkAnswer(@RequestBody ForgetPasswordAnswers securityAnswer) throws MyException {

		ForgetPasswordAnswers response = answerService.checkAnswer(securityAnswer);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ANSWER_MATCH, HttpStatus.OK),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ANSWER_UNMATCH, HttpStatus.OK),
					HttpStatus.OK);
		}
	}

}
