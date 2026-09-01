package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.DemoCoys;
import com.example.demo.repository.DemoCoysRepo;
import com.example.demo.service.DemoCoysService;
import com.example.demo.util.FileUploader;

@Service
public class DemoCoysServiceImpl implements DemoCoysService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	DemoCoysRepo coysRepo;

	@Override
	public DemoCoys addDetails(DemoCoys record, MultipartFile docFile) {
		if (docFile != null && !docFile.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());

		return coysRepo.save(record);
	}

	@Override
	public DemoCoys updateDetails(DemoCoys request, MultipartFile docFile) {
		DemoCoys updated = null;
		DemoCoys records = coysRepo.findById(request.getId()).get();
		if (records != null) {
			if (docFile != null && !docFile.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
				records.setFile(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setDescription(request.getDescription());
			records.setName(request.getName());
			records.setStatus(request.getStatus());
			updated = coysRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public List<DemoCoys> getList(int status) {
		List<DemoCoys> list = new ArrayList<>();
		Integer[] deletedStatus = { 2 };
		if (status == 1 || status == 0) {
			list = coysRepo.findAllByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = coysRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

	@Override
	public DemoCoys viewById(Long id) {
		DemoCoys record = coysRepo.findById(id).get();
		return record;
	}

	@Override
	public DemoCoys changeStatus(int status, Long id) {
		DemoCoys record = coysRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdatedAt(new Date());

			record = coysRepo.save(record);
			return record;
		}
		return null;
	}

}
