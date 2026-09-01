package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.PoliciesInfoSecurity;
import com.example.demo.model.ReportsInfoSecurity;

public interface InfoSecurityService {
	
	//==================Policies Info Security==================================
	
		PoliciesInfoSecurity addPoliciesDetails(PoliciesInfoSecurity  record,MultipartFile docFile);
			
		PoliciesInfoSecurity updatePoliciesDetails(PoliciesInfoSecurity record , MultipartFile docFile);
		
		List<PoliciesInfoSecurity> getPoliciesList(int status);
		
		PoliciesInfoSecurity viewPoliciesById(Long id);
		
		PoliciesInfoSecurity changePolicyStatus(int status , Long id);
		
	//============================Reports Info Security========================================

		ReportsInfoSecurity addReportDetails(ReportsInfoSecurity  record,MultipartFile docFile);
		
		ReportsInfoSecurity updateReportDetails(ReportsInfoSecurity record , MultipartFile docFile);
		
		List<ReportsInfoSecurity> getReportList(int status);
		
		ReportsInfoSecurity viewReportById(Long id);
		
		ReportsInfoSecurity changeReportStatus(int status , Long id);
		

}
