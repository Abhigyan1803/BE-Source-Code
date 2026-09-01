package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.PoliciesSecurity;
import com.example.demo.repository.PoliciesSecurityRepo;
import com.example.demo.service.PoliciesSecurityService;
import com.example.demo.util.FileUploader;

@Service
public class PoliciesSecurityServiceImpl implements PoliciesSecurityService {
	
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;
	
	@Autowired
	PoliciesSecurityRepo policiesRepo;

	@Override
	public PoliciesSecurity addDetails(PoliciesSecurity record, MultipartFile docFile) {
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
	public PoliciesSecurity updateDetails(PoliciesSecurity request, MultipartFile docFile) {
		PoliciesSecurity updated = null;
		PoliciesSecurity records = policiesRepo.findById(request.getId()).get();
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
	public List<PoliciesSecurity> getList(int status) {
		List<PoliciesSecurity> list = new ArrayList<>();
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
	public PoliciesSecurity viewById(Long id) {
		PoliciesSecurity record = policiesRepo.findById(id).get();
		return record;
	}

	@Override
	public PoliciesSecurity changeStatus(int status, Long id) {
		PoliciesSecurity record =  policiesRepo.findById(id).get();
		if(record !=  null)
		{
			record.setStatus(status);
			record.setUpdatedAt(new Date());
			
			record = policiesRepo.save(record);
			return record ;
		}
		return null;
	}

}
