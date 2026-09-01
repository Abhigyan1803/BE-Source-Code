package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import com.example.demo.entity.BaseEntity;

@Entity
@Table(name = "modules")
@Where(clause = "is_deleted = false")
@DynamicUpdate
public class ModulesEntity extends BaseEntity {

	private static final long serialVersionUID = 6529122372010964084L;

	private String moduleName;

	private Integer status;

	// @OneToMany(cascade = { CascadeType.ALL })
	@Column(nullable = true)
	List<SubModulesEntity> subModules;

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@OneToMany(mappedBy = "modules", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	public List<SubModulesEntity> getSubModules() {
		return subModules;
	}

	public void setSubModules(List<SubModulesEntity> subModules) {
		this.subModules = subModules;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
