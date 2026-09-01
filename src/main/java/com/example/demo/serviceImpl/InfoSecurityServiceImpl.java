package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.PoliciesInfoSecurity;
import com.example.demo.model.ReportsInfoSecurity;
import com.example.demo.repository.PoliciesInfoSecurityRepo;
import com.example.demo.repository.ReportsInfoSecurityRepo;
import com.example.demo.service.InfoSecurityService;
import com.example.demo.util.FileUploader;

@Service
public class InfoSecurityServiceImpl implements InfoSecurityService{
	
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;
	
	@Autowired
	PoliciesInfoSecurityRepo policiesInfoSecurityRepo;
	
	@Autowired
	ReportsInfoSecurityRepo reportsInfoSecurityRepo;

	@Override
	public PoliciesInfoSecurity addPoliciesDetails(PoliciesInfoSecurity record, MultipartFile docFile) {
		if(docFile != null && !docFile.isEmpty())
		{
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());
		
		return policiesInfoSecurityRepo.save(record);	}

	@Override
	public PoliciesInfoSecurity updatePoliciesDetails(PoliciesInfoSecurity request, MultipartFile docFile) {
		PoliciesInfoSecurity updated = null;
		PoliciesInfoSecurity records = policiesInfoSecurityRepo.findById(request.getId()).get();
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
				updated = policiesInfoSecurityRepo.save(records);
				return updated;
			}
			return updated;
	}

	@Override
	public List<PoliciesInfoSecurity> getPoliciesList(int status) {
		List<PoliciesInfoSecurity> list = new ArrayList<>();
		if(status == 1 || status == 0)
		{
			list = policiesInfoSecurityRepo.findAllByStatusOrderByIdDesc(status);
		}
		else
		{
			list = policiesInfoSecurityRepo.findAllByOrderByIdDesc();
		}
		return list;
	}

	@Override
	public PoliciesInfoSecurity viewPoliciesById(Long id) {
		PoliciesInfoSecurity record = policiesInfoSecurityRepo.findById(id).get();
		return record;
	}

	@Override
	public PoliciesInfoSecurity changePolicyStatus(int status, Long id) {
		PoliciesInfoSecurity record =  policiesInfoSecurityRepo.findById(id).get();
		if(record !=  null)
		{
			record.setStatus(status);
			record.setUpdatedAt(new Date());
			
			record = policiesInfoSecurityRepo.save(record);
			return record ;
		}
		return null;
	}

	@Override
	public ReportsInfoSecurity addReportDetails(ReportsInfoSecurity record, MultipartFile docFile) {
		if(docFile != null && !docFile.isEmpty())
		{
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());
		
		return reportsInfoSecurityRepo.save(record);
	}

	@Override
	public ReportsInfoSecurity updateReportDetails(ReportsInfoSecurity request, MultipartFile docFile) {
		ReportsInfoSecurity updated = null;
		ReportsInfoSecurity records = reportsInfoSecurityRepo.findById(request.getId()).get();
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
				updated = reportsInfoSecurityRepo.save(records);
				return updated;
			}
			return updated;	}

	@Override
	public List<ReportsInfoSecurity> getReportList(int status) {
		List<ReportsInfoSecurity> list = new ArrayList<>();
		if(status == 1 || status == 0)
		{
			list = reportsInfoSecurityRepo.findAllByStatusOrderByIdDesc(status);
		}
		else
		{
			list = reportsInfoSecurityRepo.findAllByOrderByIdDesc();
		}
		return list;
	}

	@Override
	public ReportsInfoSecurity viewReportById(Long id) {
		ReportsInfoSecurity record = reportsInfoSecurityRepo.findById(id).get();
		return record;
	}

	@Override
	public ReportsInfoSecurity changeReportStatus(int status, Long id) {
		ReportsInfoSecurity record =  reportsInfoSecurityRepo.findById(id).get();
		if(record !=  null)
		{
			record.setStatus(status);
			record.setUpdatedAt(new Date());
			
			record = reportsInfoSecurityRepo.save(record);
			return record ;
		}
		return null;
	}

}
