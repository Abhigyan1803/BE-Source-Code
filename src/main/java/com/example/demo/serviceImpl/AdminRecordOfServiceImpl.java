package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Officer;
import com.example.demo.payload.OfficerPayLoad;
import com.example.demo.repository.AdminRecordOfServiceRepo;
import com.example.demo.service.AdminRecordOfService;

@Service
public class AdminRecordOfServiceImpl implements AdminRecordOfService {

	@Autowired
	AdminRecordOfServiceRepo recordOfServiceRepo;

	@Override
	public Officer createOfficer(Officer officer) {
		Officer result = null;
		if (officer != null && officer.getId() == null) {
			officer.setCreatedAt(new Date());
			result = recordOfServiceRepo.save(officer);
		}
		return result;
	}

	@Override
	public Officer getOfficerById(Long id) {
		Optional<Officer> result = recordOfServiceRepo.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public List<Officer> getAllOfficerByStatus(Integer status) {
		List<Officer> result = null;
		Integer[] deletedStatus = { 2 };
		if (status == 1) {
			result = recordOfServiceRepo.findAllByStatusOrderByIdDesc(status);
		} else {
			result = recordOfServiceRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return result;
	}

	@Override
	public Officer ChangeOfficerStatus(Officer officer) {
		Officer result = null;
		if (officer != null && officer.getId() != null) {
			Optional<Officer> off = recordOfServiceRepo.findById(officer.getId());
			if (off.isPresent()) {
				result = off.get();
				if (officer.getStatus() != null) {
					result.setStatus(officer.getStatus());
					result = recordOfServiceRepo.save(result);
				}
			}
		}
		return result;
	}

	@Override
	public Officer UpdateOfficerDetails(Officer officer) {
		Officer result = null;
		if (officer != null && officer.getId() != null && officer.getId() != 0) {
			Optional<Officer> off = recordOfServiceRepo.findById(officer.getId());
			if (off.isPresent()) {
				officer.setCreatedAt(off.get().getCreatedAt());
				officer.setUpdatedAt(new Date());
				result = recordOfServiceRepo.save(officer);
			}
		}
		return result;
	}

	@Override
	public List<OfficerPayLoad> getActiveOfficers() {
		List<OfficerPayLoad> officerPayLoadList = null;
		List<Officer> officerList = recordOfServiceRepo.findAllByStatus(1);
		if (officerList.size() > 0) {
			officerPayLoadList = new ArrayList<OfficerPayLoad>();
			for (Officer officer : officerList) {
				OfficerPayLoad officerPayLoad = new OfficerPayLoad();
				officerPayLoad.setId(officer.getId());
				officerPayLoad.setName(officer.getName());
				officerPayLoad.setServiceId(officer.getPersonalNumber());
				officerPayLoadList.add(officerPayLoad);
			}
		}
		return officerPayLoadList;
	}

	@Override
	public Officer getOfficerByPersonalNumber(String personalNumber) {
		return recordOfServiceRepo.findByPersonalNumber(personalNumber);
	}

}