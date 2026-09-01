package com.example.demo.serviceImpl;

import java.util.ArrayList;
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

import com.example.demo.model.AdjutantBranch;
import com.example.demo.model.AdjutantDetails;
import com.example.demo.repository.AdjutantBranchRepo;
import com.example.demo.repository.AdjutantDetailsRepo;
import com.example.demo.service.AdjutantDetailsService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;

@Service
public class AdjutantDetailsServiceImpl implements AdjutantDetailsService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdjutantBranchRepo branchRepo;

	@Autowired
	AdjutantDetailsRepo adDetailsRepo;

	@Override
	public Map<Object, Object> getAdjutantBranch() {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<AdjutantBranch> list = branchRepo.findAll();
			if (list != null && list.size() != 0) {
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
	public Map<Object, Object> addDetails(AdjutantDetails details, MultipartFile document, ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {

			AdjutantDetails nameExist = adDetailsRepo.findByNameAndAdjutantBranchId(details.getName(), (long) 1);
			if (nameExist != null) {
				// throw new MyException(ConstantMessage.ARO_NUMBER_EXIST);
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.ARO_NUMBER_EXIST);
				return map;
			} else {
				if (document != null && !document.isEmpty()) {

					String filename = FileUploader.uploadProfileImage(document, UploadDir);
					details.setDocument(url + filename);
				}

				AdjutantDetails saved = adDetailsRepo.save(details);
				if (saved != null) {

					FileWritting.createLog((HttpServletRequest) request, saved.getId() + ",added,"
							+ "addAdjudantDetails," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
					map.put(ConstantMessage.LIST, saved);
					map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
					map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_ADDED_SUCCESSFULLY);
					return map;
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

	@Override
	public Map<Object, Object> getAllAdjutantDetails() {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			Integer[] deletedStatus = { 2 };
			List<AdjutantDetails> list = adDetailsRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
			if (list != null && list.size() != 0) {
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
	public Map<Object, Object> getDetailsById(Long id) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			AdjutantDetails details = adDetailsRepo.findById(id).get();
			if (details != null) {
				map.put(ConstantMessage.LIST, details);
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
	public Map<Object, Object> activeDeactiveStatus(Long id, int status, ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			AdjutantDetails details = adDetailsRepo.findById(id).get();
			if (details != null) {
				details.setStatus(status);
				details.setUpdatedOn(new Date());

				details = adDetailsRepo.save(details);

				FileWritting.createLog((HttpServletRequest) request, details.getId() + ",update status,"
						+ "activeDeactiveStatus," + ConstantMessage.STATUS_UPDATED_SUCCESSFULLY + "," + new Date());

				map.put(ConstantMessage.LIST, details);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.STATUS_UPDATED_SUCCESSFULLY);
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
	public Map<Object, Object> updateDetails(AdjutantDetails details, MultipartFile document, ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			AdjutantDetails existing = adDetailsRepo.findById(details.getId()).get();
			if (existing != null) {
				if (document != null && !document.isEmpty()) {

					String filename = FileUploader.uploadProfileImage(document, UploadDir);
					existing.setDocument(url + filename);
				}
				existing.setName(details.getName());
				existing.setStatus(details.getStatus());
				existing.setFlag(details.isFlag());
				existing.setUpdatedOn(new Date());
				existing.setDescription(details.getDescription());

				AdjutantBranch branch = branchRepo.findById(details.getAdjutantBranch().getId()).get();
				existing.setAdjutantBranch(branch);

				existing = adDetailsRepo.save(existing);

				FileWritting.createLog((HttpServletRequest) request, existing.getId() + ",update,"
						+ "updateAdjutantDetails," + ConstantMessage.RECORD_UPDATED_SUCCESSFULLY + "," + new Date());

				map.put(ConstantMessage.LIST, existing);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_UPDATED_SUCCESSFULLY);
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
	public Map<Object, Object> getDetailsByAdjutantBranch(Long id, int status,boolean flag) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<AdjutantDetails> list = new ArrayList<>();
			if (status == 1 || status == 0 ) {
				//list = adDetailsRepo.findByAdjutantBranchIdAndStatusOrderByIdDesc(id, status,);
				list = adDetailsRepo.findByAdjutantBranchIdAndStatusAndFlagOrderByIdDesc(id, status,flag);
			} else {
				list = adDetailsRepo.findByAdjutantBranchIdOrderByIdDesc(id);
				//list = adDetailsRepo.findByAdjutantBranchIdAndFlagOrderByIdDesc(id,flag);
			}
			if (list != null && list.size() != 0) {
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

}
