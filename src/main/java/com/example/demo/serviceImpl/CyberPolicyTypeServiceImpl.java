package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.CyberPolicyType;
import com.example.demo.repository.CyberPolicyTypeRepo;
import com.example.demo.service.CyberPolicyTypeService;
import com.example.demo.util.FileUploader;

@Service
public class CyberPolicyTypeServiceImpl implements CyberPolicyTypeService {

	@Autowired
	CyberPolicyTypeRepo cyberPolicyTypeRepo;
	
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Override
	public CyberPolicyType addCyberPolicy(MultipartFile file,CyberPolicyType request) {
	    if(file != null && !file.isEmpty()) {
					String filename = FileUploader.uploadProfileImage(file,UploadDir);
				  request.setLink(url + filename);
				}
		return cyberPolicyTypeRepo.save(request);
	}

	@Override
	public List<CyberPolicyType> getAllCyberPolicyHomepage() {
		return cyberPolicyTypeRepo.findByStatusOrderByIdDesc(1);
	}
	
	@Override
	public CyberPolicyType activeDeActiveCyberPolicy(Long id,int status) {
		CyberPolicyType cyberPolicyTypeNew=null; 
		
		CyberPolicyType cyberPolicyType=cyberPolicyTypeRepo.findById(id).get();
         if(cyberPolicyType!=null) 
         {
        	  cyberPolicyType.setStatus(status);
        	  cyberPolicyTypeNew=cyberPolicyTypeRepo.save(cyberPolicyType);
         }		
       return cyberPolicyTypeRepo.save(cyberPolicyTypeNew);
	}
	
	
	@Override
	public CyberPolicyType getCyberPolicyById(Long id) {
		return cyberPolicyTypeRepo.findById(id).get();
	}
	
	
	@Override
	public CyberPolicyType updateCyberPolicy(MultipartFile file,CyberPolicyType request) {
		CyberPolicyType cyberPolicyTypeNew=null; 
		
		CyberPolicyType cyberPolicyType=cyberPolicyTypeRepo.findById(request.getId()).get();
         if(cyberPolicyType!=null) 
         {
        	 if(file != null && !file.isEmpty()) {
					String filename = FileUploader.uploadProfileImage(file,UploadDir);
					request.setLink(url+filename);
				}
        	 else 
        	 {
        			request.setLink(cyberPolicyType.getLink());
        	 }
        	   request.setCreatedAt(cyberPolicyType.getCreatedAt());
        	   request.setUpdatedOn(new Date());
        	  cyberPolicyTypeNew=cyberPolicyTypeRepo.save(request);
         }		
       return cyberPolicyTypeNew;
	}

	@Override
	public List<CyberPolicyType> getAllCyberPolicy() {
		return cyberPolicyTypeRepo.findAllByOrderByIdDesc();
	}


	
	
}
