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

import com.example.demo.model.AdventureCellType;
import com.example.demo.model.Letter;
import com.example.demo.repository.AdventureCellTypeRepo;
import com.example.demo.repository.LetterRepository;
import com.example.demo.service.LetterService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;

@Service
public class LetterServiceImpl implements LetterService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	LetterRepository letterRepo;

	@Autowired
	AdventureCellTypeRepo acRepo;

	@Override
	public Map<Object, Object> addLetter(MultipartFile document, Letter letter, ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();

		try {
			if (document != null && !document.isEmpty()) {

				String filename = FileUploader.uploadProfileImage(document, UploadDir);
				letter.setDocument(url + filename);
			}

			letter.setDate(new Date());
			letter.setUpdatedOn(new Date());

			AdventureCellType acType = acRepo.findById(letter.getAcType().getId()).get();
			letter.setAcType(acType);

			letter = letterRepo.save(letter);
			if (letter != null) {
				FileWritting.createLog((HttpServletRequest) request, letter.getId() + ",added," + "add Letter,"
						+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.LIST, letter);
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
	public Map<Object, Object> getAllRecords() {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			// List<Letter> list = letterRepo.findAll();
			List<Letter> list = letterRepo.findAllByOrderByIdDesc();

			if (list.size() != 0 && !list.isEmpty()) {
				map.put(ConstantMessage.LIST, list);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

	@Override
	public Map<Object, Object> viewDetailsById(Long id) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			Letter letter = letterRepo.findById(id).get();
			if (letter != null) {
				map.put(ConstantMessage.LIST, letter);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.INVALID_ID);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}

		return map;
	}

	@Override
	public Map<Object, Object> updateRecord(MultipartFile document, Letter update, ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			Letter letter = letterRepo.findById(update.getId()).get();
			if (letter != null) {
				if (document != null && !document.isEmpty()) {

					String filename = FileUploader.uploadProfileImage(document, UploadDir);
					letter.setDocument(url + filename);
				}
				letter.setDescription(update.getDescription());
				letter.setName(update.getName());
				letter.setStatus(update.getStatus());
				letter.setUpdatedOn(new Date());

				AdventureCellType acType = acRepo.findById(update.getAcType().getId()).get();
				letter.setAcType(acType);

				letter = letterRepo.save(letter);
				FileWritting.createLog((HttpServletRequest) request, letter.getId() + ",updated ," + "updated Letter,"
						+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.LIST, letter);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.message, ConstantMessage.RECORD_UPDATED_SUCCESSFULLY);

			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.message, ConstantMessage.INVALID_ID);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

	@Override
	public Map<Object, Object> activeDeactiveStatus(Long id, int status, ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			Letter letter = letterRepo.findById(id).get();
			if (letter != null) {
				letter.setStatus(status);
				letter = letterRepo.save(letter);
				FileWritting.createLog((HttpServletRequest) request, letter.getId() + ",updated ,"
						+ "status updated Letter," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.LIST, letter);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.message, ConstantMessage.STATUS_UPDATED_SUCCESSFULLY);

			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.message, ConstantMessage.INVALID_ID);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

}
