package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRoleModuleMappingModel extends BaseModel {

	private UserRoleModel userRole;

	private List<ModulesModel> modules;

	private boolean isView = true;
	private boolean isCreate = true;
	private boolean isEdit = true;
	private boolean isDeleted = false;

	public UserRoleModel getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoleModel userRole) {
		this.userRole = userRole;
	}

	public List<ModulesModel> getModules() {
		return modules;
	}

	public void setModules(List<ModulesModel> modules) {
		this.modules = modules;
	}

	public boolean isView() {
		return isView;
	}

	public void setView(boolean isView) {
		this.isView = isView;
	}

	public boolean isCreate() {
		return isCreate;
	}

	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}

	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
