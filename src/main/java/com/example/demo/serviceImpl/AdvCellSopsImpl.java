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

import com.example.demo.model.AdvCellSops;
import com.example.demo.model.AdventureCellType;
import com.example.demo.model.TermSeason;
import com.example.demo.payload.GeneralInstructionReqPayload;
import com.example.demo.payload.UpdateGeneralInstructionPayload;
import com.example.demo.repository.AdvCellSopsRepo;
import com.example.demo.repository.AdventureCellTypeRepo;
import com.example.demo.repository.TermSeasonRepo;
import com.example.demo.service.AdvCellSopsService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;

@Service
public class AdvCellSopsImpl implements AdvCellSopsService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdvCellSopsRepo advCellSopsRepo;

	@Autowired
	AdventureCellTypeRepo acRepo;

	@Autowired
	TermSeasonRepo seasonRepo;

	@Override
	public Map<Object, Object> addAdvCellSops(GeneralInstructionReqPayload reqPayload, MultipartFile img,
			ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();

		AdvCellSops advCellSops = new AdvCellSops();
		try {
			if (img != null && !img.isEmpty()) {

				String filename = FileUploader.uploadProfileImage(img, UploadDir);
				advCellSops.setDocument(url + filename);
			}
			advCellSops.setDescription(reqPayload.getDescription());
			advCellSops.setDocName(reqPayload.getDocName());

			AdventureCellType actype = acRepo.findById((long) reqPayload.getAcType()).get();
			advCellSops.setAcType(actype);

			TermSeason season = seasonRepo.findById((long) reqPayload.getTerm()).get();
			advCellSops.setSeasonTerm(season);

			advCellSops.setStatus(reqPayload.getStatus());
			advCellSops.setYear(reqPayload.getYear());

			advCellSops = advCellSopsRepo.save(advCellSops);
			if (advCellSops != null) {
				FileWritting.createLog((HttpServletRequest) request, advCellSops.getId() + ",added,"
						+ "add Adventure Cell Sops," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.LIST, advCellSops);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_ADDED_SUCCESSFULLY);
				return map;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
			return map;
		}

		return map;
	}

	@Override
	public Map<Object, Object> getAllAdvCellSops() {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<AdvCellSops> advCellSopsList = advCellSopsRepo.findAllByOrderByIdDesc();
			if (advCellSopsList.size() != 0) {
				map.put(ConstantMessage.LIST, advCellSopsList);
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
	public Map<Object, Object> updateadvCellSops(UpdateGeneralInstructionPayload reqPayload, MultipartFile img,
			ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();

		try {
			AdvCellSops advCellSops = advCellSopsRepo.findById(reqPayload.getId()).get();
			if (advCellSops != null) {
				if (img != null && !img.isEmpty()) {

					String filename = FileUploader.uploadProfileImage(img, UploadDir);
					advCellSops.setDocument(url + filename);
				}
				advCellSops.setDescription(reqPayload.getDescription());
				advCellSops.setDocName(reqPayload.getDocName());

				AdventureCellType actype = acRepo.findById((long) reqPayload.getAcType()).get();
				advCellSops.setAcType(actype);

				TermSeason season = seasonRepo.findById((long) reqPayload.getTerm()).get();
				advCellSops.setSeasonTerm(season);

				advCellSops.setStatus(reqPayload.getStatus());
				advCellSops.setYear(reqPayload.getYear());
				advCellSops.setUpdatedOn(new Date());
				advCellSops = advCellSopsRepo.save(advCellSops);
				if (advCellSops != null) {
					FileWritting.createLog((HttpServletRequest) request,
							advCellSops.getId() + ",updated," + "update Adventure Cell Sops,"
									+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
					map.put(ConstantMessage.LIST, advCellSops);
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
			return map;

		}
		return map;
	}

	@Override
	public Map<Object, Object> viewDetailsById(Long id) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			AdvCellSops advCellSops = advCellSopsRepo.findById(id).get();
			if (advCellSops != null) {
				map.put(ConstantMessage.LIST, advCellSops);
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
	public Map<Object, Object> activeDeactiveStatus(Long id, int status, ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			AdvCellSops advCellSops = advCellSopsRepo.findById(id).get();
			if (advCellSops != null) {
				advCellSops.setStatus(status);
				advCellSopsRepo.save(advCellSops);

				FileWritting.createLog((HttpServletRequest) request,
						advCellSops.getId() + ",updated," + "status update Adventure Cell Sops,"
								+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());

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
	public Map<Object, Object> getSopsByStatus(int status) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<AdvCellSops> advCellSopsList = advCellSopsRepo.findByStatus(status);
			if (advCellSopsList.size() != 0) {
				map.put(ConstantMessage.LIST, advCellSopsList);
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
