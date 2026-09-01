package com.example.demo.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.GSO2ServiceSubject;

public interface GSO2ServiceSubjectService {

	Map<Object, Object> addServiceSubject(MultipartFile document, GSO2ServiceSubject serviceSubject,
			ServletRequest request);

	Map<Object, Object> getAllServiceSubjectRecords();

	Map<Object, Object> viewServiceSubjectDetailsById(Long id);

	Map<Object, Object> updateServiceSubjectRecord(MultipartFile document, GSO2ServiceSubject update,
			ServletRequest request);

	Map<Object, Object> activeDeactiveStatusServiceSubject(Long id, int status, ServletRequest request);

	Map<Object, Object> getAllServiceSubjectByTypeSubTypeAndTerm(String type, String subType, Long termId);

}
