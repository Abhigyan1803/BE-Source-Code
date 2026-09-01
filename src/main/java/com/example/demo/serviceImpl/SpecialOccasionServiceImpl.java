package com.example.demo.serviceImpl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SpecialOccasion;
import com.example.demo.repository.SpecialOccasionRepo;
import com.example.demo.service.SpecialOccasionService;

@Service
public class SpecialOccasionServiceImpl implements SpecialOccasionService {

	@Autowired
	SpecialOccasionRepo specialOccasionRepo;

	@Override
	public SpecialOccasion addOccasion(SpecialOccasion occasion) {
		return specialOccasionRepo.save(occasion);
	}

	@Override
	public List<SpecialOccasion> getAllOccasiomList(Integer status) {
		Integer[] deletedStatus = { 2 };
		if (status < 2) {
			List<SpecialOccasion> list = specialOccasionRepo.findByStatusAndStatusNotInOrderByIdDesc(status,
					deletedStatus);
			return list;
		} else {
			List<SpecialOccasion> list = specialOccasionRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
			return list;
		}
	}

	@Override
	public SpecialOccasion getOccasionById(Long id) {
		Optional<SpecialOccasion> list = specialOccasionRepo.findById(id);
		return list.get();
	}

	@Override
	public SpecialOccasion updateOccasion(SpecialOccasion occasion) {
		SpecialOccasion so = null;
		Optional<SpecialOccasion> s = specialOccasionRepo.findById(occasion.getId());
		if (s.isPresent()) {
			so = s.get();

			if (occasion.getOfficerRank() != null) {
				so.setOfficerRank(occasion.getOfficerRank());
			}
			if (occasion.getOfficerName() != null) {
				so.setOfficerName(occasion.getOfficerName());
			}
			if (occasion.getPostedBranch() != null) {
				so.setPostedBranch(occasion.getPostedBranch());
			}

			if (occasion.getOfficerDOB() != null) {
				so.setOfficerDOB(occasion.getOfficerDOB());
			}
			if (occasion.getSpouseName() != null) {
				so.setSpouseName(occasion.getSpouseName());
			}
			if (occasion.getRelation() != null) {
				so.setRelation(occasion.getRelation());
			}
			if (occasion.getSpouseDOB() != null) {
				so.setSpouseDOB(occasion.getSpouseDOB());
			}
			if (occasion.getMarriageAnniversary() != null) {
				so.setMarriageAnniversary(occasion.getMarriageAnniversary());
			}
			if (occasion.getStatus() != null) {
				so.setStatus(occasion.getStatus());
			}
			if (occasion.getIcNumber() != null) {
				SpecialOccasion icNumber=specialOccasionRepo.findByIcNumber(occasion.getIcNumber());
				if(icNumber!=null) {
					return null;
				}
				so.setIcNumber(occasion.getIcNumber());
			}
			so.setUpdatedAt(new Date());
		}
		SpecialOccasion list = specialOccasionRepo.save(so);
		return list;
	}

	/*
	 * @Override public List<SpecialOccasion> getWeekOccasion() { LocalDate
	 * startDate = LocalDate.now(); LocalDate endDate = LocalDate.now().plusDays(7);
	 * Integer month1 = startDate.getMonthValue(); Integer month2 =
	 * endDate.getMonthValue(); String month3 = null; String month4 = null; if
	 * (month1 < 10) { month3 = "0" + startDate.getMonthValue(); } if (month2 < 10)
	 * { month4 = "0" + endDate.getMonthValue(); } String date1 = month3 + "-" +
	 * startDate.getDayOfMonth(); String date2 = month4 + "-" +
	 * endDate.getDayOfMonth(); List<SpecialOccasion> list =
	 * specialOccasionRepo.getDataBetweenDate(date1, date2);
	 *
	 * return list; }
	 */

	@Override
	public List<SpecialOccasion> getWeekOccasion() {
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = LocalDate.now().plusDays(7);
		Integer month1 = startDate.getMonthValue();
		Integer month2 = endDate.getMonthValue();
		String month3 = null;
		String month4 = null;
		String Day1 = null;
		String Day2 = null;
		if (month1 < 10) {
			month3 = "0" + startDate.getMonthValue();

		} else {

			month3 = "" + startDate.getMonthValue();
		}
		if (month2 < 10) {
			month4 = "0" + endDate.getMonthValue();
		} else {
			month4 = "" + endDate.getMonthValue();
		}

		if (startDate.getDayOfMonth() < 10) {
			Day1 = "0" + startDate.getDayOfMonth();
		} else {

			Day1 = "" + startDate.getDayOfMonth();
		}
		if (endDate.getDayOfMonth() < 10) {
			Day2 = "0" + endDate.getDayOfMonth();
		} else {
			Day2 = "" + endDate.getDayOfMonth();
		}

		// String date1 = month3 + "-" + "0"+startDate.getDayOfMonth();
		// String date2 = month4 + "-" + "0"+ endDate.getDayOfMonth();

		String date1 = month3 + "-" + Day1;
		String date2 = month4 + "-" + Day2;

		// System.out.println("API PASS" + date1 + " " + date2);
		List<SpecialOccasion> list = specialOccasionRepo.getDataBetweenDate(date1, date2);

		return list;
	}
}
