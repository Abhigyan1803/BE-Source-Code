package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.RoleManagement;
import com.example.demo.repository.RoleManagementRepo;
import com.example.demo.service.RoleManagementService;

@Service
public class RoleManagementServiceImpl implements RoleManagementService {

	@Autowired
	private RoleManagementRepo roleManagementRepo;

	@Override
	public RoleManagement createRole(RoleManagement roleManagement) {
		// TODO Auto-generated method stub
		// RoleManagement roleMgt = null;
		roleManagement.setRoleName(roleManagement.getRoleName().trim());
		return roleManagementRepo.save(roleManagement);
	}

//	@Override
//	public List<RoleManagement> getRoles() {
//		// TODO Auto-generated method stub
//		List<RoleManagement> roleResults = roleManagementRepo.findAll();
//		return roleResults;
//	}

	@Override
	public List<RoleManagement> getRolesByIsShow(int isShow) {
		// TODO Auto-generated method stub
		List<RoleManagement> roleResults = roleManagementRepo.findAllByIsShow(isShow);
		return roleResults;
	}

}
