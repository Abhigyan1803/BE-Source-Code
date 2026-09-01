package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role_module_mapping")
public class RoleModuleMappingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long roleId;
	private Long moduleId;
	private Boolean isCreate = false;
	private Boolean isUpdate = false;
	private Boolean isView = false;
	private Boolean isDelete = false;
	@OneToMany
	private List<RoleSubModuleMappingEntity> roleSubModuleMappingEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Boolean getIsCreate() {
		return isCreate;
	}

	public void setIsCreate(Boolean isCreate) {
		this.isCreate = isCreate;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public Boolean getIsView() {
		return isView;
	}

	public void setIsView(Boolean isView) {
		this.isView = isView;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public List<RoleSubModuleMappingEntity> getRoleSubModuleMappingEntity() {
		return roleSubModuleMappingEntity;
	}

	public void setRoleSubModuleMappingEntity(List<RoleSubModuleMappingEntity> roleSubModuleMappingEntity) {
		this.roleSubModuleMappingEntity = roleSubModuleMappingEntity;
	}

}
