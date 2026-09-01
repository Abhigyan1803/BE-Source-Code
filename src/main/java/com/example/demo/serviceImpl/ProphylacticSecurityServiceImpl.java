package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.PoliciesProphylacticSecurity;
import com.example.demo.model.ReportsProphylacticSecurity;
import com.example.demo.repository.PoliciesProphylacticSecurityRepo;
import com.example.demo.repository.ReportsProphylacticSecurityRepo;
import com.example.demo.service.ProphylacticSecurityService;
import com.example.demo.util.FileUploader;

@Service
public class ProphylacticSecurityServiceImpl implements ProphylacticSecurityService {
	
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;
	
	@Autowired
	PoliciesProphylacticSecurityRepo policiesRepo;
	
	@Autowired
	ReportsProphylacticSecurityRepo reportsRepo;

	@Override
	public PoliciesProphylacticSecurity addPoliciesDetails(PoliciesProphylacticSecurity record, MultipartFile docFile) {
		if(docFile != null && !docFile.isEmpty())
		{
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());
		
		return policiesRepo.save(record);
	}

	@Override
	public PoliciesProphylacticSecurity updatePoliciesDetails(PoliciesProphylacticSecurity request,
			MultipartFile docFile) {
		PoliciesProphylacticSecurity updated = null;
		PoliciesProphylacticSecurity records = policiesRepo.findById(request.getId()).get();
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
				updated = policiesRepo.save(records);
				return updated;
			}
			return updated;
	}

	@Override
	public List<PoliciesProphylacticSecurity> getPoliciesList(int status) {
		List<PoliciesProphylacticSecurity> list = new ArrayList<>();
		if(status == 1 || status == 0)
		{
			list = policiesRepo.findAllByStatusOrderByIdDesc(status);
		}
		else
		{
			list = policiesRepo.findAllByOrderByIdDesc();
		}
		return list;
	}

	@Override
	public PoliciesProphylacticSecurity viewPoliciesById(Long id) {
		PoliciesProphylacticSecurity record = policiesRepo.findById(id).get();
		return record;
	}

	@Override
	public PoliciesProphylacticSecurity changePolicyStatus(int status, Long id) {
		PoliciesProphylacticSecurity record =  policiesRepo.findById(id).get();
		if(record !=  null)
		{
			record.setStatus(status);
			record.setUpdatedAt(new Date());
			
			record = policiesRepo.save(record);
			return record ;
		}
		return null;
	}

	@Override
	public ReportsProphylacticSecurity addReportDetails(ReportsProphylacticSecurity record, MultipartFile docFile) {
		if(docFile != null && !docFile.isEmpty())
		{
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());
		
		return reportsRepo.save(record);
	}

	@Override
	public ReportsProphylacticSecurity updateReportDetails(ReportsProphylacticSecurity request, MultipartFile docFile) {
		ReportsProphylacticSecurity updated = null;
		ReportsProphylacticSecurity records = reportsRepo.findById(request.getId()).get();
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
				updated = reportsRepo.save(records);
				return updated;
			}
			return updated;
	}

	@Override
	public List<ReportsProphylacticSecurity> getReportList(int status) {
		List<ReportsProphylacticSecurity> list = new ArrayList<>();
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
	public ReportsProphylacticSecurity viewReportById(Long id) {
		ReportsProphylacticSecurity record = reportsRepo.findById(id).get();
		return record;
	}

	@Override
	public ReportsProphylacticSecurity changeReportStatus(int status, Long id) {
		ReportsProphylacticSecurity record =  reportsRepo.findById(id).get();
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
