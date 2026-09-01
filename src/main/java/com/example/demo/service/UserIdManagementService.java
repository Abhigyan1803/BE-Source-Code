package com.example.demo.service;

import java.util.List;

import com.example.demo.model.UserIdManagement;

public interface UserIdManagementService {

	UserIdManagement CreateUserId(UserIdManagement userIdManagement);

	List<UserIdManagement> getUserId();

	List<UserIdManagement> getUserIdByRoleIdAndSubRoleIdAndAppId(Long roleId, Long subRoleId, Long appId);


}
