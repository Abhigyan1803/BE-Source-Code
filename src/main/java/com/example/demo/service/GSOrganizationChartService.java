package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.GSOrganizationChart;
import com.example.demo.model.GSPosition;
import com.example.demo.myexception.MyException;

public interface GSOrganizationChartService {
	
	GSOrganizationChart addOrganization(GSOrganizationChart  records , MultipartFile docFile) throws MyException;
	
	GSOrganizationChart updateOrganization(GSOrganizationChart records , MultipartFile docFile);
	
	GSOrganizationChart changeStatus(Long id , int status);
	
	GSOrganizationChart viewById(Long id);
	
	List<GSOrganizationChart> getOrganizationList(int status);

	List<GSPosition> getGsPositions();

}
