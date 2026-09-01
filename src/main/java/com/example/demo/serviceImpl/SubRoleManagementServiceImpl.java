package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SubRoleManagement;
import com.example.demo.repository.SubRoleManagementRepo;
import com.example.demo.service.SubRoleManagementService;

@Service
public class SubRoleManagementServiceImpl implements SubRoleManagementService {

	@Autowired
	private SubRoleManagementRepo subRoleManagementRepo;

	@Override
	public SubRoleManagement createSubRole(SubRoleManagement subroleManagement) {
		// TODO Auto-generated method stub
		subroleManagement.setSubRoleName(subroleManagement.getSubRoleName().trim());
		return subRoleManagementRepo.save(subroleManagement);
	}

	@Override
	public List<SubRoleManagement> getSubRoles() {
		// TODO Auto-generated method stub
		List<SubRoleManagement> subRoleResults = subRoleManagementRepo.findAll();
		return subRoleResults;
	}

	@Override
	public List<SubRoleManagement> getSubRolesByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		List<SubRoleManagement> subRoleResults = subRoleManagementRepo.findByRoleId(roleId);
		return subRoleResults;
	}

}
