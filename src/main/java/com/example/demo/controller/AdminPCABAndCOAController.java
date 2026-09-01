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

import com.example.demo.model.PCABAndCOA;
import com.example.demo.myexception.MyException;
import com.example.demo.service.AdminPCABAndCOAService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/pcab-and-coa")
public class AdminPCABAndCOAController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdminPCABAndCOAService pcabService;

	@PostMapping(value = "/add-pcab-and-coa")
	public ResponseEntity<?> addPCABAndCOA(PCABAndCOA pcab, @RequestParam("pcabDoc") MultipartFile[] file,
			ServletRequest request) throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		for (MultipartFile multipartFile : file) {
			uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
			pcab.setDoc(url + uploaded_doc);
		}
		PCABAndCOA response = pcabService.createPCABAndCOA(pcab);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "pcab-and-coa," + ConstantMessage.PCA_COA_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.PCA_COA_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-pcab-and-coa-list")
	public ResponseEntity<?> getPCABAndCOAList(@RequestParam Integer status) {
		List<PCABAndCOA> list = pcabService.getAllPCABAndCOAList(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-pcab-and-coa")
	public ResponseEntity<?> getPCABAndCOAByID(@RequestParam Integer id) {
		PCABAndCOA list = pcabService.getPCABAndCOAById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-pcab-and-coa")
	public ResponseEntity<?> updatePCABAndCOA(PCABAndCOA pcab,
			@RequestParam(value = "pcabDoc", required = false) MultipartFile[] file, ServletRequest request)
			throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		if (file != null) {
			long length = file.length;
			System.err.println("file length " + length);
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				pcab.setDoc(url + uploaded_doc);
			}
		}
		PCABAndCOA response = pcabService.updatePCABAndCOA(pcab);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",updated," + "pcab-and-coa," + ConstantMessage.PCA_COA_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.PCA_COA_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

}
