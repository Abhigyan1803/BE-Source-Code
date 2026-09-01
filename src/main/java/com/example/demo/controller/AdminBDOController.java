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

import com.example.demo.model.BDO;
import com.example.demo.model.BDODocuments;
import com.example.demo.myexception.MyException;
import com.example.demo.service.AdminBDOService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ConstantVar;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/BDO")
public class AdminBDOController {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdminBDOService bdoService;

	@PostMapping(value = "/add-bdo")
	public ResponseEntity<?> addBdo(BDO bdo, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			@RequestParam("bdoDoc") MultipartFile[] file, ServletRequest request) throws MyException {
		String doc = StringUtils.EMPTY;
		Set<BDODocuments> bdodocs = new HashSet<>();

		for (MultipartFile multipartFile : file) {
			doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
			BDODocuments bdor = new BDODocuments();
			bdor.setBdoDocument(url + doc);
			bdor.setStatus(ConstantVar.ONE);
			bdodocs.add(bdor);
		}
		BDO response = bdoService.createBDO(bdo, bdodocs);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "BDO," + ConstantMessage.BDO_ADDED + "," + new Date());
		// method
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.BDO_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-bdo-list")
	public ResponseEntity<?> bdoList(@RequestParam String status) {
		List<BDO> list = bdoService.getAllBDOList(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-bdo")
	public ResponseEntity<?> getBDOByID(@RequestParam Long id) {
		BDO list = bdoService.getBDOById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/update-bdo")
	public ResponseEntity<?> updateBDO(BDO bdo, @RequestParam(value = "bdoDoc", required = false) MultipartFile[] file,
			ServletRequest request) throws MyException {
		String doc = StringUtils.EMPTY;
		Set<BDODocuments> bdodocs = new HashSet<>();
		if (file != null) {
			for (MultipartFile multipartFile : file) {
				doc = FileUploader.uploadProfileImage(multipartFile, UploadDir);
				if (doc != null) {
					BDODocuments bdor = new BDODocuments();
					bdor.setBdoDocument(url + doc);
					bdor.setStatus(ConstantVar.ONE);
					bdor.setUpdatedAt(new Date());
					bdodocs.add(bdor);
				}

			}
			System.out.println(bdodocs);
		}
		BDO response = bdoService.updateBDO(bdo, bdodocs);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",updated," + "BDO," + ConstantMessage.BDO_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.BDO_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

}
