package com.example.demo.service;

import java.util.List;

import com.example.demo.model.BloodGroups;
import com.example.demo.model.Caste;
import com.example.demo.model.MaritalStatus;
import com.example.demo.model.MotherTongue;
import com.example.demo.model.Nationality;
import com.example.demo.model.Rank;
import com.example.demo.model.ReligiousDenomination;
import com.example.demo.model.SainikSchoolLocation;
import com.example.demo.model.SchoolNames;
import com.example.demo.model.States;

public interface MasterService {

	List<Nationality> getAllNationality();

	List<MotherTongue> getAllMotherTongue();

	List<ReligiousDenomination> getAllReligious();

	List<Caste> getCasteList();

	List<States> getStatesList();

	List<MaritalStatus> getMaritalList();

	List<BloodGroups> getBloodGroupsList();

	List<Rank> getRanks();

	List<SchoolNames> getSchoolNamesList();

	List<SainikSchoolLocation> getSainikSchoolLocations();

}
