package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import com.example.demo.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sub_modules")
@Where(clause = "is_deleted = false")
@DynamicUpdate
public class SubModulesEntity extends BaseEntity {

	private static final long serialVersionUID = 6529122372010964084L;

	private String subModuleName;

	private Integer status;

	@JsonIgnore
	private ModulesEntity modules;

	public String getSubModuleName() {
		return subModuleName;
	}

	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;

	}

	@ManyToOne
	@JoinColumn(name = "module_id", referencedColumnName = "id", nullable = false)
	public ModulesEntity getModules() {
		return modules;
	}

	public void setModules(ModulesEntity modules) {
		this.modules = modules;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
