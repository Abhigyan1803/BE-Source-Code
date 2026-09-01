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

import com.example.demo.model.GSO_OneTrg;
import com.example.demo.model.ScheduleOfCentralLecture;
import com.example.demo.repository.GSO_OneRepo;
import com.example.demo.repository.ScheduleOfCentralLectureRepository;
import com.example.demo.service.GSO_OneTrgService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;

@Service
public class GSO_OneTrgServiceImpl implements GSO_OneTrgService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	GSO_OneRepo gsoRepo;
	
	@Autowired
	ScheduleOfCentralLectureRepository centralLecRepo;

	@Override
	public Map<Object, Object> addTrgModule(MultipartFile img, String description, String title, int status,ServletRequest servletRequest) {
		HashMap<Object, Object> map = new HashMap<>();

		GSO_OneTrg gsoOne = new GSO_OneTrg();
		try {
			if (img != null && !img.isEmpty()) {

				String filename = FileUploader.uploadProfileImage(img, UploadDir);
				gsoOne.setDocument(url + filename);
			}
			gsoOne.setDescription(description);
			gsoOne.setStatus(status);
			gsoOne.setTitle(title);

			gsoOne = gsoRepo.save(gsoOne);

			if (gsoOne != null) {
				FileWritting.createLog((HttpServletRequest) servletRequest,gsoOne.getId() + ",added," + "addGSOOneTrg," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.LIST, gsoOne);
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
	public Map<Object, Object> getAllTrgModule() {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<GSO_OneTrg> gsoTrgList = gsoRepo.findAllByOrderByIdDesc();
			if (gsoTrgList.size() != 0) {
				map.put(ConstantMessage.LIST, gsoTrgList);
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
	public Map<Object, Object> updateTrgModule(Long id, MultipartFile img, String description, String title,
			int status,ServletRequest servletRequest) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			GSO_OneTrg gsoOne = gsoRepo.findById(id).get();
			if (gsoOne != null) {
				if (img != null && !img.isEmpty()) {

					String filename = FileUploader.uploadProfileImage(img, UploadDir);
					gsoOne.setDocument(url + filename);
				}
				if (!description.equals("") && description != "") {
					gsoOne.setDescription(description);
				}
				if (!title.equals("") && title != "") {
					gsoOne.setTitle(title);
				}
				gsoOne.setStatus(status);
				gsoOne.setUpdatedOn(new Date());

				gsoOne = gsoRepo.save(gsoOne);
				if (gsoOne != null) {
					FileWritting.createLog((HttpServletRequest) servletRequest,gsoOne.getId() + ",update," + "gsoContoller," + ConstantMessage.RECORD_UPDATED_SUCCESSFULLY + "," + new Date());
					map.put(ConstantMessage.LIST, gsoOne);
					map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
					map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_UPDATED_SUCCESSFULLY);
					return map;
				}
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
	public Map<Object, Object> activeDeactiveStatus(Long id, int status,ServletRequest servletRequest) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			GSO_OneTrg gsoTrgList = gsoRepo.findById(id).get();
			gsoTrgList.setStatus(status);
			gsoRepo.save(gsoTrgList);
			
			FileWritting.createLog((HttpServletRequest) servletRequest,gsoTrgList.getId() + ",status-update," + "gsoContoller," + ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
			map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.STATUS_UPDATED_SUCCESSFULLY);
			return map;
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;
	}

	@Override
	public Map<Object, Object> getTrgDetailsById(Long id) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			GSO_OneTrg gsoTrgList = gsoRepo.findById(id).get();
			if (gsoTrgList != null) {
				map.put(ConstantMessage.LIST, gsoTrgList);
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

	//-------------------------------------  Schedule of central lec ---------------------------------------------
	
	@Override
	public Map<Object, Object> addCentralLecture(MultipartFile img, String description, String title, int status,
			ServletRequest servletRequest) {
		HashMap<Object, Object> map = new HashMap<>();

		ScheduleOfCentralLecture centralLec = new ScheduleOfCentralLecture();
		try {
			if (img != null && !img.isEmpty()) {

				String filename = FileUploader.uploadProfileImage(img, UploadDir);
				centralLec.setDocument(url + filename);
			}
			centralLec.setDescription(description);
			centralLec.setStatus(status);
			centralLec.setTitle(title);

			centralLec = centralLecRepo.save(centralLec);

			if (centralLec != null) {
				FileWritting.createLog((HttpServletRequest) servletRequest,centralLec.getId() + ",added," + "addScheduleOfCentralLecture," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.LIST, centralLec);
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
	public Map<Object, Object> getAllCentralLecture() {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<ScheduleOfCentralLecture> centralLecList = centralLecRepo.findAllByOrderByIdDesc();
			if (centralLecList.size() != 0) {
				map.put(ConstantMessage.LIST, centralLecList);
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
	public Map<Object, Object> updateCentralLecture(Long id, MultipartFile img, String description, String title,
			int status, ServletRequest servletRequest) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			ScheduleOfCentralLecture centralLec = centralLecRepo.findById(id).get();
			if (centralLec != null) {
				if (img != null && !img.isEmpty()) {

					String filename = FileUploader.uploadProfileImage(img, UploadDir);
					centralLec.setDocument(url + filename);
				}
				if (!description.equals("") && description != "") {
					centralLec.setDescription(description);
				}
				if (!title.equals("") && title != "") {
					centralLec.setTitle(title);
				}
				centralLec.setStatus(status);
				centralLec.setUpdatedOn(new Date());

				centralLec = centralLecRepo.save(centralLec);
				if (centralLec != null) {
					FileWritting.createLog((HttpServletRequest) servletRequest,centralLec.getId() + ",update," + "gsoContoller," + ConstantMessage.RECORD_UPDATED_SUCCESSFULLY + "," + new Date());
					map.put(ConstantMessage.LIST, centralLec);
					map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
					map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_UPDATED_SUCCESSFULLY);
					return map;
				}
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
	public Map<Object, Object> activeDeactiveStatusCentralLec(Long id, int status, ServletRequest servletRequest) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			ScheduleOfCentralLecture centralLecList = centralLecRepo.findById(id).get();
			centralLecList.setStatus(status);
			centralLecRepo.save(centralLecList);
			
			FileWritting.createLog((HttpServletRequest) servletRequest,centralLecList.getId() + ",status-update-central-lecture," + "gsoContoller," + ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());
			map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.STATUS_UPDATED_SUCCESSFULLY);
			return map;
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;
	}
	
@Override
	public Map<Object, Object> getCentralLectureById(Long id) {
	HashMap<Object, Object> map = new HashMap<>();
	try {
		ScheduleOfCentralLecture centralLec = centralLecRepo.findById(id).get();
		if (centralLec != null) {
			map.put(ConstantMessage.LIST, centralLec);
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
}
