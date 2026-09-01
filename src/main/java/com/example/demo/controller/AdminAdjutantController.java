package com.example.demo.controller;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AdjutantDetails;
import com.example.demo.service.AdjutantDetailsService;

@RestController
@CrossOrigin
@RequestMapping("/adjutant")
public class AdminAdjutantController {
	
	@Autowired
	AdjutantDetailsService adService;
	
	@GetMapping("/getAdjutantBranches")
	public Map<Object,Object> getAllAdjutantBranches()
	{
		return adService.getAdjutantBranch();
	}
	
	@PostMapping("/addAdjudantDetails")
	public Map<Object,Object> addAdjutantDetails(AdjutantDetails details , MultipartFile doc,ServletRequest request)
	{
		return adService.addDetails(details, doc , request);
	}
	
	@PostMapping("/updateAdjutantDetails")
	public Map<Object,Object> updateAdjutantDetails(AdjutantDetails details , MultipartFile doc,ServletRequest request)
	{
		return adService.updateDetails(details, doc , request);
	}
	
	@GetMapping("/getAllAdjutantDetails")
	public Map<Object,Object> getAllAdjutantDetails()
	{
		return adService.getAllAdjutantDetails();
	}
	
	@PostMapping("/activeDeactiveStatus")
	public Map<Object,Object> activeDeactiveStatus(Long id , int status,ServletRequest request)
	{
		return adService.activeDeactiveStatus(id, status , request);
	}
	
	@PostMapping("/viewDetailsById")
	public Map<Object,Object> viewDetailsById(Long id)
	{
		return adService.getDetailsById(id);
	}
	@GetMapping("/getByAdjutantBranch")
	public Map<Object,Object> getRecordsByAdjutantBranch(Long id , int status,@RequestParam(required = false)boolean flag) //flag parameter added
	{
		return adService.getDetailsByAdjutantBranch(id,status,flag);
	}

}
