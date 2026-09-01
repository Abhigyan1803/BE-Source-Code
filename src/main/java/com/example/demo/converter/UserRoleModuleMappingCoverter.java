package com.example.demo.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.entity.BaseEntity;
import com.example.demo.model.BaseModel;
import com.example.demo.model.UserRoleModuleMappingEntity;
import com.example.demo.model.UserRoleModuleMappingModel;

@Component
public class UserRoleModuleMappingCoverter implements BaseConverter {

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Override
	public BaseModel convertEntityToModel(BaseEntity baseEntity) {
		UserRoleModuleMappingEntity entity = (UserRoleModuleMappingEntity) baseEntity;
		UserRoleModuleMappingModel model = new UserRoleModuleMappingModel();
		model.setId(entity.getId());
		model.setEdit(entity.isEdit());
		model.setView(entity.isView());
		model.setCreate(entity.isCreate());

		return model;
	}

	@Override
	public BaseEntity convertModelToEntity(BaseModel baseModel) {
		UserRoleModuleMappingModel model = (UserRoleModuleMappingModel) baseModel;
		UserRoleModuleMappingEntity entity = new UserRoleModuleMappingEntity();
		entity.setId(model.getId());
		entity.setCreate(model.isCreate());
		entity.setEdit(model.isEdit());
		entity.setView(model.isView());
		
		return entity;
	}

}
