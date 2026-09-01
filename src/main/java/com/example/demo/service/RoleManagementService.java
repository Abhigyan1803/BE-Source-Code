package com.example.demo.service;

import java.util.List;

import com.example.demo.model.RoleManagement;

public interface RoleManagementService {

	RoleManagement createRole(RoleManagement roleManagement);

	//List<RoleManagement> getRoles();

	List<RoleManagement> getRolesByIsShow(int isShow);

}
