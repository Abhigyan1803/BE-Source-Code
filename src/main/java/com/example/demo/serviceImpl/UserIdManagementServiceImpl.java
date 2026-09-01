package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserIdManagement;
import com.example.demo.repository.UserIdManagementRepo;
import com.example.demo.service.UserIdManagementService;

@Service
public class UserIdManagementServiceImpl implements UserIdManagementService {

	@Autowired
	private UserIdManagementRepo userIdManagementRepo;

	@Override
	public UserIdManagement CreateUserId(UserIdManagement userIdManagement) {
		// TODO Auto-generated method stub
		userIdManagement.setUserId(userIdManagement.getUserId());
		return userIdManagementRepo.save(userIdManagement);
	}

	@Override
	public List<UserIdManagement> getUserId() {
		// TODO Auto-generated method stub
		List<UserIdManagement> userIdList = userIdManagementRepo.findAll();
		return userIdList;
	}

	@Override
	public List<UserIdManagement> getUserIdByRoleIdAndSubRoleIdAndAppId(Long roleId, Long subRoleId, Long appId) {
		// TODO Auto-generated method stub
		List<UserIdManagement> userIdList = userIdManagementRepo.findAllByRoleIdAndSubRoleIdAndAppId(roleId, subRoleId,
				appId);
		return userIdList;
	}

}
