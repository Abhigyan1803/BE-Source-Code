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

import com.example.demo.model.GSO2ServiceSubject;
import com.example.demo.repository.GSO2ServiceSubjectRepo;
import com.example.demo.service.GSO2ServiceSubjectService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;

@Service
public class GSO2ServiceSubjectServiceImpl implements GSO2ServiceSubjectService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;
	@Autowired
	GSO2ServiceSubjectRepo serviceSubrepo;

	@Override
	public Map<Object, Object> addServiceSubject(MultipartFile document, GSO2ServiceSubject serviceSubject,
			ServletRequest request) {
		// TODO Auto-generated method stub
		HashMap<Object, Object> map = new HashMap<>();
		try {
			if (document != null && !document.isEmpty()) {

				String filename = FileUploader.uploadProfileImage(document, UploadDir);
				serviceSubject.setDocument(url + filename);
			}
			serviceSubject = serviceSubrepo.save(serviceSubject);
			if (serviceSubject != null) {
				FileWritting.createLog((HttpServletRequest) request, serviceSubject.getId() + ",added,"
						+ "add Service Subject," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.LIST, serviceSubject);
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
	public Map<Object, Object> getAllServiceSubjectRecords() {
		// TODO Auto-generated method stub
		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<GSO2ServiceSubject> serviceSubjectList = serviceSubrepo.findAllByOrderByIdDesc();
			if (serviceSubjectList.size() != 0 && !serviceSubjectList.isEmpty()) {
				map.put(ConstantMessage.LIST, serviceSubjectList);
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
	public Map<Object, Object> viewServiceSubjectDetailsById(Long id) {
		// TODO Auto-generated method stub
		HashMap<Object, Object> map = new HashMap<>();
		try {
			GSO2ServiceSubject serviceSubject = serviceSubrepo.findById(id).get();
			if (serviceSubject != null) {
				map.put(ConstantMessage.LIST, serviceSubject);
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
	public Map<Object, Object> updateServiceSubjectRecord(MultipartFile document, GSO2ServiceSubject update,
			ServletRequest request) {
		// TODO Auto-generated method stub
		HashMap<Object, Object> map = new HashMap<>();
		try {
			GSO2ServiceSubject serviceSubject = serviceSubrepo.findById(update.getId()).get();
			if (serviceSubject != null) {
				if (document != null && !document.isEmpty()) {

					String filename = FileUploader.uploadProfileImage(document, UploadDir);
					serviceSubject.setDocument(url + filename);
				}
				serviceSubject.setName(update.getName());
				serviceSubject.setDescription(update.getDescription());
				serviceSubject.setStatus(update.getStatus());
				serviceSubject.setUpdatedOn(new Date());
				serviceSubject.setServiceSubjectType(update.getServiceSubjectType());
				serviceSubrepo.save(serviceSubject);
				FileWritting.createLog((HttpServletRequest) request, serviceSubject.getId() + ",updated,"
						+ "updated Service Subject," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());

				map.put(ConstantMessage.LIST, serviceSubject);
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
	public Map<Object, Object> activeDeactiveStatusServiceSubject(Long id, int status, ServletRequest request) {
		// TODO Auto-generated method stub
		HashMap<Object, Object> map = new HashMap<>();
		try {
			GSO2ServiceSubject serviceSubject = serviceSubrepo.findById(id).get();
			if (serviceSubject != null) {
				serviceSubject.setStatus(status);
				serviceSubrepo.save(serviceSubject);

				FileWritting.createLog((HttpServletRequest) request,
						serviceSubject.getId() + ",updated," + "status updated Service subject ,"
								+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());

				map.put(ConstantMessage.LIST, serviceSubject);
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

	@Override
	public Map<Object, Object> getAllServiceSubjectByTypeSubTypeAndTerm(String type, String subType, Long termId) {
		HashMap<Object, Object> map = new HashMap<>();
		try {

			List<GSO2ServiceSubject> serviceSubjectList = serviceSubrepo
					.findAllByTypeAndSubTypeAndTermIdOrderByIdDesc(type, subType, termId);
			if (serviceSubjectList.size() != 0 && !serviceSubjectList.isEmpty()) {
				map.put(ConstantMessage.LIST, serviceSubjectList);
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
}
