package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.RespService;

@RestController
@CrossOrigin
@RequestMapping("/api/respController")
public class RespController {
	
	@Autowired
	RespService respService;
	

   @GetMapping("/getAllResp")
   public Map<Object,Object> getAllResp()
   {
	return respService.getAllResp();
   }

	
	
	
}