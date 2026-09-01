package com.example.demo.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface BattallionActitvityService {

   
	Map<Object, Object> addBattallionActivity(MultipartFile file ,Integer battalionId,ServletRequest servletRequest);
	
	Map<Object , Object>getBattalionActivityByStatus(int status);
	
	Map<Object , Object>activeDeactiveActivity(Long id , int status,ServletRequest servletRequest);

}
