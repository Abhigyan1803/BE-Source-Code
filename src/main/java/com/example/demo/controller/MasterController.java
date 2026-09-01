package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.example.demo.service.MasterService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@RequestMapping("/api/master")
@CrossOrigin
public class MasterController {

	@Autowired
	MasterService masterService;

	@GetMapping("/religious-list")
	public ResponseEntity<?> getReligiousList() {
		List<ReligiousDenomination> list = masterService.getAllReligious();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping("/nationality-list")
	public ResponseEntity<?> getNationalityList() {
		List<Nationality> list = masterService.getAllNationality();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping("/mother-tongue-list")
	public ResponseEntity<?> getMotherTongueList() {
		List<MotherTongue> list = masterService.getAllMotherTongue();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping("/state-list")
	public ResponseEntity<?> stateList() {
		List<States> list = masterService.getStatesList();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping("/caste-list")
	public ResponseEntity<?> casteList() {
		List<Caste> list = masterService.getCasteList();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping("/blood-group-list")
	public ResponseEntity<?> bloodGroupList() {
		List<BloodGroups> list = masterService.getBloodGroupsList();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping("/marital-list")
	public ResponseEntity<?> maritalList() {
		List<MaritalStatus> list = masterService.getMaritalList();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping("/rank-list")
	public ResponseEntity<?> rankList() {
		List<Rank> list = masterService.getRanks();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping("/school-list")
	public ResponseEntity<?> SchoolList() {
		List<SchoolNames> list = masterService.getSchoolNamesList();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping("/sainik-location-list")
	public ResponseEntity<?> SainikSchoolList() {
		List<SainikSchoolLocation> list = masterService.getSainikSchoolLocations();
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

}
