package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRoleModel extends BaseModel {

	private String roleName;
	private String roleId;
	private String description;
	private Integer status;
	private boolean isShow = true;
	private List<UserRoleModuleMappingModel> userRoleModuleMapping;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public List<UserRoleModuleMappingModel> getUserRoleModuleMapping() {
		return userRoleModuleMapping;
	}

	public void setUserRoleModuleMapping(List<UserRoleModuleMappingModel> userRoleModuleMapping) {
		this.userRoleModuleMapping = userRoleModuleMapping;
	}

}
