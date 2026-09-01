package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.PoliciesIntelligenceSecurity;
import com.example.demo.model.ReportsIntelligenceSecurity;
import com.example.demo.repository.PoliciesIntelligenceSecurityRepo;
import com.example.demo.repository.ReportsIntelligenceSecurityRepo;
import com.example.demo.service.IntelligenceSecurityService;
import com.example.demo.util.FileUploader;

@Service
public class IntelligenceSecurityServiceImpl implements IntelligenceSecurityService{
	
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;
	
	@Autowired
	PoliciesIntelligenceSecurityRepo policyRepo;
	
	@Autowired
	ReportsIntelligenceSecurityRepo reportsRepo;

	@Override
	public PoliciesIntelligenceSecurity addPoliciesDetails(PoliciesIntelligenceSecurity record, MultipartFile docFile) {
		if(docFile != null && !docFile.isEmpty())
		{
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());
		
		return policyRepo.save(record);
	}

	@Override
	public PoliciesIntelligenceSecurity updatePoliciesDetails(PoliciesIntelligenceSecurity request,
			MultipartFile docFile) {
		PoliciesIntelligenceSecurity updated = null;
		PoliciesIntelligenceSecurity records = policyRepo.findById(request.getId()).get();
			if(records != null)
			{
				if(docFile != null && !docFile.isEmpty())
				{
					String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
					records.setFile(url + filename);
				}
				records.setUpdatedAt(new Date());
				records.setDescription(request.getDescription());
				records.setName(request.getName());
				records.setStatus(request.getStatus());
				updated = policyRepo.save(records);
				return updated;
			}
			return updated;
	}

	@Override
	public List<PoliciesIntelligenceSecurity> getPoliciesList(int status) {
		List<PoliciesIntelligenceSecurity> list = new ArrayList<>();
		if(status == 1 || status == 0)
		{
			list = policyRepo.findAllByStatusOrderByIdDesc(status);
		}
		else
		{
			list = policyRepo.findAllByOrderByIdDesc();
		}
		return list;
	}

	@Override
	public PoliciesIntelligenceSecurity viewPoliciesById(Long id) {
		PoliciesIntelligenceSecurity record = policyRepo.findById(id).get();
		return record;
	}

	@Override
	public PoliciesIntelligenceSecurity changePolicyStatus(int status, Long id) {
		PoliciesIntelligenceSecurity record =  policyRepo.findById(id).get();
		if(record !=  null)
		{
			record.setStatus(status);
			record.setUpdatedAt(new Date());
			
			record = policyRepo.save(record);
			return record ;
		}
		return null;
	}

	@Override
	public ReportsIntelligenceSecurity addReportDetails(ReportsIntelligenceSecurity record, MultipartFile docFile) {
		if(docFile != null && !docFile.isEmpty())
		{
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url +filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());
		
		return reportsRepo.save(record);
	}

	@Override
	public ReportsIntelligenceSecurity updateReportDetails(ReportsIntelligenceSecurity request, MultipartFile docFile) {
		ReportsIntelligenceSecurity updated = null;
		ReportsIntelligenceSecurity records = reportsRepo.findById(request.getId()).get();
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
				updated = reportsRepo.save(records);
				return updated;
			}
			return updated;
	}

	@Override
	public List<ReportsIntelligenceSecurity> getReportList(int status) {
		List<ReportsIntelligenceSecurity> list = new ArrayList<>();
		if(status == 1 || status == 0)
		{
			list = reportsRepo.findAllByStatusOrderByIdDesc(status);
		}
		else
		{
			list = reportsRepo.findAllByOrderByIdDesc();
		}
		return list;
	}

	@Override
	public ReportsIntelligenceSecurity viewReportById(Long id) {
		ReportsIntelligenceSecurity record = reportsRepo.findById(id).get();
		return record;
	}

	@Override
	public ReportsIntelligenceSecurity changeReportStatus(int status, Long id) {
		ReportsIntelligenceSecurity record =  reportsRepo.findById(id).get();
		if(record !=  null)
		{
			record.setStatus(status);
			record.setUpdatedAt(new Date());
			
			record = reportsRepo.save(record);
			return record ;
		}
		return null;
	}

}
