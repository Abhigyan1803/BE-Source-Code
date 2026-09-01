package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.SectionHospital;

public interface SectionHospitalService {
	
	SectionHospital addDetails(SectionHospital details , MultipartFile file);
	
	SectionHospital updateDetails(SectionHospital details , MultipartFile file);
	
	SectionHospital viewById(Long id);
	
	SectionHospital chnageStatus(Long id , int status);
	
	List<SectionHospital> getList(int status);

}
