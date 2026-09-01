package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role_entity")
public class RoleEntity {

	private static final long serialVersionUID = -7097744751865962296L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String roleName;
	private Integer status;
/////////////
	private String department;
	private String appointment;
/////////////

	@OneToMany
	private List<RoleModuleMappingEntity> roleModuleMappingEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<RoleModuleMappingEntity> getRoleModuleMappingEntity() {
		return roleModuleMappingEntity;
	}

	public void setRoleModuleMappingEntity(List<RoleModuleMappingEntity> roleModuleMappingEntity) {
		this.roleModuleMappingEntity = roleModuleMappingEntity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
