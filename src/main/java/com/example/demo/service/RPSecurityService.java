package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.RPSecurity;

public interface RPSecurityService {
	
	RPSecurity addDetails(RPSecurity  record,MultipartFile docFile);
	
	RPSecurity updateDetails(RPSecurity record , MultipartFile docFile);
	
	List<RPSecurity> getList(int status);
	
	RPSecurity viewById(Long id);
	
	RPSecurity changeStatus(int status , Long id);

}
