package com.example.demo.converter;

import org.springframework.stereotype.Component;

import com.example.demo.entity.BaseEntity;
import com.example.demo.model.BaseModel;
import com.example.demo.model.ModulesEntity;
import com.example.demo.model.ModulesModel;

@Component
public class ModulesEntityConverter implements BaseConverter {

	@Override
	public BaseModel convertEntityToModel(BaseEntity baseEntity) {
		ModulesEntity entity = (ModulesEntity) baseEntity;
		ModulesModel model = new ModulesModel();
		model.setId(entity.getId());
		model.setModuleName(entity.getModuleName());
		model.setStatus(entity.getStatus());
		return model;
	}

	@Override
	public BaseEntity convertModelToEntity(BaseModel baseModel) {
		ModulesModel model = (ModulesModel) baseModel;
		ModulesEntity entity = new ModulesEntity();

		entity.setId(model.getId());
		entity.setModuleName(model.getModuleName());
		entity.setStatus(model.getStatus());

		return entity;
	}

}
