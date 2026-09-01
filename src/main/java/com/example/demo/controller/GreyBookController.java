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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.GreyBook;
import com.example.demo.myexception.MyException;
import com.example.demo.repository.GreyBookRepo;
import com.example.demo.service.GreyBookService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/greyBookController")
public class GreyBookController {

	@Autowired
	GreyBookService greyBookService;

	@Autowired
	GreyBookRepo greyBookRepo;
	
	@PostMapping(value = "/addGreyBook")
	public ResponseEntity<?> addGreyBook(@RequestBody GreyBook greyBook,ServletRequest request) throws MyException {
	
		GreyBook emailExist=greyBookRepo.findByEmail(greyBook.getEmail());
		GreyBook phoneExist=greyBookRepo.findByPhoneNumber(greyBook.getPhoneNumber());
		//GreyBook rankExist=greyBookRepo.findByEmail(greyBook.getEmail());
		if(emailExist!=null) 
		{
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.EMAIL_EXIST, HttpStatus.OK,null),
					HttpStatus.OK);
		}else if(phoneExist!=null) 
		{
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.PHONE_EXIST, HttpStatus.OK,null),
					HttpStatus.OK);
		}
//		else if(rankExist!=null) 
//		{
//			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK,null),
//					HttpStatus.OK);
//		}else 
		else{
			GreyBook response=greyBookService.addGreyBook(greyBook);
			FileWritting.createLog((HttpServletRequest)request ,response.getId()+ ",add,"+"addGreyBook,"+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY+","+ new Date());
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/getGreyBookRecords")
	public ResponseEntity<?> getGreyBookRecords(@RequestParam int status) {
		List<GreyBook> list = greyBookService.getAllGreyBookRecords(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/activeDeActiveGreyBook")
	public ResponseEntity<?> activeDeActiveGreyBook(@RequestParam Long id,int status,ServletRequest request) {
		GreyBook response = greyBookService.activeDeactiveGreyBookRecord(id,status);
		FileWritting.createLog((HttpServletRequest)request ,response.getId()+ ",status,"+"activeDeActiveGreyBook,"+ ConstantMessage.OK_MESSAGE+","+ new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK,response),
				HttpStatus.OK);
	}

	@PatchMapping(value = "/updateGreyBook")
	public ResponseEntity<?> updateGreyBook(@RequestBody GreyBook greyBook,ServletRequest request) throws MyException {
		    GreyBook response=greyBookService.updateGreyBookRecord(greyBook);
		    FileWritting.createLog((HttpServletRequest)request ,response.getId()+ ",update,"+"updateGreyBook,"+ ConstantMessage.RECORD_UPDATED_SUCCESSFULLY+","+ new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/getDetailsById")
	public ResponseEntity<?> getDetailsById(@RequestParam Long id) {
		GreyBook response = greyBookService.getGreyBookRecordById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK,response),
				HttpStatus.OK);
	}

	
}
