package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.PoliciesProphylacticSecurity;
import com.example.demo.model.ReportsProphylacticSecurity;

public interface ProphylacticSecurityService {
	
	//==================Policies Prophylactic Security==================================
	
			PoliciesProphylacticSecurity addPoliciesDetails(PoliciesProphylacticSecurity  record,MultipartFile docFile);
				
			PoliciesProphylacticSecurity updatePoliciesDetails(PoliciesProphylacticSecurity record , MultipartFile docFile);
			
			List<PoliciesProphylacticSecurity> getPoliciesList(int status);
			
			PoliciesProphylacticSecurity viewPoliciesById(Long id);
			
			PoliciesProphylacticSecurity changePolicyStatus(int status , Long id);
			
		//============================Reports Prophylactic Security========================================

			ReportsProphylacticSecurity addReportDetails(ReportsProphylacticSecurity  record,MultipartFile docFile);
			
			ReportsProphylacticSecurity updateReportDetails(ReportsProphylacticSecurity record , MultipartFile docFile);
			
			List<ReportsProphylacticSecurity> getReportList(int status);
			
			ReportsProphylacticSecurity viewReportById(Long id);
			
			ReportsProphylacticSecurity changeReportStatus(int status , Long id);
			

}
