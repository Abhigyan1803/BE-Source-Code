package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Battalion;
import com.example.demo.model.LeaveDetails;
import com.example.demo.repository.AdminBattalionRepo;
import com.example.demo.repository.LeaveDetailsRepository;
import com.example.demo.service.LeaveDetailsService;

@Service
public class LeaveDetailsServiceImpl implements LeaveDetailsService{

	@Autowired
	LeaveDetailsRepository leaveRepo;
	
	@Autowired
	AdminBattalionRepo battalionRepo;
	
	@Override
	public LeaveDetails addLeaveDetails(LeaveDetails leaveDetails) {
		// TODO Auto-generated method stub
		LeaveDetails result=leaveRepo.save(leaveDetails);
		return result;
	}
	
	@Override
	public List<LeaveDetails> getLeaveDetailsList() {
		// TODO Auto-generated method stub
		List<LeaveDetails> list=new ArrayList<>();
		List<LeaveDetails> result=leaveRepo.findAll();
		for(LeaveDetails leaveDetails:result) {
			Battalion battalion=battalionRepo.findById(leaveDetails.getBattalionId()).get();
			leaveDetails.setBattalionName(battalion.getShortName());
			list.add(leaveDetails);
		}
		return list;
	}
	
	@Override
	public LeaveDetails getLeaveDetailsById(Long id) {
		// TODO Auto-generated method stub
		Optional<LeaveDetails> result=leaveRepo.findById(id);
		if(result.isPresent()) {
			LeaveDetails leaveDetails=result.get();
			Battalion battalion=battalionRepo.findById(leaveDetails.getBattalionId()).get();
			leaveDetails.setBattalionName(battalion.getShortName());
			return leaveDetails;
		}
		return null;
	}
	
	@Override
	public LeaveDetails updateLeaveDetails(LeaveDetails leaveDetails) {
		// TODO Auto-generated method stub
		LeaveDetails result = null;
		if (leaveDetails != null && leaveDetails.getId() != null && leaveDetails.getId() != 0) {

			Optional<LeaveDetails> at = leaveRepo.findById(leaveDetails.getId());
			if (at.isPresent()) {
				leaveDetails.setUpdatedAt(new Date());
				result = leaveRepo.save(leaveDetails);
			}
		}
		return result;
	}
	@Override
	public LeaveDetails leaveDetailsChangeStatus(Long id, Integer status) {
		// TODO Auto-generated method stub
		if(id!=null && id!=0) {
		LeaveDetails result=leaveRepo.findById(id).get();
		if(result!=null) {
			result.setStatus(status);
			leaveRepo.save(result);
			return result;
		}
		}
		return null;
	}
}
