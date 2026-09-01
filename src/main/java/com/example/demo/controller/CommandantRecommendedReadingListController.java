package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.CommandantRecommendedReadingList;
import com.example.demo.service.CommandantRecommendedReadingListService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@Controller
@CrossOrigin
@RequestMapping("/api/commandant-recommended-reading-list")
public class CommandantRecommendedReadingListController {

	@Autowired
	CommandantRecommendedReadingListService readingList;

	@PostMapping(value = "/add-recommended-book")
	public ResponseEntity<?> addRecommendedBook(@RequestBody CommandantRecommendedReadingList recommendedBook,
			ServletRequest request) {
		CommandantRecommendedReadingList response = readingList.addRecommendedBook(recommendedBook);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "commandant-recommended-reading-list,"
						+ ConstantMessage.COMMANDANT_RECOMMENDED_READING_LIST_ADDED + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.COMMANDANT_RECOMMENDED_READING_LIST_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-recommended-book-list")
	public ResponseEntity<?> getAllRecommendedBook(@RequestParam Integer status) {
		List<CommandantRecommendedReadingList> list = readingList.getAllRecommendedBook(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.COMMANDANT_RECOMMENDED_READING_LIST_ADDED, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-recommended-book")
	public ResponseEntity<?> getRecommendedBookById(@RequestParam Long id) {
		CommandantRecommendedReadingList list = readingList.getRecommendedBook(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.COMMANDANT_RECOMMENDED_READING_LIST_ADDED, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@PostMapping(value = "/update-recommended-book")
	public ResponseEntity<?> updateRecommendedBook(@RequestBody CommandantRecommendedReadingList recommendedBook,
			ServletRequest request) {
		CommandantRecommendedReadingList response = readingList.updateRecommendedBook(recommendedBook);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",updated," + "commandant-recommended-reading-list,"
						+ ConstantMessage.COMMANDANT_RECOMMENDED_READING_LIST_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.COMMANDANT_RECOMMENDED_READING_LIST_UPDATED,
				HttpStatus.OK, response), HttpStatus.OK);
	}
}
