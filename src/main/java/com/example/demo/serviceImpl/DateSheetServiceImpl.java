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

import com.example.demo.model.DateSheet;
import com.example.demo.repository.DateSheetRepository;
import com.example.demo.service.DateSheetService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;

@Service
public class DateSheetServiceImpl implements DateSheetService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	DateSheetRepository sheetRepo;

	@Override
	public Map<Object, Object> addDateSheet(MultipartFile document, DateSheet sheet, ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			if (document != null && !document.isEmpty()) {

				String filename = FileUploader.uploadProfileImage(document, UploadDir);
				sheet.setDocument(url + filename);
			}
			sheet = sheetRepo.save(sheet);
			if (sheet != null) {
				FileWritting.createLog((HttpServletRequest) request, sheet.getId() + ",added," + "add Date Sheet,"
						+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.LIST, sheet);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_MESSAGE);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_ADDED_SUCCESSFULLY);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

	@Override
	public Map<Object, Object> getAllRecords(Long termId) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			// List<DateSheet> sheetList = sheetRepo.findAll();

			List<DateSheet> sheetList = sheetRepo.findAllByTermIdOrderByIdDesc(termId);
			if (sheetList.size() != 0 && !sheetList.isEmpty()) {
				map.put(ConstantMessage.LIST, sheetList);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

	@Override
	public Map<Object, Object> viewDetailsById(Long id) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			DateSheet sheet = sheetRepo.findById(id).get();
			if (sheet != null) {
				map.put(ConstantMessage.LIST, sheet);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.message, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
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
	public Map<Object, Object> updateRecord(MultipartFile document, DateSheet update, ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			DateSheet sheet = sheetRepo.findById(update.getId()).get();
			if (sheet != null) {
				if (document != null && !document.isEmpty()) {

					String filename = FileUploader.uploadProfileImage(document, UploadDir);
					sheet.setDocument(url + filename);
				}
				sheet.setName(update.getName());
				sheet.setDescription(update.getDescription());
				sheet.setStatus(update.getStatus());
				sheet.setUpdatedOn(new Date());

				sheetRepo.save(sheet);
				FileWritting.createLog((HttpServletRequest) request, sheet.getId() + ",updated," + "updated Date Sheet,"
						+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());

				map.put(ConstantMessage.LIST, sheet);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.message, ConstantMessage.RECORD_UPDATED_SUCCESSFULLY);

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
	public Map<Object, Object> activeDeactiveStatus(Long id, int status, ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			DateSheet sheet = sheetRepo.findById(id).get();
			if (sheet != null) {
				sheet.setStatus(status);
				sheetRepo.save(sheet);

				FileWritting.createLog((HttpServletRequest) request, sheet.getId() + ",updated,"
						+ "status updated Date Sheet," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());

				map.put(ConstantMessage.LIST, sheet);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.message, ConstantMessage.STATUS_UPDATED_SUCCESSFULLY);

			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.message, ConstantMessage.INVALID_ID);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

}
