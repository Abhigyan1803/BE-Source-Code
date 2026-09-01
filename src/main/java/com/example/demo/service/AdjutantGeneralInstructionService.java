package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.POPAdjutantBranch;
import com.example.demo.model.ReceptionAdjutantBranch;
import com.example.demo.model.SOPAdjutantBranch;
import com.example.demo.model.ScheduleDrillCompetition;

public interface AdjutantGeneralInstructionService {
	
	//========================Reception General Instruction =========================
	
	ReceptionAdjutantBranch addReception(ReceptionAdjutantBranch details , MultipartFile file);

	ReceptionAdjutantBranch updateReception(ReceptionAdjutantBranch details , MultipartFile file);
	
	ReceptionAdjutantBranch changeReceptionStatus(Long id , int status);
	
	ReceptionAdjutantBranch viewReceptionById(Long id);
	
	List<ReceptionAdjutantBranch> getReceptionList(int status);
	
	//========================POP General Instruction =========================
	
	POPAdjutantBranch addPOP(POPAdjutantBranch details , MultipartFile file);

	POPAdjutantBranch updatePOP(POPAdjutantBranch details , MultipartFile file);
		
	POPAdjutantBranch changePOPStatus(Long id , int status);
		
	POPAdjutantBranch viewPOPById(Long id);
		
	List<POPAdjutantBranch> getPOPList(int status);
	
	//========================POP General Instruction =========================
	
		SOPAdjutantBranch addSOP(SOPAdjutantBranch details , MultipartFile file);

		SOPAdjutantBranch updateSOP(SOPAdjutantBranch details , MultipartFile file);
			
		SOPAdjutantBranch changeSOPStatus(Long id , int status);
			
		SOPAdjutantBranch viewSOPById(Long id);
			
		List<SOPAdjutantBranch> getSOPList(int status);
		
	//========================Schedule Drill Competition General Instruction =========================
		
		ScheduleDrillCompetition addSchedule(ScheduleDrillCompetition details , MultipartFile file);

		ScheduleDrillCompetition updateSchedule(ScheduleDrillCompetition details , MultipartFile file);
				
		ScheduleDrillCompetition changeScheduleStatus(Long id , int status);
				
		ScheduleDrillCompetition viewScheduleById(Long id);
				
		List<ScheduleDrillCompetition> getScheduleList(int status);

		List<ScheduleDrillCompetition> getScheduleByTypeAndStatus(String type, int status);

}
