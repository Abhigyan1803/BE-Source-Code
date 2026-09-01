package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.HallOfFameOfficer;
import com.example.demo.repository.HallOfFameOfficerRepo;
import com.example.demo.service.HallOfFameOfficerService;

@Service
public class HallOfFameOfficerServiceImpl implements HallOfFameOfficerService {

	@Autowired
	HallOfFameOfficerRepo fameRepo;

	@Override
	public HallOfFameOfficer addOfficerFame(HallOfFameOfficer hallOfFameOfficer) {
		return fameRepo.save(hallOfFameOfficer);
	}

	@Override
	public List<HallOfFameOfficer> getOfficerFameList(Integer status) {
		Integer[] deletedStatus = { 2 };
		if (status < 2) {
			List<HallOfFameOfficer> list = fameRepo.findByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
			return list;
		} else {
			List<HallOfFameOfficer> list = fameRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
			return list;
		}
	}

	@Override
	public HallOfFameOfficer getOfficerFameById(Integer id) {
		Optional<HallOfFameOfficer> list = fameRepo.findById(id);
		return list.get();
	}

	@Override
	public HallOfFameOfficer updateOfficerFame(HallOfFameOfficer hallOfFameOfficer) {
		HallOfFameOfficer fame = null;
		Optional<HallOfFameOfficer> f = fameRepo.findById(hallOfFameOfficer.getId());
		if (f.isPresent()) {

			fame = f.get();

			if (StringUtils.isNotBlank(hallOfFameOfficer.getOfficerImage())) {
				fame.setOfficerImage(hallOfFameOfficer.getOfficerImage());
			}

			if (hallOfFameOfficer.getOfficerRank() != null) {

				fame.setOfficerRank(hallOfFameOfficer.getOfficerRank());
			}

			if (hallOfFameOfficer.getOfficerName() != null) {

				fame.setOfficerName(hallOfFameOfficer.getOfficerName());
			}

			if (hallOfFameOfficer.getOfficerBattalion() != null) {

				fame.setOfficerBattalion(hallOfFameOfficer.getOfficerBattalion());
			}

			if (hallOfFameOfficer.getOfficerRegiment() != null) {

				fame.setOfficerRegiment(hallOfFameOfficer.getOfficerRegiment());
			}

			if (hallOfFameOfficer.getAwardMedal() != null) {

				fame.setAwardMedal(hallOfFameOfficer.getAwardMedal());
			}

			if (hallOfFameOfficer.getYearAwarded() != null) {

				fame.setYearAwarded(hallOfFameOfficer.getYearAwarded());
			}

			if (hallOfFameOfficer.getDescription() != null) {

				fame.setDescription(hallOfFameOfficer.getDescription());
			}

			if (hallOfFameOfficer.getIsForeign() != null) {

				fame.setIsForeign(hallOfFameOfficer.getIsForeign());
			}

			if (hallOfFameOfficer.getCountry() != null) {

				fame.setCountry(hallOfFameOfficer.getCountry());
			}

			if (hallOfFameOfficer.getRecognition() != null) {

				fame.setRecognition(hallOfFameOfficer.getRecognition());
			}

			if (hallOfFameOfficer.getStatus() != null) {

				fame.setStatus(hallOfFameOfficer.getStatus());
			}

			fame.setUpdatedAt(new Date());

		}
		HallOfFameOfficer list = fameRepo.save(fame);
		return list;
	}

	@Override
	public List<HallOfFameOfficer> getAwardedIndianOfficerFameList(Integer fameCounrty, String awardName,
			Integer status) {
		List<HallOfFameOfficer> list = fameRepo.findByIsForeignAndAwardMedalAndStatusOrderByIdDesc(fameCounrty,
				awardName, status);
		return list;
	}

//	@Override
//	public List<HallOfFameOfficer> getAwardedIndianOfficerFameList(String awardName) {
//		List<HallOfFameOfficer> list = fameRepo.findByIsForeignAndAwardMedalAndStatusOrderByIdDesc(0, awardName, 1);
//		return list;
//	}
//
//	@Override
//	public List<HallOfFameOfficer> getAwardedForeignerOfficerFameList(String awardName) {
//		List<HallOfFameOfficer> list = fameRepo.findByIsForeignAndAwardMedalAndStatusOrderByIdDesc(1, awardName, 1);
//		return list;
//	}

}
