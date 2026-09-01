package com.example.demo.service;

import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.RequirementComplaint;

public interface RequirementComplaintService {
	
	RequirementComplaint  addDetails(RequirementComplaint records , MultipartFile docFile , ServletRequest request);
	
	RequirementComplaint  updateDetails(RequirementComplaint records , MultipartFile docFile , ServletRequest request);
	
	RequirementComplaint  changeStatus(int status ,Long id);
	
	RequirementComplaint viewById(Long id);
	
	List<RequirementComplaint> getList(int status);

	List<RequirementComplaint> getByRequestNature(String requestNature , int status);
	
}
