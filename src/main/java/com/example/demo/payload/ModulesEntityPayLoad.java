package com.example.demo.payload;

import java.util.List;

public class ModulesEntityPayLoad {
	private Long id;
	private String moduleName;
	private Integer status;
	private Boolean isCreate = false;
	private Boolean isUpdate = false;
	private Boolean isView = false;
	private Boolean isDelete = false;
	private Boolean isModule = false;
	List<SubModulesEntityPayLoad> subModules;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Boolean getIsModule() {
		return isModule;
	}

	public void setIsModule(Boolean isModule) {
		this.isModule = isModule;
	}

	public List<SubModulesEntityPayLoad> getSubModules() {
		return subModules;
	}

	public void setSubModules(List<SubModulesEntityPayLoad> subModules) {
		this.subModules = subModules;
	}

}
