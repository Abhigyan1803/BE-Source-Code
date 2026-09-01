package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.TerritorialArmySecurity;
import com.example.demo.repository.TerritorialArmySecurityRepo;
import com.example.demo.service.TerritorialArmySecurityService;
import com.example.demo.util.FileUploader;

@Service
public class TerritorialArmySecurityServiceImpl implements TerritorialArmySecurityService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	TerritorialArmySecurityRepo territorialArmyRepo;

	@Override
	public TerritorialArmySecurity addDetails(TerritorialArmySecurity record, MultipartFile docFile) {
		if (docFile != null && !docFile.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());

		return territorialArmyRepo.save(record);
	}

	@Override
	public TerritorialArmySecurity updateDetails(TerritorialArmySecurity request, MultipartFile docFile) {
		TerritorialArmySecurity updated = null;
		TerritorialArmySecurity records = territorialArmyRepo.findById(request.getId()).get();
		if (records != null) {
			if (docFile != null && !docFile.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
				records.setFile(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setDescription(request.getDescription());
			records.setName(request.getName());
			records.setStatus(request.getStatus());
			updated = territorialArmyRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public List<TerritorialArmySecurity> getList(int status) {
		Integer[] deletedStatus = { 2 };
		List<TerritorialArmySecurity> list = new ArrayList<>();
		if (status == 1 || status == 0) {
			list = territorialArmyRepo.findAllByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = territorialArmyRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

	@Override
	public TerritorialArmySecurity viewById(Long id) {
		TerritorialArmySecurity record = territorialArmyRepo.findById(id).get();
		return record;
	}

	@Override
	public TerritorialArmySecurity changeStatus(int status, Long id) {
		TerritorialArmySecurity record = territorialArmyRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdatedAt(new Date());

			record = territorialArmyRepo.save(record);
			return record;
		}
		return null;
	}

}
