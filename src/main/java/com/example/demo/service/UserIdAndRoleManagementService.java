package com.example.demo.service;

import java.util.List;

import javax.servlet.ServletRequest;

import com.example.demo.model.UserIdAndRoleManagement;
import com.example.demo.myexception.MyException;

public interface UserIdAndRoleManagementService {

	UserIdAndRoleManagement CreateUserIdAndRoleManagement(UserIdAndRoleManagement userIdAndRoleManagement);

	List<UserIdAndRoleManagement> getUserIdAndRoleManagement();


	UserIdAndRoleManagement getUserIdAndRoleMngtByRoleIdAndSubRoleIdAndAppIdAndUserId(Long roleId, Long subRoleId,
			Long appId, Long userId);
	
	UserIdAndRoleManagement getUserIdAndRoleMngtByUserName(String userName, ServletRequest request) throws MyException;

}
