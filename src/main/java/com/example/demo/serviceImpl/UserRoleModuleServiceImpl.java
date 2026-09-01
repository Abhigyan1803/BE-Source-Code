package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.converter.ModulesEntityConverter;
import com.example.demo.converter.UserRoleConverter;
import com.example.demo.converter.UserRoleModuleMappingCoverter;
import com.example.demo.model.ModulesEntity;
import com.example.demo.model.ModulesModel;
import com.example.demo.model.RoleEntity;
import com.example.demo.model.RoleModuleMappingEntity;
import com.example.demo.model.RoleSubModuleMappingEntity;
import com.example.demo.model.SubModulesEntity;
import com.example.demo.model.UserRoleEntity;
import com.example.demo.model.UserRoleModel;
import com.example.demo.model.UserRoleModuleMappingEntity;
import com.example.demo.model.UserRoleModuleMappingModel;
import com.example.demo.payload.ModulesEntityPayLoad;
import com.example.demo.payload.RolePayload;
import com.example.demo.payload.SubModulesEntityPayLoad;
import com.example.demo.repository.ModulesRepository;
import com.example.demo.repository.RoleEntityRepo;
import com.example.demo.repository.RoleModuleMappingEntityRepo;
import com.example.demo.repository.RoleSubModuleMappingEntityRepo;
import com.example.demo.repository.SubModulesRepository;
import com.example.demo.repository.UserRoleModuleRepo;
import com.example.demo.service.UserRoleModuleService;

@Service
public class UserRoleModuleServiceImpl implements UserRoleModuleService {

	@Autowired
	private UserRoleModuleRepo roleModuleRepo;

	@Autowired
	private UserRoleModuleMappingServiceImpl userRoleModuleMappingServiceImpl;

	@Autowired
	private UserRoleConverter userRoleConverter;

	@Autowired
	private ModulesEntityConverter modulesEntityConverter;

	@Autowired
	private UserRoleModuleMappingCoverter userRoleModuleMappingCoverter;

	@Autowired
	private RoleEntityRepo roleEntityRepo;

	@Autowired
	private RoleModuleMappingEntityRepo roleModuleMappingEntityRepo;

	@Autowired
	private RoleSubModuleMappingEntityRepo roleSubModuleMappingEntityRepo;

	@Autowired
	private ModulesRepository modulesRepo;

	@Autowired
	private SubModulesRepository subModulesRepo;

	@Override
	public UserRoleModel createRole(UserRoleModel userRoleModel) {

		UserRoleEntity userRoleEntity = userRoleConverter.convertModelToEntity(userRoleModel);

		UserRoleEntity userRole_ = roleModuleRepo.save(userRoleEntity);

		List<UserRoleModuleMappingEntity> userRoleModuleMappingEntities = new ArrayList<UserRoleModuleMappingEntity>();
		List<ModulesEntity> modulesEntitieList = new ArrayList<ModulesEntity>();

		for (UserRoleModuleMappingModel userRoleModuleMappingModel : userRoleModel.getUserRoleModuleMapping()) {
			UserRoleModuleMappingEntity userRoleModuleMappingEntity = (UserRoleModuleMappingEntity) userRoleModuleMappingCoverter
					.convertModelToEntity(userRoleModuleMappingModel);

			userRoleModuleMappingEntity.setId(userRole_.getId());
			userRoleModuleMappingEntity.setUserRole(userRoleEntity);

			for (ModulesModel modulesModel : userRoleModuleMappingModel.getModules()) {
				ModulesEntity modulesEntity = (ModulesEntity) modulesEntityConverter.convertModelToEntity(modulesModel);
				userRoleModuleMappingEntity.setModules(modulesEntity);
				modulesEntitieList.add(modulesEntity);
				userRoleModuleMappingServiceImpl.createUserRoleModuleMapping(userRoleModuleMappingEntity);
			}
			userRoleModuleMappingEntities.add(userRoleModuleMappingEntity);
		}

		return userRoleConverter.convertEntityToModel(userRoleEntity);
	}

