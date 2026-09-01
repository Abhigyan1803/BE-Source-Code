package com.example.demo.serviceImpl;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cadet;
import com.example.demo.model.EdInterviewSheet;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.EdInterviewSheetRepo;
import com.example.demo.service.EdInterviewSheetService;

@Service
public class EdInterviewSheetServiceImpl implements EdInterviewSheetService {
	@Autowired
	private EdInterviewSheetRepo repo;

	@Autowired
	AdminCadetRepo cadetRepo;

	@Override
	public EdInterviewSheet addEdInterviewSheet(EdInterviewSheet edInterviewSheet) {
		// TODO Auto-generated method stub
		return repo.save(edInterviewSheet);
	}

	@Override
	public EdInterviewSheet getByServiceId(String serviceId) {
		// TODO Auto-generated method stub
		Optional<EdInterviewSheet> result = repo.findByServiceId(serviceId);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public EdInterviewSheet updateEdInterviewSheetService(EdInterviewSheet edInterviewSheet) {
		EdInterviewSheet result = null;
		if (edInterviewSheet != null && edInterviewSheet.getId() != null && edInterviewSheet.getId() != 0) {
			Optional<EdInterviewSheet> interviewInterview = repo.findById(edInterviewSheet.getId());
			if (interviewInterview.isPresent()) {
				result = interviewInterview.get();
				if (result != null) {

					if (edInterviewSheet.getIsViewByGc() == true) {
						Cadet cadet = cadetRepo.findByServiceId(edInterviewSheet.getServiceId());
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

							if (result.getAppdate() != null && result.getAppearence() != null
									&& !result.getAppearence().trim().isEmpty()
									&& (result.getAppGcInitialsWithDate() == null
											|| result.getAppGcInitialsWithDate().trim().isEmpty())) {
								result.setAppGcInitialsWithDate(gcInitialsWithDate);
							}
							if (result.getFamDate() != null && result.getFamilyback() != null
									&& !result.getFamilyback().trim().isEmpty()
									&& (result.getFamiGcInitialsWithDate() == null
											|| result.getFamiGcInitialsWithDate().trim().isEmpty())) {
								result.setFamiGcInitialsWithDate(gcInitialsWithDate);
							}
							if (result.getWorkDate() != null && result.getWorkExp() != null
									&& !result.getWorkExp().trim().isEmpty()
									&& (result.getWorkGcInitialsWithDate() == null
											|| result.getWorkGcInitialsWithDate().trim().isEmpty())) {
								result.setWorkGcInitialsWithDate(gcInitialsWithDate);
							}
							if (result.getIniDate() != null && result.getInitialAss() != null
									&& !result.getInitialAss().trim().isEmpty()
									&& (result.getIniGcInitialsWithDate() == null
											|| result.getIniGcInitialsWithDate().trim().isEmpty())) {
								result.setIniGcInitialsWithDate(gcInitialsWithDate);
							}
							if (result.getMisDate() != null && result.getMisc() != null
									&& !result.getMisc().trim().isEmpty() && (result.getMisGcInitialsWithDate() == null
											|| result.getMisGcInitialsWithDate().trim().isEmpty())) {
								result.setMisGcInitialsWithDate(gcInitialsWithDate);
							}
							if (result.getAnyDate() != null && result.getAnyPts() != null
									&& !result.getAnyPts().trim().isEmpty()
									&& (result.getAnyGcInitialsWithDate() == null
											|| result.getAnyGcInitialsWithDate().trim().isEmpty())) {
								result.setAnyGcInitialsWithDate(gcInitialsWithDate);
							}
							if (result.getIhavDate() != null && result.getiHaveExp() != null
									&& !result.getiHaveExp().trim().isEmpty()
									&& (result.getIhavGcInitialsWithDate() == null
											|| result.getIhavGcInitialsWithDate().trim().isEmpty())) {
								result.setIhavGcInitialsWithDate(gcInitialsWithDate);
							}

						}
					}

					if (edInterviewSheet.getAnyDate() != null) {
						result.setAnyDate(edInterviewSheet.getAnyDate());
					}

					if (edInterviewSheet.getAnyPts() != null) {
						result.setAnyPts(edInterviewSheet.getAnyPts());
					}
					if (edInterviewSheet.getAppdate() != null) {
						result.setAppdate(edInterviewSheet.getAppdate());
					}
					if (edInterviewSheet.getAppearence() != null) {
						result.setAppearence(edInterviewSheet.getAppearence());

					}

					if (edInterviewSheet.getFamDate() != null) {
						result.setFamDate(edInterviewSheet.getFamDate());

					}

					if (edInterviewSheet.getFamilyback() != null) {
						result.setFamilyback(edInterviewSheet.getFamilyback());

					}
					if (edInterviewSheet.getIhavDate() != null) {
						result.setIhavDate(edInterviewSheet.getIhavDate());

					}
					if (edInterviewSheet.getiHaveExp() != null) {
						result.setiHaveExp(edInterviewSheet.getiHaveExp());

					}

					if (edInterviewSheet.getIniDate() != null) {
						result.setIniDate(edInterviewSheet.getIniDate());

					}

					if (edInterviewSheet.getInitialAss() != null) {
						result.setInitialAss(edInterviewSheet.getInitialAss());

					}
					if (edInterviewSheet.getMisc() != null) {
						result.setMisc(edInterviewSheet.getMisc());

					}
					if (edInterviewSheet.getMisDate() != null) {
						result.setMisDate(edInterviewSheet.getMisDate());

					}

					if (edInterviewSheet.getWorkDate() != null) {
						result.setWorkDate(edInterviewSheet.getWorkDate());

					}
					if (edInterviewSheet.getWorkExp() != null) {
						result.setWorkExp(edInterviewSheet.getWorkExp());

					}

					result = repo.save(result);
				}
			}
		}
		return result;

	}
}
