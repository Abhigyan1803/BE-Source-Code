package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Withdrawal;
import com.example.demo.myexception.MyException;
import com.example.demo.service.AdminWithdrawalService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/current-cases ")
public class AdminWithdrawalController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdminWithdrawalService withdrawalService;

	@PostMapping(value = "/add-withdrawal")
	public ResponseEntity<?> addWithdrawal(Withdrawal withdrawal, @RequestParam("withdrawalDoc") MultipartFile[] file,
			ServletRequest request) throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		for (MultipartFile multipartFile : file) {
			uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
			withdrawal.setDoc(url + uploaded_doc);
		}
		Withdrawal response = withdrawalService.createWithdrawal(withdrawal);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "withdrawal," + ConstantMessage.WITHDRAWAL_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.WITHDRAWAL_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-withdrawal-list")
	public ResponseEntity<?> getWithdrawalList(@RequestParam Integer status) {
		List<Withdrawal> list = withdrawalService.getAllWithdrawalList(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-withdrawal")
	public ResponseEntity<?> getWithdrawalByID(@RequestParam Integer id) {
		Withdrawal list = withdrawalService.getWithdrawalById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-withdrawal")
	public ResponseEntity<?> updateWithdrawal(Withdrawal withdrawal,
			@RequestParam(value = "withdrawalDoc", required = false) MultipartFile[] file, ServletRequest request)
			throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		if (file != null) {
			long length = file.length;
			System.err.println("file length " + length);
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				withdrawal.setDoc(url + uploaded_doc);
			}
		}
		Withdrawal response = withdrawalService.updateWithdrawal(withdrawal);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",updated," + "withdrawal," + ConstantMessage.WITHDRAWAL_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.WITHDRAWAL_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

}
