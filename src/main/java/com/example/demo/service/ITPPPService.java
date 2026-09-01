package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.ITPPP;

public interface ITPPPService {
	
	ITPPP addDetails(ITPPP records , MultipartFile file);
	
	ITPPP updateDetails(ITPPP records , MultipartFile file);
	
	ITPPP changeStatus(Long id , int status);
	
	ITPPP viewById(Long id);
	
	List<ITPPP> getList(int status);

}
