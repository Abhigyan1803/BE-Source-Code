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

import com.example.demo.model.ParadeStateOfGCS;
import com.example.demo.repository.ParadeStateOfGCSRepo;
import com.example.demo.service.ParadeStateofGCSService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;

@Service
public class ParadeStateOfGCSServiceImpl implements ParadeStateofGCSService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	ParadeStateOfGCSRepo paradeRepo;

	@Override
	public Map<Object, Object> addDetails(MultipartFile doc, ParadeStateOfGCS record, ServletRequest servletRequest) {

		HashMap<Object, Object> map = new HashMap<>();
		try {
			if (doc != null && !doc.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(doc, UploadDir);
				record.setDocument(url + filename);
			}
			record.setCreatedAt(new Date());
			record.setUpdatedOn(new Date());

			ParadeStateOfGCS saved = paradeRepo.save(record);
			if (saved != null) {
				FileWritting.createLog((HttpServletRequest) servletRequest, saved.getId() + ",added," + "parade_state,"
						+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.LIST, saved);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_ADDED_SUCCESSFULLY);
				return map;
			}
		} catch (Exception ex) {
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.message, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

	@Override
	public Map<Object, Object> updateDetails(MultipartFile doc, ParadeStateOfGCS update,
			ServletRequest servletRequest) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			ParadeStateOfGCS existing = paradeRepo.findById(update.getId()).get();
			if (existing != null) {
				if (doc != null && !doc.isEmpty()) {
					String filename = FileUploader.uploadProfileImage(doc, UploadDir);
					existing.setDocument(url + filename);
				}
				existing.setName(update.getName());
				existing.setStatus(update.getStatus());
				existing.setUpdatedOn(new Date());
				existing.setBattalion(update.getBattalion());
				existing.setTerm(update.getTerm());

				ParadeStateOfGCS updated = paradeRepo.save(existing);
				if (updated != null) {
					FileWritting.createLog((HttpServletRequest) servletRequest, updated.getId() + ",update,"
							+ "parade_state," + ConstantMessage.RECORD_UPDATED_SUCCESSFULLY + "," + new Date());
					map.put(ConstantMessage.LIST, updated);
					map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
					map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_UPDATED_SUCCESSFULLY);
					return map;
				}
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.INVALID_ID);
			}
		} catch (Exception ex) {
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.message, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

	@Override
	public Map<Object, Object> viewById(Long id) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			ParadeStateOfGCS record = paradeRepo.findById(id).get();
			if (record != null) {
				map.put(ConstantMessage.LIST, record);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.INVALID_ID);
			}

		} catch (Exception ex) {
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.message, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

	@Override
	public Map<Object, Object> activeDeactiveStatus(Long id, int status, ServletRequest servletRequest) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			ParadeStateOfGCS record = paradeRepo.findById(id).get();
			if (record != null) {
				record.setStatus(status);
				record.setUpdatedOn(new Date());

				paradeRepo.save(record);

				FileWritting.createLog((HttpServletRequest) servletRequest, record.getId() + ",update_status,"
						+ "parade_state," + ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.LIST, record);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.STATUS_UPDATED_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.INVALID_ID);
			}
		} catch (Exception ex) {
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.message, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

	@Override
	public Map<Object, Object> getAllDetails() {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			// Pageable
			// pagedData=PageRequest.of(request.getpNumber(),request.getPageSize());
			Integer[] deletedStatus = { 2 };
			List<ParadeStateOfGCS> list = paradeRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
			if (!list.isEmpty()) {
				map.put(ConstantMessage.LIST, list);
				// map.put(ConstantMessage.LIST_SIZE,list.getTotalElements());
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}

		} catch (Exception ex) {
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.message, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

}
