package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AdventureCellChart;
import com.example.demo.model.TermSeason;
import com.example.demo.repository.AdventureCellChartRepo;
import com.example.demo.repository.TermSeasonRepo;
import com.example.demo.service.AdventureCellChartService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;

@Service
public class AdventureCellChartServiceImpl implements AdventureCellChartService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdventureCellChartRepo chartRepo;

	@Autowired
	TermSeasonRepo termSeasonRepo;

	@Override
	public Map<Object, Object> addChart(MultipartFile doc, AdventureCellChart chart, ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			if (doc != null && !doc.isEmpty()) {

				String filename = FileUploader.uploadProfileImage(doc, UploadDir);
				chart.setDocument(url + filename);
			}

			TermSeason term = termSeasonRepo.findById(chart.getSeasonTerm().getId()).get();
			chart.setSeasonTerm(term);

			AdventureCellChart saved = chartRepo.save(chart);
			if (saved != null) {
				FileWritting.createLog((HttpServletRequest) request, saved.getId() + ",added,"
						+ "add adventureCell Chart," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.LIST, saved);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_ADDED_SUCCESSFULLY);
				return map;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

	@Override
	public Map<Object, Object> updateChart(MultipartFile doc, AdventureCellChart chart, ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			AdventureCellChart existing = chartRepo.findById(chart.getId()).get();
			if (existing != null) {
				if (doc != null && !doc.isEmpty()) {
					String filename = FileUploader.uploadProfileImage(doc, UploadDir);
					existing.setDocument(url + filename);
				}
				TermSeason term = termSeasonRepo.findById(chart.getSeasonTerm().getId()).get();
				existing.setSeasonTerm(term);
				existing.setUpdatedOn(new Date());
				existing.setName(chart.getName());
				existing.setDescription(chart.getDescription());
				existing.setStatus(chart.getStatus());

				AdventureCellChart updated = chartRepo.save(existing);
				if (updated != null) {
					FileWritting.createLog((HttpServletRequest) request,
							updated.getId() + ",updated," + "updated adventureCell Chart,"
									+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
					map.put(ConstantMessage.LIST, updated);
					map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
					map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_ADDED_SUCCESSFULLY);
					return map;
				}
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.INVALID_ID);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

	@Override
	public Map<Object, Object> getAllCharts() {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			// List<AdventureCellChart> list = chartRepo.findAll();
			List<AdventureCellChart> list = chartRepo.findAllByOrderByIdDesc();
			if (list != null && list.size() != 0) {
				map.put(ConstantMessage.LIST, list);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

	@Override
	public Map<Object, Object> viewById(Long id) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			AdventureCellChart list = chartRepo.findById(id).get();
			if (list != null) {
				map.put(ConstantMessage.LIST, list);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

	@Override
	public Map<Object, Object> activeDeactiveStatus(Long id, int status, ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			AdventureCellChart list = chartRepo.findById(id).get();
			if (list != null) {
				list.setStatus(status);
				list.setUpdatedOn(new Date());

				chartRepo.save(list);
				FileWritting.createLog((HttpServletRequest) request,
						list.getId() + ",updated," + "status updated adventureCell Chart,"
								+ ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.LIST, list);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.STATUS_UPDATED_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

}
