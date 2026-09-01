package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.MediaCategory;
import com.example.demo.service.MediaCategoryService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/mediaController")
public class MediaCategoryController {

   @Autowired	
   MediaCategoryService mediaCategoryService;	
   
	@GetMapping(value = "/getAllMediaCategories")
	public ResponseEntity<?> getAllMediaCategories() {
		List<MediaCategory> list=mediaCategoryService.getAllMediaCategories(); 
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

}
