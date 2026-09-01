package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.TermSeasonService;

@RestController
@CrossOrigin
@RequestMapping("/termSeason")
public class AdminTermSeasonController {

	@Autowired
	TermSeasonService seasonService;
	
	@GetMapping("/getSeasonTerm")
	public Map<Object,Object> getAllSeasonTerms()
	{
		return seasonService.getAllTermSeaon();
		
	}
	
	
}
                                         