package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.ITPPP;
import com.example.demo.repository.ITPPPRepo;
import com.example.demo.service.ITPPPService;
import com.example.demo.util.FileUploader;

@Service
public class ITPPPServiceImpl implements ITPPPService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	ITPPPRepo itPppRepo;

	@Override
	public ITPPP addDetails(ITPPP record, MultipartFile file) {
		if (file != null && !file.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(file, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());

		return itPppRepo.save(record);
	}

	@Override
	public ITPPP updateDetails(ITPPP request, MultipartFile docFile) {
		ITPPP updated = null;
		ITPPP records = itPppRepo.findById(request.getId()).get();
		if (records != null) {
			if (docFile != null && !docFile.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
				records.setFile(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setDescription(request.getDescription());
			records.setName(request.getName());
			records.setStatus(request.getStatus());
			updated = itPppRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public ITPPP changeStatus(Long id, int status) {
		ITPPP record = itPppRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdatedAt(new Date());

			record = itPppRepo.save(record);
			return record;
		}
		return null;
	}

	@Override
	public ITPPP viewById(Long id) {
		ITPPP record = itPppRepo.findById(id).get();
		return record;
	}

	@Override
	public List<ITPPP> getList(int status) {
		List<ITPPP> list = new ArrayList<>();
		Integer[] deletedStatus = { 2 };
		if (status == 1 || status == 0) {
			list = itPppRepo.findAllByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = itPppRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

}
