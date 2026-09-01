package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.example.demo.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_role")
@Where(clause = "is_deleted = false and is_show = true")
@DynamicUpdate
@SQLDelete(sql = "UPDATE user_role SET is_deleted = true WHERE id = ?")
public class UserRoleEntity extends BaseEntity {

	private static final long serialVersionUID = -7097744751865962296L;

	@Column(unique = true)
	private String roleName;
	private String roleId;
	private String description;
	private Integer status;
	private boolean isShow = true;

	private List<UserRoleModuleMappingEntity> userRoleModuleMappingEntity;

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

	@Column(name = "is_show", nullable = false)
	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}


	@OneToMany(mappedBy = "userRole", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<UserRoleModuleMappingEntity> getUserRoleModuleMappingEntity() {
		return userRoleModuleMappingEntity;
	}
	
	public void setUserRoleModuleMappingEntity(List<UserRoleModuleMappingEntity> userRoleModuleMappingEntity) {
		this.userRoleModuleMappingEntity = userRoleModuleMappingEntity;
	}
}
