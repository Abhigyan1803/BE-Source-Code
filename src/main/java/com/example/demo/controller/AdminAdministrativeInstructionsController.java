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

import com.example.demo.model.AdministrativeInstructions;
import com.example.demo.myexception.MyException;
import com.example.demo.service.AdminAdministrativeInstructionsService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/administrative-instructions")
public class AdminAdministrativeInstructionsController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdminAdministrativeInstructionsService instructionService;

	@PostMapping(value = "/add-instructions")
	public ResponseEntity<?> addParadeState(AdministrativeInstructions instruction,
			@RequestParam("instructionsDoc") MultipartFile[] file, ServletRequest request) throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		for (MultipartFile multipartFile : file) {
			uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
			instruction.setDoc(url + uploaded_doc);
		}
		AdministrativeInstructions response = instructionService.createInstruction(instruction);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added,"
				+ "administrative-instructions," + ConstantMessage.ADMINISTRATIVE_INSTRUCTION_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.ADMINISTRATIVE_INSTRUCTION_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-instructions-list")
	public ResponseEntity<?> getParadeStateList(@RequestParam Integer status) {
		List<AdministrativeInstructions> list = instructionService.getAllInstructionsList(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-instructions")
	public ResponseEntity<?> getParadeStateByID(@RequestParam Integer id) {
		AdministrativeInstructions list = instructionService.getInstructionById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-instructions")
	public ResponseEntity<?> updateInstructions(AdministrativeInstructions instruction,
			@RequestParam(value = "instructionsDoc", required = false) MultipartFile[] file, ServletRequest request)
			throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		if (file != null) {
			long length = file.length;
			System.err.println("file length " + length);
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				instruction.setDoc(url + uploaded_doc);
			}
		}
		AdministrativeInstructions response = instructionService.updateInstruction(instruction);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",updated," + "administrative-instructions,"
						+ ConstantMessage.ADMINISTRATIVE_INSTRUCTION_UPDATED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.ADMINISTRATIVE_INSTRUCTION_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

}
