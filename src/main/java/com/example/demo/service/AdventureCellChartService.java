package com.example.demo.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AdventureCellChart;

public interface AdventureCellChartService {

	Map<Object, Object> addChart(MultipartFile doc, AdventureCellChart chart, ServletRequest request);

	Map<Object, Object> updateChart(MultipartFile doc, AdventureCellChart chart, ServletRequest request);

	Map<Object, Object> getAllCharts();

	Map<Object, Object> viewById(Long id);

	Map<Object, Object> activeDeactiveStatus(Long id, int status, ServletRequest request);

}
