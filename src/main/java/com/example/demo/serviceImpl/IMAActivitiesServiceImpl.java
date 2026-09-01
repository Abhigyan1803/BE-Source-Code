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

import com.example.demo.model.IMAActivities;
import com.example.demo.repository.IMAActivityRepo;
import com.example.demo.service.IMAActivitiesService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;

@Service
public class IMAActivitiesServiceImpl implements IMAActivitiesService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	IMAActivityRepo imaRepo;

	@Override
	public Map<Object, Object> addActivity(MultipartFile img, int status, ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		IMAActivities imaActivity = new IMAActivities();
		try {
			if (img != null && !img.isEmpty()) {

				String filename = FileUploader.uploadProfileImage(img, UploadDir);
				imaActivity.setImage(url + filename);
			}
			imaActivity.setStatus(status);

			imaActivity = imaRepo.save(imaActivity);

			if (imaActivity != null) {
				FileWritting.createLog((HttpServletRequest) request, imaActivity.getId() + ",added," + "add Activity,"
						+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.LIST, imaActivity);
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
	public Map<Object, Object> getAllActivities() {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<IMAActivities> activityList = imaRepo.findAllByOrderByIdDesc();
			if (activityList != null) {
				map.put(ConstantMessage.LIST, activityList);
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
	public Map<Object, Object> getActivitiesByStatus(int status) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<IMAActivities> activityList = imaRepo.findByStatusOrderByCreatedDateDesc(status);
			if (activityList != null) {
				map.put(ConstantMessage.LIST, activityList);
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
	public Map<Object, Object> activeDeactiveActivity(Long id, int status, ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			IMAActivities imaActivity = imaRepo.findById(id).get();
			if (imaActivity != null) {
				imaActivity.setStatus(status);
				imaActivity = imaRepo.save(imaActivity);

				FileWritting.createLog((HttpServletRequest) request, imaActivity.getId() + ",updated,"
						+ "status updated Activity," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());

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
