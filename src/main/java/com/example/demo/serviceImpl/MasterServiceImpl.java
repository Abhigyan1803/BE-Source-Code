package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.example.demo.repository.BloodGroupsRepo;
import com.example.demo.repository.CasteRepository;
import com.example.demo.repository.MaritalStatusRepo;
import com.example.demo.repository.MotherTongueRepository;
import com.example.demo.repository.NationalityRepository;
import com.example.demo.repository.RankRepository;
import com.example.demo.repository.ReligiousDenominationRepo;
import com.example.demo.repository.SainikSchoolLocationRepo;
import com.example.demo.repository.SchoolNamesRepo;
import com.example.demo.repository.StatesRepository;
import com.example.demo.service.MasterService;

@Service
public class MasterServiceImpl implements MasterService {

	@Autowired
	NationalityRepository nationalityRepo;

	@Autowired
	ReligiousDenominationRepo religiousRepo;

	@Autowired
	MotherTongueRepository motherTongueRepo;

	@Autowired
	StatesRepository statesRepo;

	@Autowired
	BloodGroupsRepo bloodGroupRepo;

	@Autowired
	CasteRepository casteRepo;

	@Autowired
	MaritalStatusRepo maritalStatusRepo;

	@Autowired
	RankRepository rankRepo;

	@Autowired
	SchoolNamesRepo schoolNamesRepo;

	@Autowired
	SainikSchoolLocationRepo sainikSchoolLocRepo;

	@Override
	public List<Nationality> getAllNationality() {
		List<Nationality> list = nationalityRepo.findAll();
		return list;
	}

	@Override
	public List<MotherTongue> getAllMotherTongue() {
		List<MotherTongue> list = motherTongueRepo.findAll();
		return list;
	}

	@Override
	public List<ReligiousDenomination> getAllReligious() {
		List<ReligiousDenomination> list = religiousRepo.findAll();
		return list;
	}

	@Override
	public List<Caste> getCasteList() {
		List<Caste> list = casteRepo.findAll();
		return list;
	}

	@Override
	public List<States> getStatesList() {
		List<States> list = statesRepo.findAll();
		return list;
	}

	@Override
	public List<MaritalStatus> getMaritalList() {
		List<MaritalStatus> list = maritalStatusRepo.findAll();
		return list;
	}

	@Override
	public List<BloodGroups> getBloodGroupsList() {
		List<BloodGroups> list = bloodGroupRepo.findAll();
		return list;
	}

	@Override
	public List<Rank> getRanks() {
		List<Rank> list = rankRepo.findAllByStatus(1);
		return list;
	}

	@Override
	public List<SchoolNames> getSchoolNamesList() {
		List<SchoolNames> list = schoolNamesRepo.findAll();
		return list;
	}

	@Override
	public List<SainikSchoolLocation> getSainikSchoolLocations() {
		List<SainikSchoolLocation> list = sainikSchoolLocRepo.findAll();
		return list;
	}

}
