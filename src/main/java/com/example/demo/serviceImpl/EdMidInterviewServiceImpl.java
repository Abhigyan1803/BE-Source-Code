package com.example.demo.serviceImpl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cadet;
import com.example.demo.model.EdMidInterview;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.EdMidInterviewRepo;
import com.example.demo.service.EdMidInterviewService;

@Service
public class EdMidInterviewServiceImpl implements EdMidInterviewService {
	@Autowired
	private EdMidInterviewRepo repo;
	@Autowired
	AdminCadetRepo cadetRepo;

	@Override
	public EdMidInterview addEdMidInterview(EdMidInterview edMidInterview) {
		return repo.save(edMidInterview);
	}

	@Override
	public List<EdMidInterview> getByServiceId(String serviceId) {
		List<EdMidInterview> result = repo.findByServiceIdOrderById(serviceId);
		if (result.size() > 0) {
			return result;
		}
		return null;
	}

	@Override
	public EdMidInterview updateEdMidInterview(EdMidInterview edMidInterview) {
		EdMidInterview result = null;
		if (edMidInterview != null && edMidInterview.getId() != null && edMidInterview.getId() != 0) {
			Optional<EdMidInterview> midInterview = repo.findById(edMidInterview.getId());
			if (midInterview.isPresent()) {
				result = midInterview.get();
				if (result != null) {
					if (edMidInterview.getIsViewByGc() == true && (result.getGcInitialsWithDate() == null
							|| result.getGcInitialsWithDate().trim().isEmpty())) {
						Cadet cadet = cadetRepo.findByServiceId(edMidInterview.getServiceId());
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

					if (edMidInterview.getDate() != null) {
						result.setDate(edMidInterview.getDate());
					}

					if (edMidInterview.getCapt() != null) {
						result.setCapt(edMidInterview.getCapt());
					}
					if (edMidInterview.getStatus() != null) {
						result.setStatus(edMidInterview.getStatus());
					}
					if (edMidInterview.getDetails() != null) {
						result.setDetails(edMidInterview.getDetails());
					}
					result = repo.save(result);
				}
			}
		}
		return result;

	}

	@Override
	public EdMidInterview getByServiceIdAndSubmittedBy(String serviceId, String submittedBy) {
		Optional<EdMidInterview> result = repo.findByServiceIdAndSubmittedBy(serviceId, submittedBy);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

}
