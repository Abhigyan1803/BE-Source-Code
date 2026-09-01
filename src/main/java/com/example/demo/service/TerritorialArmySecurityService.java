package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.TerritorialArmySecurity;

public interface TerritorialArmySecurityService {

	TerritorialArmySecurity addDetails(TerritorialArmySecurity  record,MultipartFile docFile);
	
	TerritorialArmySecurity updateDetails(TerritorialArmySecurity record , MultipartFile docFile);
	
	List<TerritorialArmySecurity> getList(int status);
	
	TerritorialArmySecurity viewById(Long id);
	
	TerritorialArmySecurity changeStatus(int status , Long id);
	
}
