package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.BiometricRFIDCard;
import com.example.demo.repository.BiometricRFIDCardRepo;
import com.example.demo.service.BiometricRFIDCardService;
import com.example.demo.util.FileUploader;

@Service
public class BiometricRFIDCardServiceImpl implements BiometricRFIDCardService{
	
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;
	
	@Autowired
	BiometricRFIDCardRepo biometricRepo;

	@Override
	public BiometricRFIDCard addDetails(BiometricRFIDCard record, MultipartFile docFile) {
		if(docFile != null && !docFile.isEmpty())
		{
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());
		
		return biometricRepo.save(record);
	}

	@Override
	public BiometricRFIDCard updateDetails(BiometricRFIDCard request, MultipartFile docFile) {
		BiometricRFIDCard updated = null;
		BiometricRFIDCard records = biometricRepo.findById(request.getId()).get();
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
				updated = biometricRepo.save(records);
				return updated;
			}
			return updated;
	}

	@Override
	public List<BiometricRFIDCard> getList(int status) {
		List<BiometricRFIDCard> list = new ArrayList<>();
		if(status == 1 || status == 0)
		{
			list = biometricRepo.findAllByStatusOrderByIdDesc(status);
		}
		else
		{
			list = biometricRepo.findAllByOrderByIdDesc();
		}
		return list;
	}

	@Override
	public BiometricRFIDCard viewById(Long id) {
		BiometricRFIDCard record = biometricRepo.findById(id).get();
		return record;
	}

	@Override
	public BiometricRFIDCard changeStatus(int status, Long id) {
		BiometricRFIDCard record =  biometricRepo.findById(id).get();
		if(record !=  null)
		{
			record.setStatus(status);
			record.setUpdatedAt(new Date());
			
			record = biometricRepo.save(record);
			return record ;
		}
		return null;
	}
	

}
