package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.ACSFP;
import com.example.demo.model.CommunicationInfra;
import com.example.demo.model.OtherSecurityInfra;
import com.example.demo.model.SRESecurity;

public interface SecurityApparatusService {
	
//==================SRESecurity ================================	
	
	SRESecurity addSREDetails(SRESecurity  record,MultipartFile docFile);
	
	SRESecurity updateSREDetails(SRESecurity record , MultipartFile docFile);
	
	List<SRESecurity> getSREList(int status);
	
	SRESecurity viewSREById(Long id);
	
	SRESecurity changeSREStatus(int status , Long id);
	
//===================ACSFP============================================
	ACSFP addACSFPDetails(ACSFP  record,MultipartFile docFile);
	
	ACSFP updateACSFPDetails(ACSFP record , MultipartFile docFile);
	
	List<ACSFP> getACSFPList(int status);
	
	ACSFP viewACSFPById(Long id);
	
	ACSFP changeACSFPStatus(int status , Long id);
	
//================OtherSecurityInfra======================================
	OtherSecurityInfra addOtherInfraDetails(OtherSecurityInfra  record,MultipartFile docFile);
	
	OtherSecurityInfra updateOtherInfraDetails(OtherSecurityInfra record , MultipartFile docFile);
	
	List<OtherSecurityInfra> getOtherInfraList(int status);
	
	OtherSecurityInfra viewOtherInfraById(Long id);
	
	OtherSecurityInfra changeOtherInfraStatus(int status , Long id);
	
//========================CommunicationInfra============================
	CommunicationInfra addCommunicationInfraDetails(CommunicationInfra  record,MultipartFile docFile);
	
	CommunicationInfra updateCommunicationInfraDetails(CommunicationInfra record , MultipartFile docFile);
	
	List<CommunicationInfra> getCommunicationInfraList(int status);
	
	CommunicationInfra viewCommunicationInfraById(Long id);
	
	CommunicationInfra changeCommunicationInfraStatus(int status , Long id);
	

}
