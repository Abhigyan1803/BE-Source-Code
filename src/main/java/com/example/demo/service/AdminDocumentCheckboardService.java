package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AviationList;
import com.example.demo.model.CavList;
import com.example.demo.model.PCList;
import com.example.demo.model.ParaList;
import com.example.demo.model.PendingCVRCases;
import com.example.demo.model.PendingConfirmationLineDirectorate;
import com.example.demo.model.PendingEducationDocs;

public interface AdminDocumentCheckboardService {

	//===============================Pending CVR Cases======================
	
	PendingCVRCases addCVR(PendingCVRCases record , MultipartFile docFile);
	
	PendingCVRCases updateCVR(PendingCVRCases record , MultipartFile docFile);
	
	PendingCVRCases viewCVR(Long id);
	
	List<PendingCVRCases> getCVRList(int status);
	
	PendingCVRCases changeCVRStatus(Long id , int status);

//===============================Pending Education Doc's======================
	PendingEducationDocs addEducationDoc(PendingEducationDocs record , MultipartFile docFile);
	
	PendingEducationDocs updateEducationDoc(PendingEducationDocs record , MultipartFile docFile);
	
	PendingEducationDocs viewEducationDoc(Long id);
	
	List<PendingEducationDocs> getEducationDocList(int status);
	
	PendingEducationDocs changeEducationDocStatus(Long id , int status);
	
//====================================Pending Confirmation Line Directorate====================
	PendingConfirmationLineDirectorate addLineDirectorate(PendingConfirmationLineDirectorate record , MultipartFile docFile);
	
	PendingConfirmationLineDirectorate updateLineDirectorate(PendingConfirmationLineDirectorate record , MultipartFile docFile);
	
	PendingConfirmationLineDirectorate viewLineDirectorate(Long id);
	
	List<PendingConfirmationLineDirectorate> getLineDirectorateList(int status);
	
	PendingConfirmationLineDirectorate changeLineDirectorateStatus(Long id , int status);
	
//=================================PC List======================================
	PCList addPCList(PCList record , MultipartFile docFile);
	
	PCList updatePCList(PCList record , MultipartFile docFile);
	
	PCList viewPCList(Long id);
	
	List<PCList> getPCList(int status);
	
	PCList changePCListStatus(Long id , int status);
	
//=================================AviationList List======================================
		AviationList addAviationList(AviationList record , MultipartFile docFile);
		
		AviationList updateAviationList(AviationList record , MultipartFile docFile);
		
		AviationList viewAviationList(Long id);
		
		List<AviationList> getAviationList(int status);
		
		AviationList changeAviationListStatus(Long id , int status);
	
//=================================Para List======================================
		ParaList addParaList(ParaList record , MultipartFile docFile);
		
		ParaList updateParaList(ParaList record , MultipartFile docFile);
		
		ParaList viewParaList(Long id);
		
		List<ParaList> getParaList(int status);
		
		ParaList changeParaListStatus(Long id , int status);
		
//=================================69 Cav List======================================
		CavList addCavList(CavList record , MultipartFile docFile);
		
		CavList updateCavList(CavList record , MultipartFile docFile);
		
		CavList viewCavList(Long id);
		
		List<CavList> getCavList(int status);
		
		CavList changeCavListStatus(Long id , int status);
}