	@Override
	public RolePayload addRole(RolePayload rolePayLoad) {

		if (rolePayLoad != null) {
			RoleEntity roleEntity = new RoleEntity();
			roleEntity.setRoleName(rolePayLoad.getRoleName());
			roleEntity.setStatus(rolePayLoad.getStatus());
			/////
			roleEntity.setDepartment(rolePayLoad.getDepartment());
			roleEntity.setAppointment(rolePayLoad.getAppointment());
			//////
			roleEntity = roleEntityRepo.save(roleEntity);
			List<ModulesEntityPayLoad> moduleList = rolePayLoad.getModulesPayLoadList();
			if (moduleList != null && moduleList.size() != 0) {
				for (ModulesEntityPayLoad modulePayload : moduleList) {
					if (modulePayload.getIsModule() == true && modulePayload.getIsView() == true) {
						RoleModuleMappingEntity roleModuleMapEntity = new RoleModuleMappingEntity();
						roleModuleMapEntity.setRoleId(roleEntity.getId());
						roleModuleMapEntity.setModuleId(modulePayload.getId());
						roleModuleMapEntity.setIsCreate(modulePayload.getIsCreate());
						roleModuleMapEntity.setIsUpdate(modulePayload.getIsUpdate());
						roleModuleMapEntity.setIsDelete(modulePayload.getIsDelete());
						roleModuleMapEntity.setIsView(modulePayload.getIsView());
						roleModuleMapEntity = roleModuleMappingEntityRepo.save(roleModuleMapEntity);
						List<SubModulesEntityPayLoad> subModuleList = modulePayload.getSubModules();
						if (roleModuleMapEntity != null && subModuleList != null && subModuleList.size() != 0) {
							for (SubModulesEntityPayLoad subModulePayload : subModuleList) {
								if (subModulePayload.getIsSubModule() == true && subModulePayload.getIsView() == true) {
									RoleSubModuleMappingEntity roleSubModuleMapEntity = new RoleSubModuleMappingEntity();
									roleSubModuleMapEntity.setRoleId(roleEntity.getId());
									roleSubModuleMapEntity.setModuleId(modulePayload.getId());
									roleSubModuleMapEntity.setSubModuleId(subModulePayload.getId());
									roleSubModuleMapEntity.setIsCreate(subModulePayload.getIsCreate());
									roleSubModuleMapEntity.setIsUpdate(subModulePayload.getIsUpdate());
									roleSubModuleMapEntity.setIsDelete(subModulePayload.getIsDelete());
									roleSubModuleMapEntity.setIsView(subModulePayload.getIsView());
									roleSubModuleMapEntity = roleSubModuleMappingEntityRepo
											.save(roleSubModuleMapEntity);
								}
							}
						}
					}
				}
			}
			return rolePayLoad;
		}
		return null;
	}

	@Override
	public List<UserRoleEntity> getRoles() {
		return roleModuleRepo.findAll();
	}

	@Override
	public Integer updateRole(UserRoleModel roleModel) {
		return roleModuleRepo.updateUserRole(roleModel.getRoleName(), roleModel.getRoleId(), roleModel.getDescription(),
				roleModel.getStatus(), roleModel.isShow(), roleModel.getId());
	}

	@Override
	public void deleteRole(Long id) {
		UserRoleEntity entity = new UserRoleEntity();
		entity.setId(id);
		roleModuleRepo.delete(entity);
	}

	@Override
	public UserRoleModel getRolesById(Long id) {

		UserRoleEntity userRoleEntity = roleModuleRepo.findById(id).get();
		UserRoleModel userRoleModel = userRoleConverter.convertEntityToModel(userRoleEntity);
		List<UserRoleModuleMappingModel> userRoleModuleMappingModel = userRoleModuleMappingServiceImpl
				.findByuserRoleId(userRoleModel.getId());
		userRoleModel.setUserRoleModuleMapping(userRoleModuleMappingModel);
		return userRoleModel;
	}

	@Override
	public UserRoleModel getRolesByRoleName(String rolename) {
		UserRoleEntity userRoleEntity = roleModuleRepo.findByRoleName(rolename);
		UserRoleModel userRoleModel = userRoleConverter.convertEntityToModel(userRoleEntity);
		return userRoleModel;
	}

