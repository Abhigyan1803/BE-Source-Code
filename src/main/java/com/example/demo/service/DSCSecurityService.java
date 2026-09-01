package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.DSCSecurity;

public interface DSCSecurityService {

	DSCSecurity addDetails(DSCSecurity  record,MultipartFile docFile);
	
	DSCSecurity updateDetails(DSCSecurity record , MultipartFile docFile);
	
	List<DSCSecurity> getList(int status);
	
	DSCSecurity viewById(Long id);
	
	DSCSecurity changeStatus(int status , Long id);

}
