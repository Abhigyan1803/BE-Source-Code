package com.example.demo.serviceImpl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cadet;
import com.example.demo.model.EdInitialInterview;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.EdInitialInterviewRepo;
import com.example.demo.service.EdInitialInterviewService;

@Service
public class EdInitialInterviewServiceImpl implements EdInitialInterviewService {
	@Autowired
	private EdInitialInterviewRepo repo;
	@Autowired
	AdminCadetRepo cadetRepo;

	@Override
	public EdInitialInterview addEdInitialInterview(EdInitialInterview edInitialInterview) {
		// TODO Auto-generated method stub
		return repo.save(edInitialInterview);
	}

	@Override
	public List<EdInitialInterview> getByServiceId(String serviceId) {
		List<EdInitialInterview> result = repo.findByServiceId(serviceId);
		if (result.size() > 0) {
			return result;
		}
		return null;

	}

	@Override
	public EdInitialInterview updateEdInitialInterview(EdInitialInterview edInitialInterview) {
		EdInitialInterview result = null;
		if (edInitialInterview != null && edInitialInterview.getId() != null && edInitialInterview.getId() != 0) {
			Optional<EdInitialInterview> initialInterview = repo.findById(edInitialInterview.getId());
			if (initialInterview.isPresent()) {
				result = initialInterview.get();
				if (result != null) {
					if (edInitialInterview.getIsViewByGc() == true && (result.getGcInitialsWithDate() == null
							|| result.getGcInitialsWithDate().trim().isEmpty())) {
						Cadet cadet = cadetRepo.findByServiceId(edInitialInterview.getServiceId());
						if (cadet != null) {
							String name = cadet.getName();
							String[] nameArr = name.split(" ");
							String gcInitialsWithDate = "";
							for (String gcName : nameArr) {
								gcInitialsWithDate = gcInitialsWithDate + gcName.charAt(0);
							}
							int year = Calendar.getInstance().get(Calendar.YEAR);
							int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
							int day = Calendar.getInstance().get(Calendar.DATE);
							gcInitialsWithDate = gcInitialsWithDate + "-" + day + "/" + month + "/" + year;
							result.setGcInitialsWithDate(gcInitialsWithDate);
						}
					}
					if (edInitialInterview.getDate() != null) {
						result.setDate(edInitialInterview.getDate());
					}

					if (edInitialInterview.getMajCol() != null) {
						result.setMajCol(edInitialInterview.getMajCol());
					}
					if (edInitialInterview.getInitialInterview() != null) {
						result.setInitialInterview(edInitialInterview.getInitialInterview());
					}
					if (edInitialInterview.getStatus() != null) {
						result.setStatus(edInitialInterview.getStatus());
					}
					result = repo.save(result);
				}
			}
		}
		return result;
	}

	@Override
	public EdInitialInterview getByServiceIdAndSubmittedBy(String serviceId, String submittedBy) {
		Optional<EdInitialInterview> result = repo.findByServiceIdAndSubmittedBy(serviceId, submittedBy);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}
}
