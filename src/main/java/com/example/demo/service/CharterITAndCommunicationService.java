package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.CommunicationSecCharter;
import com.example.demo.model.ITSecCharter;

public interface CharterITAndCommunicationService {
	
	
	//==================IT Sec Charter IT and Communication==================================
	
		ITSecCharter addITDetails(ITSecCharter  record,MultipartFile docFile);
				
		ITSecCharter updateITDetails(ITSecCharter record , MultipartFile docFile);
		
		List<ITSecCharter> getITList(int status);
		
		ITSecCharter viewITById(Long id);
		
		ITSecCharter changeITStatus(int status , Long id);
		
	//============================Communication Sec Charter IT and Communication========================================

		CommunicationSecCharter addCommunicationDetails(CommunicationSecCharter  record,MultipartFile docFile);
		
		CommunicationSecCharter updateCommunicationDetails(CommunicationSecCharter record , MultipartFile docFile);
		
		List<CommunicationSecCharter> getCommunicationList(int status);
		
		CommunicationSecCharter viewCommunicationById(Long id);
		
		CommunicationSecCharter changeCommunicationStatus(int status , Long id);

}
