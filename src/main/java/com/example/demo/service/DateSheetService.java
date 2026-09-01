package com.example.demo.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.DateSheet;

public interface DateSheetService {

	Map<Object, Object> addDateSheet(MultipartFile document, DateSheet dateSheet, ServletRequest request);

	Map<Object, Object> getAllRecords(Long termId);

	Map<Object, Object> viewDetailsById(Long id);

	Map<Object, Object> updateRecord(MultipartFile document, DateSheet update, ServletRequest request);

	Map<Object, Object> activeDeactiveStatus(Long id, int status, ServletRequest request);

}
