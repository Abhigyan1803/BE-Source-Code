package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.DSCSecurity;
import com.example.demo.repository.DSCSecurityRepo;
import com.example.demo.service.DSCSecurityService;
import com.example.demo.util.FileUploader;

@Service
public class DSSecurityServiceImpl implements DSCSecurityService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	DSCSecurityRepo dscSecurityRepo;

	@Override
	public DSCSecurity addDetails(DSCSecurity record, MultipartFile docFile) {
		if (docFile != null && !docFile.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());

		return dscSecurityRepo.save(record);
	}

	@Override
	public DSCSecurity updateDetails(DSCSecurity request, MultipartFile docFile) {
		DSCSecurity updated = null;
		DSCSecurity records = dscSecurityRepo.findById(request.getId()).get();
		if (records != null) {
			if (docFile != null && !docFile.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
				records.setFile(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setDescription(request.getDescription());
			records.setName(request.getName());
			records.setStatus(request.getStatus());
			updated = dscSecurityRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public List<DSCSecurity> getList(int status) {
		List<DSCSecurity> list = new ArrayList<>();
		Integer[] deletedStatus = { 2 };
		if (status == 1 || status == 0) {
			list = dscSecurityRepo.findAllByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = dscSecurityRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

	@Override
	public DSCSecurity viewById(Long id) {
		DSCSecurity record = dscSecurityRepo.findById(id).get();
		return record;
	}

	@Override
	public DSCSecurity changeStatus(int status, Long id) {
		DSCSecurity record = dscSecurityRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdatedAt(new Date());

			record = dscSecurityRepo.save(record);
			return record;
		}
		return null;
	}

}
