package com.example.demo.service;

import java.util.List;

import com.example.demo.model.HallOfFameOfficer;

public interface HallOfFameOfficerService {

	HallOfFameOfficer addOfficerFame(HallOfFameOfficer hallOfFameOfficer);

	List<HallOfFameOfficer> getOfficerFameList(Integer status);

	HallOfFameOfficer getOfficerFameById(Integer id);

	HallOfFameOfficer updateOfficerFame(HallOfFameOfficer hallOfFameOfficer);

//	List<HallOfFameOfficer> getAwardedIndianOfficerFameList(String awardName);
//
//	List<HallOfFameOfficer> getAwardedForeignerOfficerFameList(String awardName);

	List<HallOfFameOfficer> getAwardedIndianOfficerFameList(Integer fameCounrty, String awardName, Integer status);

}
