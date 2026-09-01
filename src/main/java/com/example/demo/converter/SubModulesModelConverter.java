package com.example.demo.converter;

import org.springframework.stereotype.Component;

import com.example.demo.entity.BaseEntity;
import com.example.demo.model.BaseModel;
import com.example.demo.model.SubModulesEntity;
import com.example.demo.model.SubModulesModel;

@Component
public class SubModulesModelConverter implements BaseConverter {

	@Override
	public BaseModel convertEntityToModel(BaseEntity baseEntity) {
		SubModulesEntity entity = (SubModulesEntity) baseEntity;
		SubModulesModel model = new SubModulesModel();
		model.setId(entity.getId());
		model.setStatus(entity.getStatus());
		model.setSubModuleName(entity.getSubModuleName());

		return model;
	}

	@Override
	public BaseEntity convertModelToEntity(BaseModel baseModel) {
		SubModulesModel model = (SubModulesModel) baseModel;
		SubModulesEntity entity = new SubModulesEntity();

		entity.setId(model.getId());
		entity.setStatus(model.getStatus());
		entity.setSubModuleName(model.getSubModuleName());
		return entity;
	}

}