	@Override
	public RolePayload updateUserRole(RolePayload rolePayLoad) {
		//
		if (rolePayLoad != null && rolePayLoad.getRoleId() != null && rolePayLoad.getRoleId() != 0) {
			// remove old mapping
			System.out.println("roleId-->" + rolePayLoad.getRoleId());
			roleModuleMappingEntityRepo.deleteAllRoleModuleMapping(rolePayLoad.getRoleId());
			roleSubModuleMappingEntityRepo.deleteAllRoleSubModuleMapping(rolePayLoad.getRoleId());

			// add new mapping
			RoleEntity roleEntity = new RoleEntity();
			roleEntity.setId(rolePayLoad.getRoleId());
			roleEntity.setRoleName(rolePayLoad.getRoleName());
			roleEntity.setStatus(rolePayLoad.getStatus());
			/////
			roleEntity.setDepartment(rolePayLoad.getDepartment());
			roleEntity.setAppointment(rolePayLoad.getAppointment());
			//////
			roleEntity = roleEntityRepo.save(roleEntity);
			List<ModulesEntityPayLoad> moduleList = rolePayLoad.getModulesPayLoadList();
			if (moduleList != null && moduleList.size() != 0) {
				for (ModulesEntityPayLoad modulePayload : moduleList) {
					if (modulePayload.getIsModule() == true && modulePayload.getIsView() == true) {
						RoleModuleMappingEntity roleModuleMapEntity = new RoleModuleMappingEntity();
						roleModuleMapEntity.setRoleId(roleEntity.getId());
						roleModuleMapEntity.setModuleId(modulePayload.getId());
						roleModuleMapEntity.setIsCreate(modulePayload.getIsCreate());
						roleModuleMapEntity.setIsUpdate(modulePayload.getIsUpdate());
						roleModuleMapEntity.setIsDelete(modulePayload.getIsDelete());
						roleModuleMapEntity.setIsView(modulePayload.getIsView());
						roleModuleMapEntity = roleModuleMappingEntityRepo.save(roleModuleMapEntity);
						List<SubModulesEntityPayLoad> subModuleList = modulePayload.getSubModules();
						if (roleModuleMapEntity != null && subModuleList != null && subModuleList.size() != 0) {
							for (SubModulesEntityPayLoad subModulePayload : subModuleList) {
								if (subModulePayload.getIsSubModule() == true && subModulePayload.getIsView() == true) {
									RoleSubModuleMappingEntity roleSubModuleMapEntity = new RoleSubModuleMappingEntity();
									roleSubModuleMapEntity.setRoleId(roleEntity.getId());
									roleSubModuleMapEntity.setModuleId(modulePayload.getId());
									roleSubModuleMapEntity.setSubModuleId(subModulePayload.getId());
									roleSubModuleMapEntity.setIsCreate(subModulePayload.getIsCreate());
									roleSubModuleMapEntity.setIsUpdate(subModulePayload.getIsUpdate());
									roleSubModuleMapEntity.setIsDelete(subModulePayload.getIsDelete());
									roleSubModuleMapEntity.setIsView(subModulePayload.getIsView());
									roleSubModuleMapEntity = roleSubModuleMappingEntityRepo
											.save(roleSubModuleMapEntity);
								}
							}
						}
					}
				}
			}
			return rolePayLoad;
		}
		//

		return null;
	}

	@Override
	public List<RolePayload> getAllRoles() {
		List<RolePayload> roleList = new ArrayList<RolePayload>();
		List<RoleEntity> roleEntityList = roleEntityRepo.findAllByStatus(1);
		if (roleEntityList.size() > 0) {
			for (RoleEntity roleEntiry : roleEntityList) {
				RolePayload rolePayLoad = new RolePayload();
				rolePayLoad.setRoleId(roleEntiry.getId());
				rolePayLoad.setRoleName(roleEntiry.getRoleName());
				rolePayLoad.setStatus(roleEntiry.getStatus());
			/////
				rolePayLoad.setDepartment(roleEntiry.getDepartment());
				rolePayLoad.setAppointment(roleEntiry.getAppointment());
				//////
				List<RoleModuleMappingEntity> moduleList = roleModuleMappingEntityRepo.findByRoleId(roleEntiry.getId());
				List<ModulesEntityPayLoad> modulPaloadList = new ArrayList<ModulesEntityPayLoad>();
				if (moduleList.size() > 0) {
					for (RoleModuleMappingEntity roleModuleMappingEntity : moduleList) {
						ModulesEntityPayLoad modulePayLoad = new ModulesEntityPayLoad();
						modulePayLoad.setId(roleModuleMappingEntity.getModuleId());
						modulePayLoad.setIsCreate(roleModuleMappingEntity.getIsCreate());
						modulePayLoad.setIsDelete(roleModuleMappingEntity.getIsDelete());
						modulePayLoad.setIsUpdate(roleModuleMappingEntity.getIsUpdate());
						modulePayLoad.setIsView(roleModuleMappingEntity.getIsView());
						////
						ModulesEntity moduleEntity = modulesRepo.findById(roleModuleMappingEntity.getModuleId()).get();
						modulePayLoad.setModuleName(moduleEntity.getModuleName());
						/////
						List<RoleSubModuleMappingEntity> subModuleList = roleSubModuleMappingEntityRepo
								.findByRoleIdAndModuleId(roleEntiry.getId(), roleModuleMappingEntity.getModuleId());
						List<SubModulesEntityPayLoad> subModulPaloadList = new ArrayList<SubModulesEntityPayLoad>();
						if (subModuleList.size() > 0) {
							for (RoleSubModuleMappingEntity subModule : subModuleList) {
								SubModulesEntityPayLoad subModulePayLoad = new SubModulesEntityPayLoad();
								subModulePayLoad.setId(subModule.getSubModuleId());
								subModulePayLoad.setIsCreate(subModule.getIsCreate());
								subModulePayLoad.setIsDelete(subModule.getIsDelete());
								subModulePayLoad.setIsUpdate(subModule.getIsUpdate());
								subModulePayLoad.setIsView(subModule.getIsView());
								////
								SubModulesEntity subModuleEntity = subModulesRepo.findById(subModule.getSubModuleId())
										.get();
								subModulePayLoad.setSubModuleName(subModuleEntity.getSubModuleName());
								/////
								subModulPaloadList.add(subModulePayLoad);
							}
						}

						roleModuleMappingEntity.setRoleSubModuleMappingEntity(subModuleList);
						modulPaloadList.add(modulePayLoad);
						modulePayLoad.setSubModules(subModulPaloadList);
					}
				}
				roleEntiry.setRoleModuleMappingEntity(moduleList);
				rolePayLoad.setModulesPayLoadList(modulPaloadList);
				roleList.add(rolePayLoad);

			}

			return roleList;
		}
		return null;
	}

