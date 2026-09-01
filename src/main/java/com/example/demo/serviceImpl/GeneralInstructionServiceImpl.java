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
import com.example.demo.model.GeneralInstruction;
import com.example.demo.model.TermSeason;
import com.example.demo.payload.GeneralInstructionReqPayload;
import com.example.demo.payload.UpdateGeneralInstructionPayload;
import com.example.demo.repository.AdventureCellTypeRepo;
import com.example.demo.repository.GeneralInstructionRepo;
import com.example.demo.repository.TermSeasonRepo;
import com.example.demo.service.GeneralInstuctionService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;

@Service
public class GeneralInstructionServiceImpl implements GeneralInstuctionService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	GeneralInstructionRepo generalRepo;

	@Autowired
	AdventureCellTypeRepo acRepo;

	@Autowired
	TermSeasonRepo seasonRepo;

	@Override
	public Map<Object, Object> addGeneralInsturction(GeneralInstructionReqPayload reqPayload, MultipartFile img,
			ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();

		GeneralInstruction general = new GeneralInstruction();
		try {
			if (img != null && !img.isEmpty()) {

				String filename = FileUploader.uploadProfileImage(img, UploadDir);
				general.setDocument(url + filename);
			}
			general.setDescription(reqPayload.getDescription());
			general.setDocName(reqPayload.getDocName());

			AdventureCellType actype = acRepo.findById((long) reqPayload.getAcType()).get();
			general.setAcType(actype);

			TermSeason season = seasonRepo.findById((long) reqPayload.getTerm()).get();
			general.setSeasonTerm(season);

			general.setStatus(reqPayload.getStatus());
			general.setYear(reqPayload.getYear());

			general = generalRepo.save(general);
			if (general != null) {
				FileWritting.createLog((HttpServletRequest) request, general.getId() + ",added,"
						+ "add General Insturction," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.LIST, general);
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
	public Map<Object, Object> getAllInstruction() {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			// List<GeneralInstruction> instructionList= generalRepo.findAll();

			List<GeneralInstruction> instructionList = generalRepo.findAllByOrderByIdDesc();
			if (instructionList.size() != 0) {
				map.put(ConstantMessage.LIST, instructionList);
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
	public Map<Object, Object> updateGeneralInstruction(UpdateGeneralInstructionPayload reqPayload, MultipartFile img,
			ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();

		try {
			GeneralInstruction general = generalRepo.findById(reqPayload.getId()).get();
			if (general != null) {
				if (img != null && !img.isEmpty()) {

					String filename = FileUploader.uploadProfileImage(img, UploadDir);
					general.setDocument(url + filename);
				}
				general.setDescription(reqPayload.getDescription());
				general.setDocName(reqPayload.getDocName());
				general.setUpdatedOn(new Date());

				AdventureCellType actype = acRepo.findById((long) reqPayload.getAcType()).get();
				general.setAcType(actype);

				TermSeason season = seasonRepo.findById((long) reqPayload.getTerm()).get();
				general.setSeasonTerm(season);

				general.setStatus(reqPayload.getStatus());
				general.setYear(reqPayload.getYear());

				general = generalRepo.save(general);
				if (general != null) {
					FileWritting.createLog((HttpServletRequest) request,
							general.getId() + ",updated," + "updated General Insturction,"
									+ ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
					map.put(ConstantMessage.LIST, general);
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
			GeneralInstruction instruction = generalRepo.findById(id).get();
			if (instruction != null) {
				map.put(ConstantMessage.LIST, instruction);
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
			GeneralInstruction instruction = generalRepo.findById(id).get();
			if (instruction != null) {
				instruction.setStatus(status);
				generalRepo.save(instruction);
				FileWritting.createLog((HttpServletRequest) request,
						instruction.getId() + ",updated," + "status updated General Insturction,"
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
	public Map<Object, Object> getInstructionsByStatus(int status) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<GeneralInstruction> instructionList = generalRepo.findByStatus(status);
			if (instructionList.size() != 0) {
				map.put(ConstantMessage.LIST, instructionList);
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
