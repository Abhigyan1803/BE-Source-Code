package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModulesModel extends BaseModel {

	private String moduleName;

	private Integer status;

	List<SubModulesModel> subModules;

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<SubModulesModel> getSubModules() {
		return subModules;
	}

	public void setSubModules(List<SubModulesModel> subModules) {
		this.subModules = subModules;
	}

}
