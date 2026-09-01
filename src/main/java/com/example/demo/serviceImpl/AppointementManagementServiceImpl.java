package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AppointementManagement;
import com.example.demo.repository.AppointementManagementRepo;
import com.example.demo.service.AppointementManagementService;

@Service
public class AppointementManagementServiceImpl implements AppointementManagementService {

	@Autowired
	private AppointementManagementRepo appointementManagementRepo;

	@Override
	public AppointementManagement CreateAppointement(AppointementManagement appointementManagement) {
		// TODO Auto-generated method stub
		appointementManagement.setAppName(appointementManagement.getAppName().trim());
		return appointementManagementRepo.save(appointementManagement);
	}

	@Override
	public List<AppointementManagement> getAppointement() {
		// TODO Auto-generated method stub
		List<AppointementManagement> appointement = appointementManagementRepo.findAll();
		return appointement;
	}

	@Override
	public List<AppointementManagement> getAppointementByRoleIdAndSubRoleId(Long roleId, Long subRoleId) {
		// TODO Auto-generated method stub
		List<AppointementManagement> appointement = appointementManagementRepo.findAllByRoleIdAndSubRoleId(roleId,subRoleId);
		return appointement;
	}

}
