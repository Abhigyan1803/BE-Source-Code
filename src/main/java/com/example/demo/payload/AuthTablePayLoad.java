package com.example.demo.payload;

public class AuthTablePayLoad {
	private Long id;// delay_dashboard primary key
	private Long loginId; // auth_table primary key
	private Long userId; // officer primary key
	private Long compId;
	private Integer battalionId;
	//private Integer hasRole;
	
	//formultipleroles
	private String hasRole;
	private String name;
	private String username;
	private String roleName;
	private String companyName;
	private String battalionName;
	private Integer status;

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

	public Long getCompId() {
		return compId;
	}

	public void setCompId(Long compId) {
		this.compId = compId;
	}

	public Integer getBattalionId() {
		return battalionId;
	}

	public void setBattalionId(Integer battalionId) {
		this.battalionId = battalionId;
	}

//	public Integer getHasRole() {
//		return hasRole;
//	}
//
//	public void setHasRole(Integer hasRole) {
//		this.hasRole = hasRole;
//	}

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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBattalionName() {
		return battalionName;
	}

	public void setBattalionName(String battalionName) {
		this.battalionName = battalionName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHasRole() {
		return hasRole;
	}

	public void setHasRole(String hasRole) {
		this.hasRole = hasRole;
	}

}
