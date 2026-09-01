package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@DynamicUpdate
public class AuthTable implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = 6529122372010964084L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long loginId;

	private String name;

	@Column(unique = true)
	private String username;

	@Column(unique = true)
	private String email;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	// as admin will not have roles so adding records with this parameter,
	// create admin with hasRole=0 , and user records with hasRole=1
	//private Integer hasRole;
	
	//for assigning multiple roles
	private String hasRole;
	// added compId,userId 25 jan 2022
	private Long compId;
	private Long userId;
	private Integer status;

	public Integer getBattalionId() {
		return battalionId;
	}

	public void setBattalionId(Integer battalionId) {
		this.battalionId = battalionId;
	}

	private Integer battalionId;

	public long getLoginId() {
		return loginId;
	}

	public void setLoginId(long loginId) {
		this.loginId = loginId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Long getCompId() {
		return compId;
	}

	public void setCompId(Long compId) {
		this.compId = compId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getHasRole() {
		return hasRole;
	}

	public void setHasRole(String hasRole) {
		this.hasRole = hasRole;
	}

	

}
