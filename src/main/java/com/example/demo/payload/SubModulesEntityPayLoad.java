package com.example.demo.payload;

public class SubModulesEntityPayLoad {
	private Long id;
	private String subModuleName;
	private Integer status;
	private Boolean isCreate = false;
	private Boolean isUpdate = false;
	private Boolean isView = false;
	private Boolean isDelete = false;
	private Boolean isSubModule = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Boolean getIsSubModule() {
		return isSubModule;
	}

	public void setIsSubModule(Boolean isSubModule) {
		this.isSubModule = isSubModule;
	}

}
