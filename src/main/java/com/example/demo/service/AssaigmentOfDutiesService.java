package com.example.demo.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AssaigmentOfDuties;

public interface AssaigmentOfDutiesService {

	Map<Object, Object> addDuties(AssaigmentOfDuties duties, MultipartFile doc, ServletRequest request);

	Map<Object, Object> updateDuties(AssaigmentOfDuties duties, MultipartFile doc, ServletRequest request);

	Map<Object, Object> viewDetailsById(Long id);

	Map<Object, Object> activeDeactiveStatus(Long id, int status, ServletRequest request);

	Map<Object, Object> getAllDuties(int battalionId, int status);

}
