package com.example.demo.service;

import java.util.List;

import com.example.demo.model.LeaveDetails;

public interface LeaveDetailsService {

	LeaveDetails addLeaveDetails(LeaveDetails leaveDetails);

	List<LeaveDetails> getLeaveDetailsList();

	LeaveDetails getLeaveDetailsById(Long id);

	LeaveDetails updateLeaveDetails(LeaveDetails leaveDetails);

	LeaveDetails leaveDetailsChangeStatus(Long id, Integer status);

}
