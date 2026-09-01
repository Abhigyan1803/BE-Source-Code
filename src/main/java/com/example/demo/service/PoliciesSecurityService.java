package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.PoliciesSecurity;

public interface PoliciesSecurityService {

	PoliciesSecurity addDetails(PoliciesSecurity  record,MultipartFile docFile);
	
	PoliciesSecurity updateDetails(PoliciesSecurity record , MultipartFile docFile);
	
	List<PoliciesSecurity> getList(int status);
	
	PoliciesSecurity viewById(Long id);
	
	PoliciesSecurity changeStatus(int status , Long id);
}
