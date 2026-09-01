package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "admin")
@DynamicUpdate
public class Admin implements Serializable {

	private static final long serialVersionUID = 6529122372010964084L;
//    @Id
//    @GenericGenerator(name = "sequence_admin_id", strategy = "com.lma.lmsApp.config.DepartmentIdGenerator")
//    @GeneratedValue(generator = "sequence_admin_id")
//    @Column(name="adminId")
//    private String adminId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long adminId;

	private String name;

	@Column(unique = true)
	private String email;

	@Column(unique = true)
	private String username;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@Column(unique = true)
	private String serviceId;

	private Integer status;
	// active user isDeleted=0 ,deleted or deactivated user=1
	private Integer isDeleted;
	private Date createdAt;
	private Date updatedAt;
	private Date lastLogin;
	private Date lastPasswordChange;
	private Integer isFirstLogin;

	@Transient
	private Integer roleId;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Menu> menuList;

	public String getUsername() {
		return username;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
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

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getLastPasswordChange() {
		return lastPasswordChange;
	}

	public void setLastPasswordChange(Date lastPasswordChange) {
		this.lastPasswordChange = lastPasswordChange;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", name=" + name + ", email=" + email + ", username=" + username
				+ ", password=" + password + ", status=" + status + ", isDeleted=" + isDeleted + ", roleId=" + roleId
				+ "]";
	}

	public Integer getIsFirstLogin() {
		return isFirstLogin;
	}

	public void setIsFirstLogin(Integer isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

}
