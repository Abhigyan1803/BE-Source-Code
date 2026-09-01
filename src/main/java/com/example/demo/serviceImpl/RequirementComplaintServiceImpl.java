package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.model.AuthTable;
import com.example.demo.model.RequirementComplaint;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.RequirementComplaintRepo;
import com.example.demo.service.RequirementComplaintService;
import com.example.demo.util.ConstantVar;
import com.example.demo.util.FileUploader;

@Service
public class RequirementComplaintServiceImpl implements RequirementComplaintService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	RequirementComplaintRepo requirementComplaintRepo;

	@Autowired
	LoginRepository authRepo;

	@Override
	public RequirementComplaint addDetails(RequirementComplaint record, MultipartFile docFile, ServletRequest request) {
		AuthTable auth = getUser(request);
		record.setName(auth.getName());

		if (docFile != null && !docFile.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
			record.setFile(url + filename);
		}
		record.setRequestStatus("Action Required");
		record.setCreateAt(new Date());
		record.setUpdateAt(new Date());

		return requirementComplaintRepo.save(record);
	}

	@Override
	public RequirementComplaint updateDetails(RequirementComplaint request, MultipartFile docFile,
			ServletRequest servletRequest) {
		RequirementComplaint updated = null;
		RequirementComplaint records = requirementComplaintRepo.findById(request.getId()).get();
		if (records != null) {
			if (docFile != null && !docFile.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
				records.setFile(url + filename);
			}
			AuthTable auth = getUser(servletRequest);
			records.setName(auth.getName());
			records.setDetails(request.getDetails());
			records.setRemarks(request.getRemarks());
			records.setRequestNature(request.getRequestNature());
			records.setTitle(request.getTitle());
			records.setRequestType(request.getRequestType());
			records.setRequestStatus(request.getRequestStatus());
			records.setAddress(request.getAddress());
			records.setStatus(request.getStatus());
			records.setUpdateAt(new Date());
			updated = requirementComplaintRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public RequirementComplaint changeStatus(int status, Long id) {
		RequirementComplaint record = requirementComplaintRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdateAt(new Date());

			record = requirementComplaintRepo.save(record);
			return record;
		}
		return null;
	}

	@Override
	public RequirementComplaint viewById(Long id) {
		RequirementComplaint record = requirementComplaintRepo.findById(id).get();
		return record;
	}

	@Override
	public List<RequirementComplaint> getList(int status) {
		List<RequirementComplaint> list = new ArrayList<>();
		Integer[] deletedStatus = { 2 };
		if (status == 1 || status == 0) {
			list = requirementComplaintRepo.findAllByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = requirementComplaintRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

	public AuthTable getUser(ServletRequest request) {
		String header = ((HttpServletRequest) request).getHeader(ConstantVar.HEADER_STRING);
		String username = null;
		String authToken = null;
		JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		if (header != null && header.startsWith(ConstantVar.TOKEN_PREFIX)) {
			authToken = header.replace(ConstantVar.TOKEN_PREFIX, "");
			username = jwtTokenUtil.getUsernameFromToken(authToken);
		}
		AuthTable authUser = authRepo.findByUsername(username);
		return authUser;
	}

	@Override
	public List<RequirementComplaint> getByRequestNature(String requestNature, int status) {
		List<RequirementComplaint> list = requirementComplaintRepo.findByRequestNatureAndStatus(requestNature, status);
		return list;
	}

}
