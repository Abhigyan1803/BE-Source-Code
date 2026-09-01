package com.example.demo.service;

import java.util.List;

import com.example.demo.model.UserRoleModuleMappingEntity;
import com.example.demo.model.UserRoleModuleMappingModel;

public interface UserRoleModuleMappingService {

	UserRoleModuleMappingEntity createUserRoleModuleMapping(UserRoleModuleMappingEntity userRoleModuleMapping);
	
	List<UserRoleModuleMappingModel> findByuserRoleId(Long id);
}