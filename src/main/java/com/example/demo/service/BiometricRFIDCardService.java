package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.BiometricRFIDCard;

public interface BiometricRFIDCardService {
	
	BiometricRFIDCard addDetails(BiometricRFIDCard  record,MultipartFile docFile);
	
	BiometricRFIDCard updateDetails(BiometricRFIDCard record , MultipartFile docFile);
	
	List<BiometricRFIDCard> getList(int status);
	
	BiometricRFIDCard viewById(Long id);
	
	BiometricRFIDCard changeStatus(int status , Long id);

}
