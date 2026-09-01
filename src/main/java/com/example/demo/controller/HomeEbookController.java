package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Ebook;
import com.example.demo.model.GreyBook;
import com.example.demo.myexception.MyException;
import com.example.demo.service.HomeEbookService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/homeEbookController")
class HomeEbookController {
	
	@Autowired
	private HomeEbookService ebookService;
	
	@PostMapping("/add_ebook")
	public ResponseEntity<?> addEbook(@RequestBody Ebook ebook) {
		Ebook response = ebookService.addEbook(ebook);
		if(response!=null) {
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response), HttpStatus.OK);
	}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_ADD, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@GetMapping("/get_ebook_By_Id")
	public ResponseEntity<?> getEbookById(Long id) {
		Ebook response = ebookService.getEbookById(id);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@GetMapping("/getAll_ebook")
	public ResponseEntity<?> getAllEbook() {
		List<Ebook> response = ebookService.getAllEbook();
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PatchMapping(value = "/update_ebook")
	public ResponseEntity<?> updateEbook(@RequestBody Ebook ebook) throws MyException {
		Ebook response=ebookService.updateEbook(ebook);
		if(response!=null) {
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_UPDATE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

}
