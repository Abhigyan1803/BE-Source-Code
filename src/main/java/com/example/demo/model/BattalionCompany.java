package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="battalion_company")
public class BattalionCompany {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name="";
	
	private int status=0;
	
	private long roleId;
	private Long subRoleId;
	private long appId;

	
	@OneToOne
	private Battalion battalionType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Battalion getBattalionType() {
		return battalionType;
	}

	public void setBattalionType(Battalion battalionType) {
		this.battalionType = battalionType;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public Long getSubRoleId() {
		return subRoleId;
	}

	public void setSubRoleId(Long subRoleId) {
		this.subRoleId = subRoleId;
	}

	public long getAppId() {
		return appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}
	
	
	
	
	

}
