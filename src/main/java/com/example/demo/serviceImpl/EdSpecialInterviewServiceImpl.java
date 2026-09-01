package com.example.demo.serviceImpl;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cadet;
import com.example.demo.model.EdSpecialInterview;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.EdSpecialInterviewRepo;
import com.example.demo.service.EdSpecialInterviewService;

@Service
public class EdSpecialInterviewServiceImpl implements EdSpecialInterviewService {
	@Autowired
	private EdSpecialInterviewRepo repo;

	@Autowired
	AdminCadetRepo cadetRepo;

	@Override
	public EdSpecialInterview addEdSpecialInterview(EdSpecialInterview edSpecialInterview) {
		// TODO Auto-generated method stub
		return repo.save(edSpecialInterview);
	}

	@Override
	public EdSpecialInterview getByServiceId(String serviceId) {
		// TODO Auto-generated method stub
		Optional<EdSpecialInterview> result = repo.findByServiceId(serviceId);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public EdSpecialInterview updateEdSpecialInterview(EdSpecialInterview edSpecialInterview) {
		EdSpecialInterview result = null;
		if (edSpecialInterview != null && edSpecialInterview.getId() != null && edSpecialInterview.getId() != 0) {
			Optional<EdSpecialInterview> specialInterview = repo.findById(edSpecialInterview.getId());
			if (specialInterview.isPresent()) {
				result = specialInterview.get();
				if (result != null) {
					if (edSpecialInterview.getIsViewByGc() == true && (result.getGcInitialsWithDate() == null
							|| result.getGcInitialsWithDate().trim().isEmpty())) {
						Cadet cadet = cadetRepo.findByServiceId(edSpecialInterview.getServiceId());
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
					if (edSpecialInterview.getDate() != null) {
						result.setDate(edSpecialInterview.getDate());
					}

					if (edSpecialInterview.getSpecialInterview() != null) {
						result.setSpecialInterview(edSpecialInterview.getSpecialInterview());
					}
					result = repo.save(result);
				}
			}
		}
		return result;

	}

}