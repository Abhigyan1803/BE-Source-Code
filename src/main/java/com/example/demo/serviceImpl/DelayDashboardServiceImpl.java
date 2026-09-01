package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AuthTable;
import com.example.demo.model.DelayDashboard;
import com.example.demo.model.Officer;
import com.example.demo.model.RoleEntity;
import com.example.demo.model.RoleModuleMappingEntity;
import com.example.demo.payload.DelayDashboardPayLoad;
import com.example.demo.payload.RolePayload;
import com.example.demo.repository.AdminBattalionRepo;
import com.example.demo.repository.AdminRecordOfServiceRepo;
import com.example.demo.repository.BattalionCompanyRepo;
import com.example.demo.repository.DelayDashboardRepository;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.RoleEntityRepo;
import com.example.demo.repository.RoleModuleMappingEntityRepo;
import com.example.demo.service.DelayDashboardService;

@Service
public class DelayDashboardServiceImpl implements DelayDashboardService {

	@Autowired
	private DelayDashboardRepository delayDashboardRepository;

	@Autowired
	LoginRepository loginRepo;

	@Autowired
	private RoleModuleMappingEntityRepo roleModuleMappingEntityRepo;

	@Autowired
	AdminRecordOfServiceRepo recordOfServiceRepo;

	@Autowired
	RoleEntityRepo roleEntityRepo;

	@Autowired
	AdminBattalionRepo adminBattalionRepo;

	@Autowired
	BattalionCompanyRepo companyRepo;

	@Override
	public DelayDashboard addDelayDashboard(DelayDashboard delayDashboard) {
		return delayDashboardRepository.save(delayDashboard);
	}

	@Override
	public DelayDashboard updateDelayDashboard(DelayDashboard delayDashboard) {
		// TODO Auto-generated method stub
		if (delayDashboard.getId() != null && delayDashboard.getId() != 0) {
			Optional<DelayDashboard> result = delayDashboardRepository.findById(delayDashboard.getId());
			if (result.isPresent()) {
				DelayDashboard delayDash = result.get();
				if (delayDashboard.getFinalSubmissionDate() != null) {
					delayDash.setFinalSubmissionDate(delayDashboard.getFinalSubmissionDate());
					return delayDashboardRepository.save(delayDash);
				}
			}
		}
		return null;
	}

	@Override
	public List<DelayDashboardPayLoad> getDelayDashboardStaff(Long moduleId, Long termId) {
		if (moduleId != null && moduleId != 0 && termId != null && termId != 0) {
			List<AuthTable> list = loginRepo.getActiveStaffs();
			if (list.size() > 0) {
				// List<AuthTablePayLoad> authPayLoadList = new ArrayList<AuthTablePayLoad>();
				
				List<DelayDashboardPayLoad> delayDashBoardPayLoadList = new ArrayList<DelayDashboardPayLoad>();
				for (AuthTable auth : list) {
					/////
					String roleString =  auth.getHasRole();
					String[] roleArr = roleString.split(",");
					List<RoleModuleMappingEntity> roleModuleMappingList = new ArrayList<RoleModuleMappingEntity>(); 
					for(String roleId :roleArr) {
						RoleModuleMappingEntity roleModuleMapping = roleModuleMappingEntityRepo
								.findByRoleIdAndModuleId(Long.parseLong(roleId), moduleId);
						if(roleModuleMapping!=null) {
							roleModuleMappingList.add(roleModuleMapping);
							
						}
					}
					/////
					
					if (roleModuleMappingList.size()>0) {
						// AuthTablePayLoad authTablePayLoad = new AuthTablePayLoad();
						DelayDashboardPayLoad delayDashPayLoad = new DelayDashboardPayLoad();
						delayDashPayLoad.setLoginId(auth.getLoginId());
						delayDashPayLoad.setUserId(auth.getUserId());
						delayDashPayLoad.setRoleId(auth.getHasRole());
						delayDashPayLoad.setUsername(auth.getUsername());
						delayDashPayLoad.setModuleId(moduleId);
						delayDashPayLoad.setTermId(termId);

						if (auth.getUserId() != null && auth.getUserId() != 0) {
							Optional<Officer> officerResult = recordOfServiceRepo.findById(auth.getUserId());
							if (officerResult.isPresent()) {
								Officer officer = officerResult.get();
								delayDashPayLoad.setName(officer.getName());
								delayDashPayLoad.setServiceId(officer.getPersonalNumber());
							}
						}
					//	Optional<RoleEntity> roleEntityResult = roleEntityRepo
						//		.findById(Long.parseLong(auth.getHasRole().toString()));
					
						/////////
						
						List<RoleEntity> roleEntityResultList = new ArrayList<RoleEntity>();
						for(String roleId:roleArr) {
                     
							RoleEntity roleEntityResult = roleEntityRepo
									.findById(Long.parseLong(roleId)).get();
							roleEntityResultList.add(roleEntityResult);
						}
						/////////
						
						if (roleEntityResultList.size()>0) {
							List<String> roleNames = new ArrayList<String>();
							for(RoleEntity roleEntity: roleEntityResultList) {
								roleNames.add(roleEntity.getRoleName());
							}
							delayDashPayLoad.setRoleNames(roleNames);
							
						}

						// To check if DelayDashboard already exist
						DelayDashboard delayDashboard = delayDashboardRepository
								.findByUserIdAndModuleIdAndTermId(auth.getUserId(), moduleId, termId);
						if (delayDashboard != null) {
							delayDashPayLoad.setId(delayDashboard.getId());
							delayDashPayLoad.setFinalSubmissionDate(delayDashboard.getFinalSubmissionDate());
							delayDashPayLoad.setDateOfSubmission(delayDashboard.getDateOfSubmission());
							delayDashPayLoad.setStatus(delayDashboard.getStatus());
						} else {
							delayDashPayLoad.setId(null);
							delayDashPayLoad.setFinalSubmissionDate(null);
							delayDashPayLoad.setDateOfSubmission(null);
							delayDashPayLoad.setStatus(null);
						}
						delayDashBoardPayLoadList.add(delayDashPayLoad);
					}
				}
				if (delayDashBoardPayLoadList.size() > 0) {
					return delayDashBoardPayLoadList;
				}
			}

		}
		return null;
	}
}
