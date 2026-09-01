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

import com.example.demo.model.AdventureCellReport;
import com.example.demo.model.TermSeason;
import com.example.demo.repository.AdventureCellReportRepo;
import com.example.demo.repository.TermSeasonRepo;
import com.example.demo.service.AdventureCellReportService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;

@Service
public class AdventureCellReportServiceImpl implements AdventureCellReportService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdventureCellReportRepo reportRepo;

	@Autowired
	TermSeasonRepo termSeasonRepo;

	@Override
	public Map<Object, Object> addReport(MultipartFile doc, AdventureCellReport report, ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			if (doc != null && !doc.isEmpty()) {

				String filename = FileUploader.uploadProfileImage(doc, UploadDir);
				report.setDocument(url + filename);
			}

			TermSeason term = termSeasonRepo.findById(report.getSeasonTerm().getId()).get();
			report.setSeasonTerm(term);

			AdventureCellReport saved = reportRepo.save(report);
			if (saved != null) {
				FileWritting.createLog((HttpServletRequest) request, saved.getId() + ",added,"
						+ "add adventureCell Report," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
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
	public Map<Object, Object> updateReport(MultipartFile doc, AdventureCellReport report, ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			AdventureCellReport existing = reportRepo.findById(report.getId()).get();
			if (existing != null) {
				if (doc != null && !doc.isEmpty()) {
					String filename = FileUploader.uploadProfileImage(doc, UploadDir);
					existing.setDocument(url + filename);
				}
				TermSeason term = termSeasonRepo.findById(report.getSeasonTerm().getId()).get();
				existing.setSeasonTerm(term);
				existing.setUpdatedOn(new Date());
				existing.setDescription(report.getDescription());
				existing.setName(report.getName());
				existing.setYear(report.getYear());
				existing.setStatus(report.getStatus());

				AdventureCellReport updated = reportRepo.save(existing);
				if (updated != null) {
					FileWritting.createLog((HttpServletRequest) request,
							updated.getId() + ",updated," + "updated adventureCell Report,"
									+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
					map.put(ConstantMessage.LIST, updated);
					map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
					map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_ADDED_SUCCESSFULLY);
					return map;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;

	}

	@Override
	public Map<Object, Object> getAllReports() {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			// List<AdventureCellReport> list = reportRepo.findAll();

			List<AdventureCellReport> list = reportRepo.findAllByOrderByIdDesc();
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
			AdventureCellReport list = reportRepo.findById(id).get();
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
			AdventureCellReport list = reportRepo.findById(id).get();
			if (list != null) {
				list.setStatus(status);
				list.setUpdatedOn(new Date());

				reportRepo.save(list);

				FileWritting.createLog((HttpServletRequest) request,
						list.getId() + ",updated," + "status updated adventureCell Report,"
								+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
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
