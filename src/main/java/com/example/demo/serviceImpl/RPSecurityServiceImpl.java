package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.RPSecurity;
import com.example.demo.repository.RPSecurityRepo;
import com.example.demo.service.RPSecurityService;
import com.example.demo.util.FileUploader;

@Service
public class RPSecurityServiceImpl implements RPSecurityService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	RPSecurityRepo rpSecurityRepo;

	@Override
	public RPSecurity addDetails(RPSecurity record, MultipartFile docFile) {
		if (docFile != null && !docFile.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());

		return rpSecurityRepo.save(record);
	}

	@Override
	public RPSecurity updateDetails(RPSecurity request, MultipartFile docFile) {
		RPSecurity updated = null;
		RPSecurity records = rpSecurityRepo.findById(request.getId()).get();
		if (records != null) {
			if (docFile != null && !docFile.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
				records.setFile(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setDescription(request.getDescription());
			records.setName(request.getName());
			records.setStatus(request.getStatus());
			updated = rpSecurityRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public List<RPSecurity> getList(int status) {
		List<RPSecurity> list = new ArrayList<>();
		Integer[] deletedStatus = { 2 };
		if (status == 1 || status == 0) {
			list = rpSecurityRepo.findAllByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = rpSecurityRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

	@Override
	public RPSecurity viewById(Long id) {
		RPSecurity record = rpSecurityRepo.findById(id).get();
		return record;

	}

	@Override
	public RPSecurity changeStatus(int status, Long id) {
		RPSecurity record = rpSecurityRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdatedAt(new Date());

			record = rpSecurityRepo.save(record);
			return record;
		}
		return null;
	}

}
