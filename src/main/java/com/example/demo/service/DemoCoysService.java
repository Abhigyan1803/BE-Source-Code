package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.DemoCoys;

public interface DemoCoysService {
	
	DemoCoys addDetails(DemoCoys  record,MultipartFile docFile);
	
	DemoCoys updateDetails(DemoCoys record , MultipartFile docFile);
	
	List<DemoCoys> getList(int status);
	
	DemoCoys viewById(Long id);
	
	DemoCoys changeStatus(int status , Long id);

}
