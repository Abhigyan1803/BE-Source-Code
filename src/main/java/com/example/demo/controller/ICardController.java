package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ICard;
import com.example.demo.myexception.MyException;
import com.example.demo.service.ICardService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/ICard")
@CrossOrigin
public class ICardController {

	@Autowired
	private ICardService iCardService;

	@PostMapping(value = "/saveICard")
	public ResponseEntity<?> addICard(@RequestBody ICard iCard) throws MyException {
		ICard response = iCardService.createICard(iCard);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ICARD_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-ICard-By-id")
	public ResponseEntity<?> getICardById(@RequestParam Long id) throws Exception {
		ICard iCard = iCardService.getICardById(id);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, iCard),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-ICard-By-Status")
	public ResponseEntity<?> getICardByStatus(@RequestParam Integer status) throws Exception {
		List<ICard> iCard = iCardService.getICardByStatus(status);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, iCard),
				HttpStatus.OK);
	}

	@PutMapping(value = "/update-ICard")
	public ResponseEntity<?> updateICard(@RequestBody ICard iCard) throws MyException {
		ICard response = iCardService.updateICard(iCard);
		if(response != null) {
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ICARD_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(new ResponseMessage("RECORDS NOT FOUND", HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}
	
	@PutMapping(value = "/update-ICard-Status")
	public ResponseEntity<?> updateICardStatus(@RequestBody ICard iCard) throws MyException {
		ICard response = iCardService.updateICardStatus(iCard);
		if(response != null) {
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ICARD_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(new ResponseMessage("RECORDS NOT FOUND", HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}

}
