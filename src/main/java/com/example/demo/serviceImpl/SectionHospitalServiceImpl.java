package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.SectionHospital;
import com.example.demo.repository.SectionHospitalRepo;
import com.example.demo.service.SectionHospitalService;
import com.example.demo.util.FileUploader;

@Service
public class SectionHospitalServiceImpl implements SectionHospitalService{
	
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;
	
	@Autowired
	SectionHospitalRepo sectionHospitalRepo;

	@Override
	public SectionHospital addDetails(SectionHospital details, MultipartFile file) {
		{
			String filename = FileUploader.uploadProfileImage(file, UploadDir);
			details.setDocument(url + filename);
		}
		details.setCreatedAt(new Date());
		details.setUpdatedAt(new Date());
		return sectionHospitalRepo.save(details);
	}

	@Override
	public SectionHospital updateDetails(SectionHospital details, MultipartFile docFile) {
		SectionHospital updated = null;
		SectionHospital records = sectionHospitalRepo.findById(details.getId()).get();
			if(records != null)
			{
				if(docFile != null && !docFile.isEmpty())
				{
					String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
					records.setDocument(url + filename);
				}
				records.setUpdatedAt(new Date());
				records.setTitle(details.getTitle());
				records.setStatus(details.getStatus());
				
				updated = sectionHospitalRepo.save(records);
				return updated;
			}
			return updated;
	}

	@Override
	public SectionHospital viewById(Long id) {
		SectionHospital details = sectionHospitalRepo.findById(id).get();
		return details;
	}

	@Override
	public SectionHospital chnageStatus(Long id, int status) {
		SectionHospital details = sectionHospitalRepo.findById(id).get();
		if(details !=  null)
		{
			details.setStatus(status);
			details.setUpdatedAt(new Date());
			
			details = sectionHospitalRepo.save(details);
			return details ;
		}
		return null;
	}

	@Override
	public List<SectionHospital> getList(int status) {
		List<SectionHospital> list = new ArrayList<>();
		if(status == 1 || status == 0)
		{
			list = sectionHospitalRepo.findAllByStatusOrderByIdDesc(status);
		}
		else
		{
			list = sectionHospitalRepo.findAllByOrderByIdDesc();
		}
		return list;
	}

}
