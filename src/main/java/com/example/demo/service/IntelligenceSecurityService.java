package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.PoliciesIntelligenceSecurity;
import com.example.demo.model.ReportsIntelligenceSecurity;

public interface IntelligenceSecurityService {
	
	//==================Policies Intelligence Security==================================
	
	PoliciesIntelligenceSecurity addPoliciesDetails(PoliciesIntelligenceSecurity  record,MultipartFile docFile);
		
	PoliciesIntelligenceSecurity updatePoliciesDetails(PoliciesIntelligenceSecurity record , MultipartFile docFile);
	
	List<PoliciesIntelligenceSecurity> getPoliciesList(int status);
	
	PoliciesIntelligenceSecurity viewPoliciesById(Long id);
	
	PoliciesIntelligenceSecurity changePolicyStatus(int status , Long id);
	
//============================Reports Intelligence Security========================================

	ReportsIntelligenceSecurity addReportDetails(ReportsIntelligenceSecurity  record,MultipartFile docFile);
	
	ReportsIntelligenceSecurity updateReportDetails(ReportsIntelligenceSecurity record , MultipartFile docFile);
	
	List<ReportsIntelligenceSecurity> getReportList(int status);
	
	ReportsIntelligenceSecurity viewReportById(Long id);
	
	ReportsIntelligenceSecurity changeReportStatus(int status , Long id);
	

}
