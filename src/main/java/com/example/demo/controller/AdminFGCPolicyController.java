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

import com.example.demo.model.FGCPolicy;
import com.example.demo.myexception.MyException;
import com.example.demo.service.AdminFGCPolicyService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/fgc-policy")
public class AdminFGCPolicyController {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdminFGCPolicyService fgcPolicyService;

	@PostMapping(value = "/add-policy")
	public ResponseEntity<?> addFGCPolicy(FGCPolicy policy, @RequestParam("policyDoc") MultipartFile[] file,
			ServletRequest request) throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		for (MultipartFile multipartFile : file) {
			uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
			policy.setDoc(url + uploaded_doc);
		}
		FGCPolicy response = fgcPolicyService.createPolicy(policy);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "fgc-policy," + ConstantMessage.FGC_POLICY_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FGC_POLICY_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-policy-list")
	public ResponseEntity<?> getFGCPolicyList(@RequestParam Integer status) {
		List<FGCPolicy> list = fgcPolicyService.getAllFGCPolicyList(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-policy")
	public ResponseEntity<?> getFGCPolicyByID(@RequestParam Integer id) {
		FGCPolicy list = fgcPolicyService.getFGCPolicyById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-policy")
	public ResponseEntity<?> updateFGCPolicy(FGCPolicy policy,
			@RequestParam(value = "policyDoc", required = false) MultipartFile[] file, ServletRequest request)
			throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		if (file != null) {
			long length = file.length;
			System.err.println("file length " + length);
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				policy.setDoc(url + uploaded_doc);
			}
		}
		FGCPolicy response = fgcPolicyService.updatePolicy(policy);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",updated," + "fgc-policy," + ConstantMessage.FGC_POLICY_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FGC_POLICY_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

}
