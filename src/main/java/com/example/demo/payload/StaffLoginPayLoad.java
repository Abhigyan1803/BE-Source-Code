package com.example.demo.payload;

import java.util.List;

import com.example.demo.model.Battalion;
import com.example.demo.model.BattalionCompany;

public class StaffLoginPayLoad {
	private Long loginId;
	private Long userId;
	private String name;
	private String username;
	//private Integer hasRole;
	//formultipleroles
	private String hasRole;
	// private Integer battalionId;
	// private Long compId;
	private Battalion battalion;
	private BattalionCompany company;
	private List<RolePayload> moduleList;

	public Long getLoginId() {
		return loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

//	public Integer getHasRole() {
//		return hasRole;
//	}
//
//	public void setHasRole(Integer hasRole) {
//		this.hasRole = hasRole;
//	}

//	public Integer getBattalionId() {
//		return battalionId;
//	}
//
//	public void setBattalionId(Integer battalionId) {
//		this.battalionId = battalionId;
//	}
//
//	public Long getCompId() {
//		return compId;
//	}
//
//	public void setCompId(Long compId) {
//		this.compId = compId;
//	}

	public List<RolePayload> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<RolePayload> moduleList) {
		this.moduleList = moduleList;
	}

	public Battalion getBattalion() {
		return battalion;
	}

	public void setBattalion(Battalion battalion) {
		this.battalion = battalion;
	}

	public BattalionCompany getCompany() {
		return company;
	}

	public void setCompany(BattalionCompany company) {
		this.company = company;
	}

	public String getHasRole() {
		return hasRole;
	}

	public void setHasRole(String hasRole) {
		this.hasRole = hasRole;
	}

}
