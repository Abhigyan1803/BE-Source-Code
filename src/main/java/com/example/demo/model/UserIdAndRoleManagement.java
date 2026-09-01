package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sun.istack.NotNull;

@Entity
@Table(name = "userIdAndRoleManagement")
public class UserIdAndRoleManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long UserRoleId;

	private String firstName;
	private String LastName;
	private String fullName;
	private Long roleId;
	private String roleName;
	private Long subRoleId;
	private String subRoleName;
	private Long appId;
	private String apptName;
	private Long userId;
	private String userName;
	@NotNull
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private Integer status;
	private Integer isDeleted;
	private Long  permission;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	public long getUserRoleId() {
		return UserRoleId;
	}

	public void setUserRoleId(long userRoleId) {
		UserRoleId = userRoleId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getSubRoleId() {
		return subRoleId;
	}

	public void setSubRoleId(Long subRoleId) {
		this.subRoleId = subRoleId;
	}

	public String getSubRoleName() {
		return subRoleName;
	}

	public void setSubRoleName(String subRoleName) {
		this.subRoleName = subRoleName;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getApptName() {
		return apptName;
	}

	public void setApptName(String apptName) {
		this.apptName = apptName;
	}

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getPermission() {
		return permission;
	}

	public void setPermission(Long permission) {
		this.permission = permission;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
