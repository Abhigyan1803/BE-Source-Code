package com.example.demo.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AdjutantDetails;

public interface AdjutantDetailsService {
	
	Map<Object,Object> getAdjutantBranch();
	
	Map<Object,Object> addDetails(AdjutantDetails details,MultipartFile document,ServletRequest request);
	
	Map<Object ,Object> getAllAdjutantDetails();
	
	Map<Object,Object> getDetailsById(Long id);
	
	Map<Object,Object> activeDeactiveStatus(Long id ,int status,ServletRequest request);
	
	Map<Object,Object> updateDetails(AdjutantDetails details,MultipartFile document,ServletRequest request);
	
	Map<Object,Object> getDetailsByAdjutantBranch(Long id,int status, boolean flag);
	

}
