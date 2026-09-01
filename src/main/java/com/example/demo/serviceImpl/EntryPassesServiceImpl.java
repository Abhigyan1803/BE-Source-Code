package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.CasualStaffEntryPasses;
import com.example.demo.model.CombatEntryPasses;
import com.example.demo.model.DefEntryPasses;
import com.example.demo.repository.CasualStaffEntryPassesRepo;
import com.example.demo.repository.CombatEntryPassesRepo;
import com.example.demo.repository.DefEntryPassesRepo;
import com.example.demo.service.EntryPassesService;
import com.example.demo.util.FileUploader;

@Service
public class EntryPassesServiceImpl implements EntryPassesService {
	
	
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;
	
	@Autowired
	CombatEntryPassesRepo combatEntryPassesRepo;
	
	@Autowired
	CasualStaffEntryPassesRepo casualStaffRepo;
	
	@Autowired
	DefEntryPassesRepo defRepo;
	
	

	@Override
	public CombatEntryPasses addCombatDetails(CombatEntryPasses record, MultipartFile docFile) {
		if(docFile != null && !docFile.isEmpty())
		{
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url +filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());
		
		return combatEntryPassesRepo.save(record);
	}

	@Override
	public CombatEntryPasses updateCombatDetails(CombatEntryPasses request, MultipartFile docFile) {
		CombatEntryPasses updated = null;
		CombatEntryPasses records = combatEntryPassesRepo.findById(request.getId()).get();
			if(records != null)
			{
				if(docFile != null && !docFile.isEmpty())
				{
					String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
					records.setFile(url +filename);
				}
				records.setUpdatedAt(new Date());
				records.setDescription(request.getDescription());
				records.setName(request.getName());
				records.setStatus(request.getStatus());
				updated = combatEntryPassesRepo.save(records);
				return updated;
			}
			return updated;
	}

	@Override
	public List<CombatEntryPasses> getCombatList(int status) {
		List<CombatEntryPasses> list = new ArrayList<>();
		if(status == 1 || status == 0)
		{
			list = combatEntryPassesRepo.findAllByStatusOrderByIdDesc(status);
		}
		else
		{
			list = combatEntryPassesRepo.findAllByOrderByIdDesc();
		}
		return list;
	}

	@Override
	public CombatEntryPasses viewCombatById(Long id) {
		CombatEntryPasses record = combatEntryPassesRepo.findById(id).get();
		return record;
	}

	@Override
	public CombatEntryPasses changeCombatStatus(int status, Long id) {
		CombatEntryPasses record =  combatEntryPassesRepo.findById(id).get();
		if(record !=  null)
		{
			record.setStatus(status);
			record.setUpdatedAt(new Date());
			
			record = combatEntryPassesRepo.save(record);
			return record ;
		}
		return null;
	}

	@Override
	public DefEntryPasses addDefDetails(DefEntryPasses record, MultipartFile docFile) {
		if(docFile != null && !docFile.isEmpty())
		{
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url +filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());
		
		return defRepo.save(record);
	}

	@Override
	public DefEntryPasses updateDefDetails(DefEntryPasses request, MultipartFile docFile) {
		DefEntryPasses updated = null;
		DefEntryPasses records = defRepo.findById(request.getId()).get();
			if(records != null)
			{
				if(docFile != null && !docFile.isEmpty())
				{
					String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
					records.setFile(url +filename);
				}
				records.setUpdatedAt(new Date());
				records.setDescription(request.getDescription());
				records.setName(request.getName());
				records.setStatus(request.getStatus());
				updated = defRepo.save(records);
				return updated;
			}
			return updated;
	}

	@Override
	public List<DefEntryPasses> getDefList(int status) {
		List<DefEntryPasses> list = new ArrayList<>();
		if(status == 1 || status == 0)
		{
			list = defRepo.findAllByStatusOrderByIdDesc(status);
		}
		else
		{
			list = defRepo.findAllByOrderByIdDesc();
		}
		return list;
	}

	@Override
	public DefEntryPasses viewDefById(Long id) {
		DefEntryPasses record = defRepo.findById(id).get();
		return record;
	}

	@Override
	public DefEntryPasses changeDefStatus(int status, Long id) {
		DefEntryPasses record =  defRepo.findById(id).get();
		if(record !=  null)
		{
			record.setStatus(status);
			record.setUpdatedAt(new Date());
			
			record = defRepo.save(record);
			return record ;
		}
		return null;
	}

	@Override
	public CasualStaffEntryPasses addCasualStaffDetails(CasualStaffEntryPasses record, MultipartFile docFile) {
		if(docFile != null && !docFile.isEmpty())
		{
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url +filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());
		
		return casualStaffRepo.save(record);
	}

	@Override
	public CasualStaffEntryPasses updateCasualStaffDetails(CasualStaffEntryPasses request, MultipartFile docFile) {
		CasualStaffEntryPasses updated = null;
		CasualStaffEntryPasses records = casualStaffRepo.findById(request.getId()).get();
			if(records != null)
			{
				if(docFile != null && !docFile.isEmpty())
				{
					String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
					records.setFile(url +filename);
				}
				records.setUpdatedAt(new Date());
				records.setDescription(request.getDescription());
				records.setName(request.getName());
				records.setStatus(request.getStatus());
				updated = casualStaffRepo.save(records);
				return updated;
			}
			return updated;
	}

	@Override
	public List<CasualStaffEntryPasses> getCasualStaffList(int status) {
		List<CasualStaffEntryPasses> list = new ArrayList<>();
		if(status == 1 || status == 0)
		{
			list = casualStaffRepo.findAllByStatusOrderByIdDesc(status);
		}
		else
		{
			list = casualStaffRepo.findAllByOrderByIdDesc();
		}
		return list;
	}

	@Override
	public CasualStaffEntryPasses viewCasualStaffById(Long id) {
		CasualStaffEntryPasses record = casualStaffRepo.findById(id).get();
		return record;
	}

	@Override
	public CasualStaffEntryPasses changeCasualStaffStatus(int status, Long id) {
		CasualStaffEntryPasses record =  casualStaffRepo.findById(id).get();
		if(record !=  null)
		{
			record.setStatus(status);
			record.setUpdatedAt(new Date());
			
			record = casualStaffRepo.save(record);
			return record ;
		}
		return null;
	}

}
