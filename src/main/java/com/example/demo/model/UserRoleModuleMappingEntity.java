package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import com.example.demo.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_role_module_mapping")
@Where(clause = "is_deleted = false")
@DynamicUpdate
public class UserRoleModuleMappingEntity extends BaseEntity {

	private static final long serialVersionUID = 6529122372010964084L;

	@JsonIgnore
	private UserRoleEntity userRole;

	private ModulesEntity modules;
	
	private boolean isView = true;
	
	private boolean isCreate = true;
	
	private boolean isEdit = true;
	
	//private boolean isDeleted = false;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName="id", nullable = false)
	public UserRoleEntity getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoleEntity userRole) {
		this.userRole = userRole;
	}

	@ManyToOne
	@JoinColumn(name = "module_id", referencedColumnName = "id", nullable = false)
	public ModulesEntity getModules() {
		return modules;
	}

	public void setModules(ModulesEntity modules) {
		this.modules = modules;
	}

	@Column(name = "is_view", nullable = false)
	public boolean isView() {
		return isView;
	}

	public void setView(boolean isView) {
		this.isView = isView;
	}

	@Column(name = "is_create", nullable = false)
	public boolean isCreate() {
		return isCreate;
	}

	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}

	@Column(name = "is_edit", nullable = false)
	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	/*
	 * public boolean isDeleted() { return isDeleted; }
	 * 
	 * public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }
	 */

}
