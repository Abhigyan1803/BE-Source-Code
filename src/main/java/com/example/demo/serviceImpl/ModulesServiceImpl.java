package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.converter.ModulesEntityConverter;
import com.example.demo.converter.SubModulesModelConverter;
import com.example.demo.model.ModulesEntity;
import com.example.demo.model.ModulesModel;
import com.example.demo.model.SubModulesEntity;
import com.example.demo.model.SubModulesModel;
import com.example.demo.payload.ModulesEntityPayLoad;
import com.example.demo.payload.SubModulesEntityPayLoad;
import com.example.demo.repository.ModulesRepository;
import com.example.demo.repository.SubModulesRepository;
import com.example.demo.service.ModulesService;

@Service
public class ModulesServiceImpl implements ModulesService {
	@Autowired
	private ModulesRepository repo;

	@Autowired
	private SubModulesRepository subModulesRepository;

	@Autowired
	private ModulesEntityConverter modulesConverter;

	@Autowired
	private SubModulesModelConverter subModulesModelConverter;

	@Override
	public ModulesModel addModules(ModulesModel module) {
		// module.setCreatedDate(new Date());
		ModulesEntity modulesEntity = (ModulesEntity) modulesConverter.convertModelToEntity(module);
		ModulesEntity entity = repo.save(modulesEntity);
		List<SubModulesModel> subModulesList = new ArrayList<SubModulesModel>();
		ModulesModel modulesModel = (ModulesModel) modulesConverter.convertEntityToModel(entity);

		for (SubModulesModel subModulesModel : module.getSubModules()) {
			SubModulesEntity subModulesEntity = (SubModulesEntity) subModulesModelConverter
					.convertModelToEntity(subModulesModel);

			subModulesEntity.setModules(entity);

			SubModulesEntity subModulesEntity2 = subModulesRepository.save(subModulesEntity);
			SubModulesModel subModulesModel2 = (SubModulesModel) subModulesModelConverter
					.convertEntityToModel(subModulesEntity2);
			subModulesList.add(subModulesModel2);
		}
		modulesModel.setSubModules(subModulesList);
		return modulesModel;
	}

	@Override
	public ModulesEntity getById(Long id) {
		Optional<ModulesEntity> result = repo.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public List<ModulesEntityPayLoad> getModuleList() {
		List<ModulesEntity> result = null;
		List<ModulesEntityPayLoad> modulePayloadList = null;
		result = repo.findAll();
		if (result != null) {
			modulePayloadList = new ArrayList<ModulesEntityPayLoad>();
			for (ModulesEntity moduleEntity : result) {
				ModulesEntityPayLoad modulePayload = new ModulesEntityPayLoad();
				modulePayload.setId(moduleEntity.getId());
				modulePayload.setModuleName(moduleEntity.getModuleName());
				modulePayload.setStatus(moduleEntity.getStatus());
				modulePayload.setIsCreate(false);
				modulePayload.setIsUpdate(false);
				modulePayload.setIsDelete(false);
				modulePayload.setIsView(false);
				modulePayload.setIsModule(false);
				List<SubModulesEntityPayLoad> subModulesEntityPayLoadList = new ArrayList<SubModulesEntityPayLoad>();
				for (SubModulesEntity subModuleEntity : moduleEntity.getSubModules()) {
					SubModulesEntityPayLoad subModulePayload = new SubModulesEntityPayLoad();
					subModulePayload.setId(subModuleEntity.getId());
					subModulePayload.setSubModuleName(subModuleEntity.getSubModuleName());
					subModulePayload.setStatus(subModuleEntity.getStatus());
					subModulePayload.setIsCreate(false);
					subModulePayload.setIsUpdate(false);
					subModulePayload.setIsDelete(false);
					subModulePayload.setIsView(false);
					subModulePayload.setIsSubModule(false);
					subModulesEntityPayLoadList.add(subModulePayload);
				}
				modulePayload.setSubModules(subModulesEntityPayLoadList);
				modulePayloadList.add(modulePayload);
			}
		}
		return modulePayloadList;
	}

	@Override
	public ModulesEntity updateModule(ModulesEntity modules) {
		ModulesEntity result = null;
		if (modules != null && modules.getId() != null && modules.getId() != 0) {

			Optional<ModulesEntity> at = repo.findById(modules.getId());
			if (at.isPresent()) {
				result = repo.save(modules);
			}
		}
		return result;

	}
}
