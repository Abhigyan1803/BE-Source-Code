package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.CasualStaffEntryPasses;
import com.example.demo.model.CombatEntryPasses;
import com.example.demo.model.DefEntryPasses;

public interface EntryPassesService {
	
//==================Combat Entry Passes==================================
	
	CombatEntryPasses addCombatDetails(CombatEntryPasses  record,MultipartFile docFile);
	
	CombatEntryPasses updateCombatDetails(CombatEntryPasses record , MultipartFile docFile);
	
	List<CombatEntryPasses> getCombatList(int status);
	
	CombatEntryPasses viewCombatById(Long id);
	
	CombatEntryPasses changeCombatStatus(int status , Long id);
	
//============================Def/Civ staff========================================

    DefEntryPasses addDefDetails(DefEntryPasses  record,MultipartFile docFile);
	
    DefEntryPasses updateDefDetails(DefEntryPasses record , MultipartFile docFile);
	
	List<DefEntryPasses> getDefList(int status);
	
	DefEntryPasses viewDefById(Long id);
	
	DefEntryPasses changeDefStatus(int status , Long id);
	
//=============================Casual Staff=============================
	
    CasualStaffEntryPasses addCasualStaffDetails(CasualStaffEntryPasses  record,MultipartFile docFile);
	
    CasualStaffEntryPasses updateCasualStaffDetails(CasualStaffEntryPasses record , MultipartFile docFile);
	
	List<CasualStaffEntryPasses> getCasualStaffList(int status);
	
	CasualStaffEntryPasses viewCasualStaffById(Long id);
	
	CasualStaffEntryPasses changeCasualStaffStatus(int status , Long id);
}
