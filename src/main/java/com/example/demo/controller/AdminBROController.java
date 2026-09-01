package com.example.demo.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.example.demo.model.BRO;
import com.example.demo.model.BRODocuments;
import com.example.demo.myexception.MyException;
import com.example.demo.service.AdminBROService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ConstantVar;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/BRO")
public class AdminBROController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdminBROService broService;

	@PostMapping(value = "/add-bro")
	public ResponseEntity<?> addBro(BRO bro, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam("broDoc") MultipartFile[] file, ServletRequest request) throws MyException {
		String doc = StringUtils.EMPTY;
		Set<BRODocuments> brodocs = new HashSet<>();

		for (MultipartFile multipartFile : file) {
			doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
			BRODocuments bror = new BRODocuments();
			bror.setBroDocument(url + doc);
			bror.setStatus(ConstantVar.ONE);
			brodocs.add(bror);
		}
		BRO response = broService.createBRO(bro, brodocs);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "BRO," + ConstantMessage.BRO_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.BRO_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-bro-list")
	public ResponseEntity<?> broList(@RequestParam String status) {
		List<BRO> list = broService.getAllBROList(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-bro")
	public ResponseEntity<?> getBROByID(@RequestParam Long id) {
		BRO list = broService.getBROById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-bro")
	public ResponseEntity<?> updateBRO(BRO bro, @RequestParam(value = "broDoc", required = false) MultipartFile[] file,
			ServletRequest request) throws MyException {
		String doc = StringUtils.EMPTY;
		Set<BRODocuments> brodocs = new HashSet<>();
		if (file != null) {
			for (MultipartFile multipartFile : file) {
				doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				BRODocuments bror = new BRODocuments();
				bror.setBroDocument(url + doc);
				bror.setStatus(ConstantVar.ONE);
				bror.setUpdatedAt(new Date());
				brodocs.add(bror);
			}
		}
		BRO response = broService.updateBRO(bro, brodocs);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",updated," + "BRO," + ConstantMessage.BRO_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.BRO_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

}
