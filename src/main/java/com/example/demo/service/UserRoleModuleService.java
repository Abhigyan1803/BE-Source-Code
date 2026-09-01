package com.example.demo.service;

import java.util.List;

import com.example.demo.model.UserRoleEntity;
import com.example.demo.model.UserRoleModel;
import com.example.demo.payload.RolePayload;

public interface UserRoleModuleService {

	public UserRoleModel createRole(UserRoleModel userRoleModel);

	public List<UserRoleEntity> getRoles();

	public Integer updateRole(UserRoleModel roleModel);

	public void deleteRole(Long id);

	public UserRoleModel getRolesById(Long id);

	public UserRoleModel getRolesByRoleName(String rolename);

	public RolePayload addRole(RolePayload rolePayLoad);

	public RolePayload updateUserRole(RolePayload rolePayLoad);

	public List<RolePayload> getAllRoles();

	public RolePayload getRoleModuleMappingByRoleId(Long roleId);

}
