package com.example.demo.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.web.multipart.MultipartFile;


public interface GSO_OneTrgService {
	
	Map<Object,Object> addTrgModule(MultipartFile document,String description,String title,int status,ServletRequest servletRequest);
	
	Map<Object,Object> getAllTrgModule();
	
	Map<Object,Object> updateTrgModule(Long id,MultipartFile document,String description,String title,int status,ServletRequest servletRequest);
	
	Map<Object , Object> activeDeactiveStatus(Long id , int status,ServletRequest servletRequest);
	
	Map<Object,Object> getTrgDetailsById(Long id);
	
	//-------------------------------------  Schedule of central lec ---------------------------------------------

	Map<Object, Object> addCentralLecture(MultipartFile document, String description, String title, int status,
			ServletRequest servletRequest);

	Map<Object, Object> getAllCentralLecture();

	Map<Object, Object> updateCentralLecture(Long id, MultipartFile document, String description, String title,
			int status, ServletRequest servletRequest);

	Map<Object, Object> activeDeactiveStatusCentralLec(Long id, int status, ServletRequest servletRequest);

	Map<Object, Object> getCentralLectureById(Long id);

}
