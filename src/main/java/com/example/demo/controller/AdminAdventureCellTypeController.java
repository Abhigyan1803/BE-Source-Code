package com.example.demo.controller;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AdventureCellChart;
import com.example.demo.model.AdventureCellReport;
import com.example.demo.service.AdventureCellChartService;
import com.example.demo.service.AdventureCellReportService;
import com.example.demo.service.AdventureCellTypeService;

@RestController
@RequestMapping("/api/adventureCellType")
@CrossOrigin
public class AdminAdventureCellTypeController {

	@Autowired
	AdventureCellTypeService acService;

	@Autowired
	AdventureCellChartService chartService;

	@Autowired
	AdventureCellReportService reportService;

	@GetMapping("/getAllAdventureTypes")
	public Map<Object, Object> getAllAdventureTypes() {
		return acService.getAllAcTypes();
	}

	// ========adventure chart methods==========
	@PostMapping("/addChart")
	public Map<Object, Object> addChart(MultipartFile doc, AdventureCellChart chart, ServletRequest request) {
		return chartService.addChart(doc, chart, request);
	}

	@PostMapping("/updateChart")
	public Map<Object, Object> updateChart(MultipartFile doc, AdventureCellChart chart, ServletRequest request) {
		return chartService.updateChart(doc, chart, request);
	}

	@PostMapping("/viewChartById")
	public Map<Object, Object> viewChart(Long id) {
		return chartService.viewById(id);
	}

	@GetMapping("/getAllCharts")
	public Map<Object, Object> getAllCharts() {
		return chartService.getAllCharts();
	}

	@PostMapping("/updateChartStatus")
	public Map<Object, Object> updateChartStatus(Long id, int status, ServletRequest request) {
		return chartService.activeDeactiveStatus(id, status, request);
	}

	// ===============adventure report methods========
	@PostMapping("/addReport")
	public Map<Object, Object> addReport(MultipartFile doc, AdventureCellReport report, ServletRequest request) {
		return reportService.addReport(doc, report, request);
	}

	@PostMapping("/updateReport")
	public Map<Object, Object> updateReport(MultipartFile doc, AdventureCellReport report, ServletRequest request) {
		return reportService.updateReport(doc, report, request);
	}

	@PostMapping("/viewReportById")
	public Map<Object, Object> viewReport(Long id) {
		return reportService.viewById(id);
	}

	@GetMapping("/getAllReports")
	public Map<Object, Object> getAllReports() {
		return reportService.getAllReports();
	}

	@PostMapping("/updateReportStatus")
	public Map<Object, Object> updateReportStatus(Long id, int status, ServletRequest request) {
		return reportService.activeDeactiveStatus(id, status, request);
	}
}
