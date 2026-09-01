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

import com.example.demo.model.SopsDetails;
import com.example.demo.payload.AddSopDetails;
import com.example.demo.payload.GetDataOnlyById;
import com.example.demo.repository.SopDetailsRepo;
import com.example.demo.service.SopsDetailsService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;

@Service
public class SopDetailsServiceImpl implements SopsDetailsService {

	@Autowired
	SopDetailsRepo sopDetailsRepo;

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Override
	public Map<Object, Object> addSopsDetails(MultipartFile file, AddSopDetails request,ServletRequest servletRequest) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			SopsDetails sopsDetails = new SopsDetails();

			if (file != null && !file.isEmpty()) {

				String filename = FileUploader.uploadProfileImage(file, UploadDir);
				sopsDetails.setDocument(url + filename);
			}
			sopsDetails.setDescription(request.getDescription());
			sopsDetails.setName(request.getName());
			sopsDetails.setStatus(request.getStatus());

			SopsDetails sopsDetailsNew = sopDetailsRepo.save(sopsDetails);
			if (sopsDetailsNew != null) {
				FileWritting.createLog((HttpServletRequest) servletRequest,sopsDetailsNew.getId() + ",added," + "addSopsDetails," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.OBJECT_DETAILS, sopsDetailsNew);
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
	public Map<Object, Object> getAllSopsDetails() {
		HashMap<Object, Object> map = new HashMap<>();

		try {
			// Pageable
			// pagedData=PageRequest.of(request.getpNumber(),request.getPageSize());
			List<SopsDetails> sopDetailsList = sopDetailsRepo.findAllByOrderByIdDesc();

			if (sopDetailsList.size() != 0) {
				map.put(ConstantMessage.LIST, sopDetailsList);
				// map.put(ConstantMessage.LIST_SIZE,sopDetailsList.getTotalElements());
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
	public Map<Object, Object> getDetailsByOnlyById(GetDataOnlyById request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			SopsDetails sopDetails = sopDetailsRepo.findById(request.getId()).get();

			if (sopDetails != null) {
				map.put(ConstantMessage.OBJECT_DETAILS, sopDetails);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.INVALID_ID);
			}
		} catch (Exception ex) {
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

	@Override
	public Map<Object, Object> activeDeActiveSops(Long id, int status,ServletRequest servletRequest) {

		HashMap<Object, Object> map = new HashMap<>();
		try {
			SopsDetails sopsDetails = sopDetailsRepo.findById(id).get();
			if (sopsDetails != null) {
				sopsDetails.setStatus(status);
				sopsDetails = sopDetailsRepo.save(sopsDetails);
				
				FileWritting.createLog((HttpServletRequest) servletRequest,sopsDetails.getId() + ",status update," + "activeDeActiveSops," + ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());

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

	@Override
	public Map<Object, Object> updateSops(MultipartFile file, Long id, String name, String description, int status,ServletRequest servletRequest) {

		HashMap<Object, Object> map = new HashMap<>();
		try {
			SopsDetails sopsDetails = sopDetailsRepo.findById(id).get();

			if (sopsDetails != null) {

				if (file != null && !file.isEmpty()) {

					String filename = FileUploader.uploadProfileImage(file, UploadDir);
					sopsDetails.setDocument(url + filename);
				}

				sopsDetails.setDescription(description);
				sopsDetails.setName(name);
				sopsDetails.setStatus(status);
				sopsDetails.setUpdatedOn(new Date());

				SopsDetails sopsDetailsNew = sopDetailsRepo.save(sopsDetails);

				if (sopsDetailsNew != null) {
					FileWritting.createLog((HttpServletRequest) servletRequest,sopsDetails.getId() + ",update," + "updateSops," + ConstantMessage.RECORD_UPDATED_SUCCESSFULLY + "," + new Date());
					map.put(ConstantMessage.OBJECT_DETAILS, sopsDetailsNew);
					map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
					map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_UPDATED_SUCCESSFULLY);
					return map;
				} else {
					map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
					map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
				}
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.INVALID_ID);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<Object, Object> readRecords() {
		HashMap<Object, Object> map = new HashMap<>();
		List<String> list= FileWritting.readFile("C:/xampp/htdocs/FyrrrImages/words.txt");
	
		map.put(ConstantMessage.OBJECT_DETAILS,list);
		map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
		map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
	
		return map;
	}
	
}
