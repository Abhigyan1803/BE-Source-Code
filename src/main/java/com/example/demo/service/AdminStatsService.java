package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Intake;
import com.example.demo.model.POC;

public interface AdminStatsService {
	
	POC addPOCDetails(POC pocDetails,MultipartFile file);
	
	List<POC> getPOCList(int status);
	
	POC  viewPOCById(Long id);
	
	POC changePOCStatus(Long id , int status);
	
	POC updatePOCDetails(POC updateDetails,MultipartFile file);
	

    Intake addIntakeDetails(Intake intakeDetails,MultipartFile file);
	
	List<Intake> getIntakeList(int status);
	
	Intake  viewIntakeById(Long id);
	
	Intake changeIntakeStatus(Long id , int status);
	
	Intake updateIntakeDetails(Intake intakeDetails,MultipartFile file);
	
}
