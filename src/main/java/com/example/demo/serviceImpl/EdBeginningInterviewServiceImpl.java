package com.example.demo.serviceImpl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cadet;
import com.example.demo.model.EdBeginningInterview;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.EdBeginningInterviewRepo;
import com.example.demo.service.EdBeginningInterviewService;

@Service
public class EdBeginningInterviewServiceImpl implements EdBeginningInterviewService {
	@Autowired
	private EdBeginningInterviewRepo repo;
	@Autowired
	AdminCadetRepo cadetRepo;

	@Override
	public EdBeginningInterview addEdBeginningInterview(EdBeginningInterview edBeginningInterview) {
		return repo.save(edBeginningInterview);
	}

	@Override
	public List<EdBeginningInterview> getByServiceId(String serviceId) {
		List<EdBeginningInterview> result = repo.findByServiceIdOrderById(serviceId);
		if (result.size() > 0) {
			return result;
		}
		return null;
	}

	@Override
	public EdBeginningInterview updateEdBeginningInterview(EdBeginningInterview edBeginningInterview) {
		EdBeginningInterview result = null;
		if (edBeginningInterview != null && edBeginningInterview.getId() != null && edBeginningInterview.getId() != 0) {
			Optional<EdBeginningInterview> beginningInterview = repo.findById(edBeginningInterview.getId());
			if (beginningInterview.isPresent()) {
				result = beginningInterview.get();
				if (result != null) {
					if (edBeginningInterview.getIsViewByGc() == true && (result.getGcInitialsWithDate() == null
							|| result.getGcInitialsWithDate().trim().isEmpty())) {
						Cadet cadet = cadetRepo.findByServiceId(edBeginningInterview.getServiceId());
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

					if (edBeginningInterview.getDate() != null) {
						result.setDate(edBeginningInterview.getDate());
					}

					if (edBeginningInterview.getCapt() != null) {
						result.setCapt(edBeginningInterview.getCapt());
					}
					if (edBeginningInterview.getStatus() != null) {
						result.setStatus(edBeginningInterview.getStatus());
					}
					if (edBeginningInterview.getDetails() != null) {
						result.setDetails(edBeginningInterview.getDetails());
					}
					result = repo.save(result);
				}
			}
		}
		return result;
	}

	@Override
	public EdBeginningInterview getByServiceIdAndSubmittedBy(String serviceId, String submittedBy) {
		Optional<EdBeginningInterview> result = repo.findByServiceIdAndSubmittedBy(serviceId, submittedBy);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

}
