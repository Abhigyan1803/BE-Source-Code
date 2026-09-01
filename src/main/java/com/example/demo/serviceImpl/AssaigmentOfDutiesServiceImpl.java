package com.example.demo.serviceImpl;

import java.util.ArrayList;
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

import com.example.demo.model.AssaigmentOfDuties;
import com.example.demo.model.Battalion;
import com.example.demo.repository.AdminBattalionRepo;
import com.example.demo.repository.AssaigmentOfDutiesRepo;
import com.example.demo.service.AssaigmentOfDutiesService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;

@Service
public class AssaigmentOfDutiesServiceImpl implements AssaigmentOfDutiesService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AssaigmentOfDutiesRepo dutiesRepo;

	@Autowired
	AdminBattalionRepo battalionRepo;

	@Override
	public Map<Object, Object> addDuties(AssaigmentOfDuties duties, MultipartFile doc, ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			if (doc != null && !doc.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(doc, UploadDir);
				duties.setDocument(url + filename);
			}
			Battalion type = battalionRepo.findById(duties.getBattalionType().getId()).get();

			duties.setBattalionType(type);

			AssaigmentOfDuties saved = dutiesRepo.save(duties);
			if (saved != null) {
				FileWritting.createLog((HttpServletRequest) request, saved.getId() + ",added,"
						+ "add Assaigment Duties," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
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
	public Map<Object, Object> updateDuties(AssaigmentOfDuties duties, MultipartFile doc, ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			AssaigmentOfDuties existing = dutiesRepo.findById(duties.getId()).get();
			if (existing != null) {
				if (doc != null && !doc.isEmpty()) {
					String filename = FileUploader.uploadProfileImage(doc, UploadDir);
					existing.setDocument(url + filename);
				}
				existing.setUpdatedOn(new Date());

				Battalion type = battalionRepo.findById(duties.getBattalionType().getId()).get();
				existing.setBattalionType(type);
				existing.setStatus(duties.getStatus());

				existing = dutiesRepo.save(existing);
				if (existing != null) {
					FileWritting.createLog((HttpServletRequest) request,
							existing.getId() + ",updated," + "updated Assaigment Duties,"
									+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
					map.put(ConstantMessage.LIST, existing);
					map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
					map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_UPDATED_SUCCESSFULLY);
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
	public Map<Object, Object> viewDetailsById(Long id) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			AssaigmentOfDuties record = dutiesRepo.findById(id).get();
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
			AssaigmentOfDuties record = dutiesRepo.findById(id).get();
			if (record != null) {
				record.setStatus(status);
				record.setUpdatedOn(new Date());

				record = dutiesRepo.save(record);
				FileWritting.createLog((HttpServletRequest) request,
						record.getId() + ",updated," + "status updated Assaigment Duties,"
								+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.LIST, record);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.STATUS_UPDATED_SUCCESSFULLY);
				return map;
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
	public Map<Object, Object> getAllDuties(int battalionId, int status) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<AssaigmentOfDuties> list  = new ArrayList<>();
			if(battalionId ==  0 && status == 2)
			{
				list = dutiesRepo.findAllByOrderByIdDesc();
			}
			else if(battalionId > 0 && battalionId < 5 && status == 2)
			{ 
				list = dutiesRepo.findByBattalionTypeIdOrderByIdDesc(battalionId);
			}
			else if(battalionId > 0 && battalionId < 5 && status < 2)
			{
				list = dutiesRepo.findByBattalionTypeIdAndStatusOrderByIdDesc(battalionId,status);
			}
			else if(battalionId == 0 && status < 2)
			{
				list = dutiesRepo.findAllByStatusOrderByIdDesc(status);
			}
			else
			{
				list = dutiesRepo.findAllByOrderByIdDesc();
			}
			
			if (list != null && list.size() != 0) {
				map.put(ConstantMessage.LIST, list);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
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

	
}
