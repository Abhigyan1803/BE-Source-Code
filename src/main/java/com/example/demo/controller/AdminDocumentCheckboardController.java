package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AviationList;
import com.example.demo.model.CavList;
import com.example.demo.model.PCList;
import com.example.demo.model.ParaList;
import com.example.demo.model.PendingCVRCases;
import com.example.demo.model.PendingConfirmationLineDirectorate;
import com.example.demo.model.PendingEducationDocs;
import com.example.demo.service.AdminDocumentCheckboardService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/documentCheckboard")
public class AdminDocumentCheckboardController {
	
	@Autowired
	AdminDocumentCheckboardService docCheckboardService;
	
	@PostMapping("/add-CVR")
	public ResponseEntity<?> addCVRDetails(PendingCVRCases details , MultipartFile docfile , ServletRequest request)
	{
		
		PendingCVRCases response = docCheckboardService.addCVR(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-CVR,"
				+ ConstantMessage.CVR_ADDED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.CVR_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-CVR")
	public ResponseEntity<?> updateCVRDetails(PendingCVRCases details , MultipartFile docfile , ServletRequest request)
	{
		
		PendingCVRCases response = docCheckboardService.updateCVR(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-CVR,"
				+ ConstantMessage.CVR_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.CVR_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-CVR-status")
	public ResponseEntity<?> changeCVRStatus(Long id , int status,ServletRequest request)
	{
		
		PendingCVRCases response = docCheckboardService.changeCVRStatus(id, status);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-CVR-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-CVR")
	public ResponseEntity<?> viewCVRById(Long id)
	{
		PendingCVRCases response = docCheckboardService.viewCVR(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-CVR-list")
	public ResponseEntity<?> getCVRList(int status)
	{
		List<PendingCVRCases> response = docCheckboardService.getCVRList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
//=======================Pending Education Doc =====================================================
	@PostMapping("/add-education-doc")
	public ResponseEntity<?> addEducationDoc(PendingEducationDocs details , MultipartFile docfile , ServletRequest request)
	{
		
		PendingEducationDocs response = docCheckboardService.addEducationDoc(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-education-doc,"
				+ ConstantMessage.EDUCATION_DOC_ADDED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.EDUCATION_DOC_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/update-education-doc")
	public ResponseEntity<?> updateEducationDoc(PendingEducationDocs details , MultipartFile docfile , ServletRequest request)
	{
		
		PendingEducationDocs response = docCheckboardService.updateEducationDoc(details, docfile);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-education-doc,"
				+ ConstantMessage.EDUCATION_DOC_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.EDUCATION_DOC_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/change-education-doc-status")
	public ResponseEntity<?> changeEducationDocStatus(Long id , int status,ServletRequest request)
	{
		
		PendingEducationDocs response = docCheckboardService.changeEducationDocStatus(id, status);
		FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-education-doc-status,"
				+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PostMapping("/view-education-doc")
	public ResponseEntity<?> viewEducationDocById(Long id)
	{
		PendingEducationDocs response = docCheckboardService.viewEducationDoc(id);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	@PostMapping("/get-education-doc-list")
	public ResponseEntity<?> getEducationDocList(int status)
	{
		List<PendingEducationDocs> response = docCheckboardService.getEducationDocList(status);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	//=======================Pending Confirmation Line Directorate =====================================================
		@PostMapping("/add-line-directorate")
		public ResponseEntity<?> addLineDirectorate(PendingConfirmationLineDirectorate details , MultipartFile docfile , ServletRequest request)
		{
			
			PendingConfirmationLineDirectorate response = docCheckboardService.addLineDirectorate(details, docfile);
			FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-line-directorate,"
					+ ConstantMessage.LINE_DIRECTORATE_ADDED_SUCCESSFULLY + "," + new Date());
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.LINE_DIRECTORATE_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		
		@PostMapping("/update-line-directorate")
		public ResponseEntity<?> updateLineDirectorate(PendingConfirmationLineDirectorate details , MultipartFile docfile , ServletRequest request)
		{
			
			PendingConfirmationLineDirectorate response = docCheckboardService.updateLineDirectorate(details, docfile);
			FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-line-directorate,"
					+ ConstantMessage.LINE_DIRECTORATE_UPDATED_SUCCESSFULLY + "," + new Date());
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.LINE_DIRECTORATE_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		
		@PostMapping("/change-line-directorate-status")
		public ResponseEntity<?> changeLineDirectorateStatus(Long id , int status,ServletRequest request)
		{
			
			PendingConfirmationLineDirectorate response = docCheckboardService.changeLineDirectorateStatus(id, status);
			FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-line-directorate-status,"
					+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		
		@PostMapping("/view-line-directorate")
		public ResponseEntity<?> viewLineDirectorateById(Long id)
		{
			PendingConfirmationLineDirectorate response = docCheckboardService.viewLineDirectorate(id);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		@PostMapping("/get-line-directorate-list")
		public ResponseEntity<?> getLineDirectorateList(int status)
		{
			List<PendingConfirmationLineDirectorate> response = docCheckboardService.getLineDirectorateList(status);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
//=================================PC List======================================
		@PostMapping("/add-PCList")
		public ResponseEntity<?> addPCList(PCList record , MultipartFile docFile , ServletRequest request )
		{
			PCList response = docCheckboardService.addPCList(record, docFile);
			FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-PCList,"
					+ ConstantMessage.PCLIST_ADDED_SUCCESSFULLY + "," + new Date());
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.PCLIST_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		@PostMapping("/update-PCList")
		public ResponseEntity<?> updatePCList(PCList record , MultipartFile docFile , ServletRequest request)
		{
			PCList response = docCheckboardService.updatePCList(record, docFile);
			FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-PCList,"
					+ ConstantMessage.PCLIST_UPDATED_SUCCESSFULLY + "," + new Date());
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.PCLIST_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		
		@PostMapping("/view-PCList-byId")
		public ResponseEntity<?> viewPCList(Long id)
		{
			PCList response = docCheckboardService.viewPCList(id);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		
		@PostMapping("/get-PCList")
		public ResponseEntity<?> getPCList(int status)
		{
			List<PCList> response = docCheckboardService.getPCList(status);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		
		@PostMapping("/change-PCList-status")
		public ResponseEntity<?> changePCListStatus(Long id , int status ,ServletRequest request)
		{
			PCList response = docCheckboardService.changePCListStatus(id, status);
			FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-PCList-status,"
					+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
//=================================AviationList List======================================
		@PostMapping("/add-AviationList")
		public ResponseEntity<?> addAviationList(AviationList record , MultipartFile docFile , ServletRequest request )
		{
			AviationList response = docCheckboardService.addAviationList(record, docFile);
			FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-AviationList,"
					+ ConstantMessage.AVIATIONLIST_ADDED_SUCCESSFULLY + "," + new Date());
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.AVIATIONLIST_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		@PostMapping("/update-AviationList")
		public ResponseEntity<?> updateAviationList(AviationList record , MultipartFile docFile , ServletRequest request)
		{
			AviationList response = docCheckboardService.updateAviationList(record, docFile);
			FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-AviationList,"
					+ ConstantMessage.AVIATIONLIST_UPDATED_SUCCESSFULLY + "," + new Date());
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.AVIATIONLIST_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		
		@PostMapping("/view-AviationList-byId")
		public ResponseEntity<?> viewAviationList(Long id)
		{
			AviationList response = docCheckboardService.viewAviationList(id);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		
		@PostMapping("/get-AviationList")
		public ResponseEntity<?> getAviationList(int status)
		{
			List<AviationList> response = docCheckboardService.getAviationList(status);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		
		@PostMapping("/change-AviationList-status")
		public ResponseEntity<?> changeAviationListStatus(Long id , int status ,ServletRequest request)
		{
			AviationList response = docCheckboardService.changeAviationListStatus(id, status);
			FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-AviationList-status,"
					+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
//=================================Para List======================================
		@PostMapping("/add-ParaList")
		public ResponseEntity<?> addParaList(ParaList record , MultipartFile docFile , ServletRequest request )
		{
			ParaList response = docCheckboardService.addParaList(record, docFile);
			FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-ParaList,"
					+ ConstantMessage.PARALIST_ADDED_SUCCESSFULLY + "," + new Date());
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.PARALIST_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		@PostMapping("/update-ParaList")
		public ResponseEntity<?> updateParaList(ParaList record , MultipartFile docFile , ServletRequest request)
		{
			ParaList response = docCheckboardService.updateParaList(record, docFile);
			FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-ParaList,"
					+ ConstantMessage.PARALIST_UPDATED_SUCCESSFULLY + "," + new Date());
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.PARALIST_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		
		@PostMapping("/view-ParaList-byId")
		public ResponseEntity<?> viewParaList(Long id)
		{
			ParaList response = docCheckboardService.viewParaList(id);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		
		@PostMapping("/get-ParaList")
		public ResponseEntity<?> getParaList(int status)
		{
			List<ParaList> response = docCheckboardService.getParaList(status);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		
		@PostMapping("/change-ParaList-status")
		public ResponseEntity<?> changeParaListStatus(Long id , int status ,ServletRequest request)
		{
			ParaList response = docCheckboardService.changeParaListStatus(id, status);
			FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-ParaList-status,"
					+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		
//=================================Cav List======================================
		@PostMapping("/add-CavList")
		public ResponseEntity<?> addCavList(CavList record , MultipartFile docFile , ServletRequest request )
		{
			CavList response = docCheckboardService.addCavList(record, docFile);
			FileWritting.createLog((HttpServletRequest) request, response.getId() + ",added," + "add-CavList,"
					+ ConstantMessage.CAVLIST_ADDED_SUCCESSFULLY + "," + new Date());
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.CAVLIST_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		@PostMapping("/update-CavList")
		public ResponseEntity<?> updateCavList(CavList record , MultipartFile docFile , ServletRequest request)
		{
			CavList response = docCheckboardService.updateCavList(record, docFile);
			FileWritting.createLog((HttpServletRequest) request, response.getId() + ",updated," + "update-CavList,"
					+ ConstantMessage.CAVLIST_UPDATED_SUCCESSFULLY + "," + new Date());
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.CAVLIST_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		
		@PostMapping("/view-CavList-byId")
		public ResponseEntity<?> viewCavList(Long id)
		{
			CavList response = docCheckboardService.viewCavList(id);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		
		@PostMapping("/get-CavList")
		public ResponseEntity<?> getCavList(int status)
		{
			List<CavList> response = docCheckboardService.getCavList(status);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		
		@PostMapping("/change-CavList-status")
		public ResponseEntity<?> changeCavListStatus(Long id , int status ,ServletRequest request)
		{
			CavList response = docCheckboardService.changeCavListStatus(id, status);
			FileWritting.createLog((HttpServletRequest) request, response.getId() + ",status_update," + "change-CavList-status,"
					+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.STATUS_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
}
