package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.converter.ModulesEntityConverter;
import com.example.demo.converter.UserRoleModuleMappingCoverter;
import com.example.demo.model.ModulesEntity;
import com.example.demo.model.ModulesModel;
import com.example.demo.model.UserRoleModuleMappingEntity;
import com.example.demo.model.UserRoleModuleMappingModel;
import com.example.demo.repository.UserRoleModuleMappingRepo;
import com.example.demo.service.UserRoleModuleMappingService;

@Service
public class UserRoleModuleMappingServiceImpl implements UserRoleModuleMappingService {

	@Autowired
	private UserRoleModuleMappingRepo userRoleModuleMappingRepo;

	@Autowired
	private UserRoleModuleMappingCoverter userRoleModuleMappingCoverter;
	
	@Autowired
	private ModulesServiceImpl modulesServiceImpl;
	
	@Autowired
	private ModulesEntityConverter modulesEntityConverter;

	@Override
	public UserRoleModuleMappingEntity createUserRoleModuleMapping(UserRoleModuleMappingEntity userRoleModuleMapping) {
		return userRoleModuleMappingRepo.save(userRoleModuleMapping);
	}

	@Override
	public List<UserRoleModuleMappingModel> findByuserRoleId(Long roleId) {
		List<UserRoleModuleMappingEntity> userRoleModuleMappingEntityList = this.userRoleModuleMappingRepo
				.findByuserRoleId(roleId);
		List<UserRoleModuleMappingModel> userRoleModuleMappingModelsList = new ArrayList<UserRoleModuleMappingModel>();
		
		List<ModulesModel> modelsList = new ArrayList<ModulesModel>();

		UserRoleModuleMappingModel userRoleModuleMappingModel = new UserRoleModuleMappingModel();
		for (UserRoleModuleMappingEntity userRoleModuleMappingEntity : userRoleModuleMappingEntityList) {
			userRoleModuleMappingModel = (UserRoleModuleMappingModel) this.userRoleModuleMappingCoverter
					.convertEntityToModel(userRoleModuleMappingEntity);
			ModulesEntity modulesEntity = this.modulesServiceImpl.getById(userRoleModuleMappingEntity.getModules().getId());
			ModulesModel modulesModel = (ModulesModel) this.modulesEntityConverter.convertEntityToModel(modulesEntity);
			modelsList.add(modulesModel);
			userRoleModuleMappingModel.setModules(modelsList);
		}
		
		userRoleModuleMappingModelsList.add(userRoleModuleMappingModel);
		return userRoleModuleMappingModelsList;
	}
}
