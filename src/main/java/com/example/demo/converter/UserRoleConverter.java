package com.example.demo.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.entity.BaseEntity;
import com.example.demo.model.BaseModel;
import com.example.demo.model.UserRoleEntity;
import com.example.demo.model.UserRoleModel;

@Component
public class UserRoleConverter implements BaseConverter {

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Override
	public UserRoleModel convertEntityToModel(BaseEntity baseEntity) {
		UserRoleEntity userRoleEntity = (UserRoleEntity) baseEntity;
		UserRoleModel userRoleModel = new UserRoleModel();
		userRoleModel.setId(userRoleEntity.getId());
		userRoleModel.setDescription(userRoleEntity.getDescription());
		userRoleModel.setRoleId(userRoleEntity.getRoleId());
		userRoleModel.setRoleName(userRoleEntity.getRoleName());
		userRoleModel.setShow(userRoleEntity.isShow());
		userRoleModel.setStatus(userRoleEntity.getStatus());
		// userRoleModel.setUserRoleModuleMapping(userRoleEntity.getUserRoleModuleMappingEntity());
		return userRoleModel;
	}

	@Override
	public UserRoleEntity convertModelToEntity(BaseModel baseModel) {
		UserRoleModel userRoleModel = (UserRoleModel) baseModel;
		UserRoleEntity userRoleEntity = new UserRoleEntity();
		userRoleEntity.setId(userRoleModel.getId());
		userRoleEntity.setDescription(userRoleModel.getDescription());
		userRoleEntity.setRoleId(userRoleModel.getRoleId());
		userRoleEntity.setRoleName(userRoleModel.getRoleName());
		userRoleEntity.setShow(userRoleModel.isShow());
		userRoleEntity.setStatus(userRoleModel.getStatus());
		return userRoleEntity;
	}

}
