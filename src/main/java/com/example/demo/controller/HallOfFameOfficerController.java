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

import com.example.demo.model.HallOfFameOfficer;
import com.example.demo.myexception.MyException;
import com.example.demo.service.HallOfFameOfficerService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/hall-of-fame")

public class HallOfFameOfficerController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	HallOfFameOfficerService hofService;

	@PostMapping(value = "/add-officer-fame")
	public ResponseEntity<?> addOfficerFame(HallOfFameOfficer hallOfFameOfficer,
			@RequestParam(value = "Imageofficer", required = false) MultipartFile[] file, ServletRequest request) {
		String uploaded_doc = StringUtils.EMPTY;
		if (file != null) {
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				hallOfFameOfficer.setOfficerImage(url + uploaded_doc);
			}
		}
		HallOfFameOfficer response = hofService.addOfficerFame(hallOfFameOfficer);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "hall-of-fame," + ConstantMessage.FAME_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAME_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-fame-list")
	public ResponseEntity<?> getOfficerFameList(@RequestParam Integer status) throws MyException {
		List<HallOfFameOfficer> list = hofService.getOfficerFameList(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-fame")
	public ResponseEntity<?> getOfficerFameByID(@RequestParam Integer id) throws MyException {
		HallOfFameOfficer list = hofService.getOfficerFameById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-officer-fame")
	public ResponseEntity<?> updateOfficerFame(HallOfFameOfficer hallOfFameOfficer,
			@RequestParam(value = "ImageOfficer", required = false) MultipartFile[] file, ServletRequest request)
			throws MyException {
		String uploaded_doc = StringUtils.EMPTY;
		if (file != null) {
			long length = file.length;
			System.err.println("file length " + length);
			for (MultipartFile multipartFile : file) {
				uploaded_doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				hallOfFameOfficer.setOfficerImage(url + uploaded_doc);
			}
		}
		HallOfFameOfficer response = hofService.updateOfficerFame(hallOfFameOfficer);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",updated," + "hall-of-fame," + ConstantMessage.FAME_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAME_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-param-veer-fame-list")
	public ResponseEntity<?> getParamVeerAwardedFameList(@RequestParam(defaultValue = "0") Integer fameCounrty,
			@RequestParam(defaultValue = "Param veer") String awardName,
			@RequestParam(defaultValue = "1") Integer status) throws MyException {
		List<HallOfFameOfficer> list = hofService.getAwardedIndianOfficerFameList(fameCounrty, awardName, status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-officers-fame-list")
	public ResponseEntity<?> getAwardedOfficerFameList(@RequestParam(defaultValue = "0") Integer fameCounrty,
			@RequestParam String awardName, @RequestParam(defaultValue = "1") Integer status) throws MyException {
		List<HallOfFameOfficer> list = hofService.getAwardedIndianOfficerFameList(fameCounrty, awardName, status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

//	@GetMapping(value = "/get-indian-officers-fame-list")
//	public ResponseEntity<?> getAwardedIndianOfficerFameList(String awardName) {
//		List<HallOfFameOfficer> list = hofService.getAwardedIndianOfficerFameList(awardName);
//		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
//				HttpStatus.OK);
//	}
//
//	@GetMapping(value = "/get-alumni-officers-fame-list")
//	public ResponseEntity<?> getAwardedForeignerOfficerFameList(String awardName) {
//		List<HallOfFameOfficer> list = hofService.getAwardedForeignerOfficerFameList(awardName);
//		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
//				HttpStatus.OK);
//	}
}
