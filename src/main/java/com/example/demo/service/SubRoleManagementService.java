package com.example.demo.service;

import java.util.List;

import com.example.demo.model.SubRoleManagement;

public interface SubRoleManagementService {

	SubRoleManagement createSubRole(SubRoleManagement subroleManagement);

	List<SubRoleManagement> getSubRoles();

	List<SubRoleManagement> getSubRolesByRoleId(Long roleId);

}
