package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Intake;
import com.example.demo.model.POC;
import com.example.demo.repository.IntakeRepo;
import com.example.demo.repository.POCRepo;
import com.example.demo.service.AdminStatsService;
import com.example.demo.util.FileUploader;

@Service
public class AdminStatsServiceImpl implements AdminStatsService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	POCRepo pocRepo;

	@Autowired
	IntakeRepo intakeRepo;

	@Override
	public POC addPOCDetails(POC pocDetails, MultipartFile file) {
		if (file != null && !file.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(file, UploadDir);
			pocDetails.setFile(url + filename);
		}
		pocDetails.setCreatedAt(new Date());
		pocDetails.setUpdatedAt(new Date());

		return pocRepo.save(pocDetails);
	}

	@Override
	public List<POC> getPOCList(int status) {
		List<POC> list = new ArrayList<>();
		Integer[] deletedStatus = { 2 };
		if (status == 1 || status == 0) {
			list = pocRepo.findAllByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = pocRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

	@Override
	public POC viewPOCById(Long id) {
		POC record = pocRepo.findById(id).get();
		return record;
	}

	@Override
	public POC changePOCStatus(Long id, int status) {
		POC record = pocRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdatedAt(new Date());

			record = pocRepo.save(record);
			return record;
		}
		return null;
	}

	@Override
	public POC updatePOCDetails(POC updateDetails, MultipartFile file) {
		POC updated = null;
		POC records = pocRepo.findById(updateDetails.getId()).get();
		if (records != null) {
			if (file != null && !file.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(file, UploadDir);
				records.setFile(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setDescription(updateDetails.getDescription());
			records.setName(updateDetails.getName());
			records.setStatus(updateDetails.getStatus());
			updated = pocRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public Intake addIntakeDetails(Intake intakeDetails, MultipartFile file) {
		if (file != null && !file.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(file, UploadDir);
			intakeDetails.setFile(url + filename);
		}
		intakeDetails.setCreatedAt(new Date());
		intakeDetails.setUpdatedAt(new Date());

		return intakeRepo.save(intakeDetails);

	}

	@Override
	public List<Intake> getIntakeList(int status) {
		List<Intake> list = new ArrayList<>();
		if (status == 1 || status == 0) {
			list = intakeRepo.findAllByStatusOrderByIdDesc(status);
		} else {
			list = intakeRepo.findAllByOrderByIdDesc();
		}
		return list;
	}

	@Override
	public Intake viewIntakeById(Long id) {
		Intake record = intakeRepo.findById(id).get();
		return record;
	}

	@Override
	public Intake changeIntakeStatus(Long id, int status) {
		Intake record = intakeRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdatedAt(new Date());

			record = intakeRepo.save(record);
			return record;
		}
		return null;
	}

	@Override
	public Intake updateIntakeDetails(Intake intakeDetails, MultipartFile file) {
		Intake updated = null;
		Intake records = intakeRepo.findById(intakeDetails.getId()).get();
		if (records != null) {
			if (file != null && !file.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(file, UploadDir);
				records.setFile(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setDescription(intakeDetails.getDescription());
			records.setName(intakeDetails.getName());
			records.setStatus(intakeDetails.getStatus());
			updated = intakeRepo.save(records);
			return updated;
		}
		return updated;
	}

}
