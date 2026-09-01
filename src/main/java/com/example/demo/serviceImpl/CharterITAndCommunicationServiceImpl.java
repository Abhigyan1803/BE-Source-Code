package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.CommunicationSecCharter;
import com.example.demo.model.ITSecCharter;
import com.example.demo.repository.CommunicationSecCharterRepo;
import com.example.demo.repository.ITSecCharterRepo;
import com.example.demo.service.CharterITAndCommunicationService;
import com.example.demo.util.FileUploader;

@Service
public class CharterITAndCommunicationServiceImpl implements CharterITAndCommunicationService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	ITSecCharterRepo itSecRepo;

	@Autowired
	CommunicationSecCharterRepo communicationSecCharterRepo;

	@Override
	public ITSecCharter addITDetails(ITSecCharter record, MultipartFile docFile) {
		if (docFile != null && !docFile.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());

		return itSecRepo.save(record);
	}

	@Override
	public ITSecCharter updateITDetails(ITSecCharter request, MultipartFile docFile) {
		ITSecCharter updated = null;
		ITSecCharter records = itSecRepo.findById(request.getId()).get();
		if (records != null) {
			if (docFile != null && !docFile.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
				records.setFile(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setDescription(request.getDescription());
			records.setName(request.getName());
			records.setStatus(request.getStatus());
			updated = itSecRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public List<ITSecCharter> getITList(int status) {
		List<ITSecCharter> list = new ArrayList<>();
		Integer[] deletedStatus = { 2 };
		if (status == 1 || status == 0) {
			list = itSecRepo.findAllByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = itSecRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

	@Override
	public ITSecCharter viewITById(Long id) {
		ITSecCharter record = itSecRepo.findById(id).get();
		return record;
	}

	@Override
	public ITSecCharter changeITStatus(int status, Long id) {
		ITSecCharter record = itSecRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdatedAt(new Date());

			record = itSecRepo.save(record);
			return record;
		}
		return null;
	}

	@Override
	public CommunicationSecCharter addCommunicationDetails(CommunicationSecCharter record, MultipartFile docFile) {
		if (docFile != null && !docFile.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setCreatedAt(new Date());
		record.setUpdatedAt(new Date());

		return communicationSecCharterRepo.save(record);
	}

	@Override
	public CommunicationSecCharter updateCommunicationDetails(CommunicationSecCharter request, MultipartFile docFile) {
		CommunicationSecCharter updated = null;
		CommunicationSecCharter records = communicationSecCharterRepo.findById(request.getId()).get();
		if (records != null) {
			if (docFile != null && !docFile.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
				records.setFile(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setDescription(request.getDescription());
			records.setName(request.getName());
			records.setStatus(request.getStatus());
			updated = communicationSecCharterRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public List<CommunicationSecCharter> getCommunicationList(int status) {
		List<CommunicationSecCharter> list = new ArrayList<>();
		Integer[] deletedStatus = { 2 };
		if (status == 1 || status == 0) {
			list = communicationSecCharterRepo.findAllByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = communicationSecCharterRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

	@Override
	public CommunicationSecCharter viewCommunicationById(Long id) {
		CommunicationSecCharter record = communicationSecCharterRepo.findById(id).get();
		return record;
	}

	@Override
	public CommunicationSecCharter changeCommunicationStatus(int status, Long id) {
		CommunicationSecCharter record = communicationSecCharterRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdatedAt(new Date());

			record = communicationSecCharterRepo.save(record);
			return record;
		}
		return null;
	}

}
