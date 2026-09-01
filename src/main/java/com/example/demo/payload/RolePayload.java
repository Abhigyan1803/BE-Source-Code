package com.example.demo.payload;

import java.util.List;

public class RolePayload {
	private Long roleId;
	private String roleName;
	private Integer status;
	/////////////
	private String department;
	private String appointment;
	/////////////
	private List<ModulesEntityPayLoad> modulesPayLoadList;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<ModulesEntityPayLoad> getModulesPayLoadList() {
		return modulesPayLoadList;
	}

	public void setModulesPayLoadList(List<ModulesEntityPayLoad> modulesPayLoadList) {
		this.modulesPayLoadList = modulesPayLoadList;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAppointment() {
		return appointment;
 	}

	public void setAppointment(String appointment) {
		this.appointment = appointment;
	}

}
