package com.example.demo.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.payload.AddSopDetails;
import com.example.demo.payload.GetDataOnlyById;

public interface SopsDetailsService {

	Map<Object, Object> addSopsDetails(MultipartFile file,AddSopDetails request,ServletRequest servletRequest);
	
//	Map<Object, Object> getAllSopsDetails(PaginationPayLoad request);
	
	Map<Object, Object> getAllSopsDetails();

	Map<Object, Object> getDetailsByOnlyById(GetDataOnlyById request);

	Map<Object, Object> activeDeActiveSops(Long id, int status,ServletRequest servletRequest);

	Map<Object, Object> updateSops(MultipartFile file,Long id,String name,String description,int status,ServletRequest servletRequest);

	Map<Object, Object> readRecords();
	
	
}