	@Override
	public RolePayload getRoleModuleMappingByRoleId(Long roleId) {
		if (roleId == null || roleId == 0) {
			return null;
		}
		RoleEntity roleEntity = null;
		Optional<RoleEntity> result = roleEntityRepo.findById(roleId);
		if (result.isPresent()) {
			roleEntity = result.get();
		}

		if (roleEntity != null) {
			RolePayload rolePayLoad = new RolePayload();
			rolePayLoad.setRoleId(roleEntity.getId());
			rolePayLoad.setRoleName(roleEntity.getRoleName());
			rolePayLoad.setStatus(roleEntity.getStatus());
			/////
			rolePayLoad.setDepartment(roleEntity.getDepartment());
			rolePayLoad.setAppointment(roleEntity.getAppointment());
			//////
			List<RoleModuleMappingEntity> moduleList = roleModuleMappingEntityRepo.findByRoleId(roleEntity.getId());
			List<ModulesEntityPayLoad> modulPaloadList = new ArrayList<ModulesEntityPayLoad>();
			if (moduleList.size() > 0) {
				for (RoleModuleMappingEntity roleModuleMappingEntity : moduleList) {
					ModulesEntityPayLoad modulePayLoad = new ModulesEntityPayLoad();
					modulePayLoad.setId(roleModuleMappingEntity.getModuleId());
					modulePayLoad.setIsCreate(roleModuleMappingEntity.getIsCreate());
					modulePayLoad.setIsDelete(roleModuleMappingEntity.getIsDelete());
					modulePayLoad.setIsUpdate(roleModuleMappingEntity.getIsUpdate());
					modulePayLoad.setIsView(roleModuleMappingEntity.getIsView());
					////
					ModulesEntity moduleEntity = modulesRepo.findById(roleModuleMappingEntity.getModuleId()).get();
					modulePayLoad.setModuleName(moduleEntity.getModuleName());
					/////
					List<RoleSubModuleMappingEntity> subModuleList = roleSubModuleMappingEntityRepo
							.findByRoleIdAndModuleId(roleEntity.getId(), roleModuleMappingEntity.getModuleId());
					List<SubModulesEntityPayLoad> subModulPaloadList = new ArrayList<SubModulesEntityPayLoad>();
					if (subModuleList.size() > 0) {
						for (RoleSubModuleMappingEntity subModule : subModuleList) {
							SubModulesEntityPayLoad subModulePayLoad = new SubModulesEntityPayLoad();
							subModulePayLoad.setId(subModule.getSubModuleId());
							subModulePayLoad.setIsCreate(subModule.getIsCreate());
							subModulePayLoad.setIsDelete(subModule.getIsDelete());
							subModulePayLoad.setIsUpdate(subModule.getIsUpdate());
							subModulePayLoad.setIsView(subModule.getIsView());
							////
							SubModulesEntity subModuleEntity = subModulesRepo.findById(subModule.getSubModuleId())
									.get();
							subModulePayLoad.setSubModuleName(subModuleEntity.getSubModuleName());
							/////
							subModulPaloadList.add(subModulePayLoad);
						}
					}

					roleModuleMappingEntity.setRoleSubModuleMappingEntity(subModuleList);
					modulPaloadList.add(modulePayLoad);
					modulePayLoad.setSubModules(subModulPaloadList);
				}
			}
			roleEntity.setRoleModuleMappingEntity(moduleList);
			rolePayLoad.setModulesPayLoadList(modulPaloadList);
			return rolePayLoad;
		} else {
			return null;
		}

	}
}
