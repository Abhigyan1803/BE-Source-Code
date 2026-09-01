package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Officer;
import com.example.demo.payload.OfficerPayLoad;

public interface AdminRecordOfService {

	Officer createOfficer(Officer officer);

	Officer getOfficerById(Long id);

	List<Officer> getAllOfficerByStatus(Integer status);

	Officer ChangeOfficerStatus(Officer officer);

	Officer UpdateOfficerDetails(Officer officer);

	List<OfficerPayLoad> getActiveOfficers();

	Officer getOfficerByPersonalNumber(String personalNumber);

}
