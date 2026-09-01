package com.example.demo.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AdventureCellReport;

public interface AdventureCellReportService {

	Map<Object, Object> addReport(MultipartFile doc, AdventureCellReport report, ServletRequest request);

	Map<Object, Object> updateReport(MultipartFile doc, AdventureCellReport report, ServletRequest request);

	Map<Object, Object> getAllReports();

	Map<Object, Object> viewById(Long id);

	Map<Object, Object> activeDeactiveStatus(Long id, int status, ServletRequest request);

}
