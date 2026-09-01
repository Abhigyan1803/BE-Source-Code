package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.CyberPolicyType;

public interface CyberPolicyTypeService {

	
	CyberPolicyType addCyberPolicy(MultipartFile file,CyberPolicyType request);

	CyberPolicyType updateCyberPolicy(MultipartFile file,CyberPolicyType request);

	CyberPolicyType getCyberPolicyById(Long id);

	List<CyberPolicyType> getAllCyberPolicy();

	CyberPolicyType activeDeActiveCyberPolicy(Long id, int status);

	List<CyberPolicyType> getAllCyberPolicyHomepage();

}
