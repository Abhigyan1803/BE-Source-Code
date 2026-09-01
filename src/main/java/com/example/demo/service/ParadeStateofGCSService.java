package com.example.demo.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.ParadeStateOfGCS;
import com.example.demo.payload.PaginationPayLoad;

public interface ParadeStateofGCSService {
	
	Map<Object,Object> addDetails(MultipartFile doc , ParadeStateOfGCS record,ServletRequest servletRequest);
	
	Map<Object,Object> updateDetails(MultipartFile doc , ParadeStateOfGCS update,ServletRequest servletRequest);
	
	Map<Object,Object> viewById(Long id);
	
	Map<Object,Object> activeDeactiveStatus(Long id , int status,ServletRequest servletRequest);
	
//    Map<Object, Object> getAllDetails(PaginationPayLoad request);
	
	 Map<Object, Object> getAllDetails();
	
	

}
