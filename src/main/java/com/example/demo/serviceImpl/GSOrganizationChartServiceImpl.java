package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.GSOrganizationChart;
import com.example.demo.model.GSPosition;
import com.example.demo.myexception.MyException;
import com.example.demo.repository.GSOrganizationChartRepo;
import com.example.demo.repository.GSPositionRepo;
import com.example.demo.service.GSOrganizationChartService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;

@Service
public class GSOrganizationChartServiceImpl  implements GSOrganizationChartService{
	
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;
	
	@Autowired
	GSOrganizationChartRepo gsOrgRepo;
	
	@Autowired
	GSPositionRepo gsPositionRepo;
	
	@Override
	public GSOrganizationChart addOrganization(GSOrganizationChart records, MultipartFile image) throws MyException {
		
		// if gs member(OrganizationChart) of same position is added then status of
		// previous will be set 0
		GSOrganizationChart icExist= gsOrgRepo.findByICNum(records.getICNum());
		if(icExist == null)
		{
			GSOrganizationChart existing = gsOrgRepo.findByGsPositionIdAndStatus(records.getGsPosition().getId(), 1);
			if (existing != null) {
				existing.setStatus(0);
				existing.setUpdatedOn(new Date());
				gsOrgRepo.save(existing);
			}
	
			if (image != null && !image.isEmpty()) {
	
				String filename = FileUploader.uploadProfileImage(image, UploadDir);
				records.setImage(url + filename);
			}
			
			GSPosition position = gsPositionRepo.findById(records.getGsPosition().getId()).get();
			if (position != null) {
				records.setGsPosition(position);
			}
	
			records.setCreatedAt(new Date());
			records.setUpdatedOn(new Date());
			GSOrganizationChart saved = gsOrgRepo.save(records);	
			return saved;
		}
		else
		{
			throw new MyException(ConstantMessage.GS_ORGANIZATION_IC_EXIST);
		}
		
	}

	@Override
	public GSOrganizationChart updateOrganization(GSOrganizationChart records, MultipartFile image) {
		GSOrganizationChart existing = gsOrgRepo.findById(records.getId()).get();
		if (existing != null) {
				if (image != null && !image.isEmpty()) {
	
					String filename = FileUploader.uploadProfileImage(image, UploadDir);
					existing.setImage(url + filename);
				}
				existing.setStatus(records.getStatus());
				existing.setName(records.getName());
				existing.setGsRank(records.getGsRank());
				existing.setAward(records.getAward());
	
				GSPosition position = gsPositionRepo.findById(records.getGsPosition().getId()).get();
				if (position != null) {
					existing.setGsPosition(position);
				}
				existing.setUpdatedOn(new Date());
				GSOrganizationChart updated = gsOrgRepo.save(existing);
				return updated;
		}
		return null;
	}
//	@Override
//	public GSOrganizationPayload addOrganization(GSOrganizationPayload payload, MultipartFile image) throws MyException {
//		
//		// if gs member(GSOrganizationChart) of same position is added then status of
//		// previous will be set 0
//		GSOrganizationChart icExist= gsOrgRepo.findByICNum(payload.getGsOrganization().getICNum());
//		if(icExist == null)
//		{
//			GSOrganizationChart existing = gsOrgRepo.findByPositionIdAndStatus(payload.getGsOrganization().getPosition().getId(), 1);
//			if (existing != null) {
//				existing.setStatus(0);
//				existing.setUpdatedOn(new Date());
//				gsOrgRepo.save(existing);
//			}
//	
//			if (image != null && !image.isEmpty()) {
//	
//				String filename = FileUploader.uploadProfileImage(image, UploadDir);
//				payload.getGsOrganization().setImage(url + filename);
//			}
//			
//			GSPosition position = gsPositionRepo.findById(payload.getGsOrganization().getPosition().getId()).get();
//			if (position != null) {
//				payload.getGsOrganization().setPosition(position);
//			}
//	
//			payload.getGsOrganization().setCreatedAt(new Date());
//			payload.getGsOrganization().setUpdatedOn(new Date());
//			GSOrganizationChart savedOrg = gsOrgRepo.save(payload.getGsOrganization());	
//			
//			
//			// team members of existing position is assaigned under new trg
//						// member(OrganizationChart)
//						if (existing != null) {
//							List<GSOrganizationTeamMember> memberList = teamMemberRepo.findByGsOrganizationChartIdAndStatus(existing.getId(), 1);
//							if (memberList != null) {
//								for (GSOrganizationTeamMember team : memberList) {
//									team.setGsOrganizationChart(savedOrg);
//									team.setUpdatedAt(new Date());
//									teamMemberRepo.save(team);
//								}
//							}
//						}
//						// if new team member are added in organization
//
//						List<GSOrganizationTeamMember> newAdded = new ArrayList<>();
//						List<GSOrganizationTeamMember> list = payload.getTeamMember();
//						if (list != null && list.size() != 0) {
//
//							for (GSOrganizationTeamMember details : list) {
//								details.setCreatedAt(new Date());
//								details.setUpdatedAt(new Date());
//								details.setGsOrganizationChart(savedOrg);
//								newAdded.add(details);
//								teamMemberRepo.save(details);
//
//							}
//					}
//						GSOrganizationPayload response = new GSOrganizationPayload();
//						response.setGsOrganization(savedOrg);
//						response.setTeamMember(newAdded);
//						return response;
//		}
//		else
//		{
//			throw new MyException(ConstantMessage.GS_ORGANIZATION_IC_EXIST);
//		}
//		
//	}
//
//	@Override
//	public GSOrganizationPayload updateOrganization(GSOrganizationPayload payload, MultipartFile image) {
//		GSOrganizationChart existing = gsOrgRepo.findById(payload.getGsOrganization().getId()).get();
//		if (existing != null) {
//				if (image != null && !image.isEmpty()) {
//	
//					String filename = FileUploader.uploadProfileImage(image, UploadDir);
//					payload.getGsOrganization().setImage(url + filename);
//				}
//				payload.getGsOrganization().setCreatedAt(existing.getCreatedAt());
//	
//				GSPosition position = gsPositionRepo.findById(payload.getGsOrganization().getPosition().getId()).get();
//				if (position != null) {
//					payload.getGsOrganization().setPosition(position);
//				}
//				payload.getGsOrganization().setUpdatedOn(new Date());
//				GSOrganizationChart updatedOrg = gsOrgRepo.save(payload.getGsOrganization());
//			
//		     List<GSOrganizationTeamMember> newAdded = new ArrayList<>();
//			List<GSOrganizationTeamMember> memberList = payload.getTeamMember();
//			if (memberList != null) {
//				for (GSOrganizationTeamMember member : memberList) {
//					if (member.getId() != 0l && member.getId() != null) {
//						GSOrganizationTeamMember update = teamMemberRepo.findById(member.getId()).get();
//						if (update != null) {
//							
//							member.setGsOrganizationChart(updatedOrg);
//							member.setUpdatedAt(new Date());
//							member.setCreatedAt(update.getCreatedAt());
//
//							teamMemberRepo.save(member);
//							newAdded.add(member);
//						}
//					} else {
//						GSOrganizationTeamMember newMember = new GSOrganizationTeamMember();
//
//						newMember.setTeam_name(member.getTeam_name());
//						newMember.setTeam_award(member.getTeam_award());
//						newMember.setTeam_position(member.getTeam_position());
//						newMember.setTeam_rank(member.getTeam_rank());
//						newMember.setStatus(member.getStatus());
//						newMember.setGsOrganizationChart(updatedOrg);
//						newMember.setCreatedAt(new Date());
//						newMember.setUpdatedAt(new Date());
//
//						newAdded.add(newMember);
//						teamMemberRepo.save(newMember);
//					}
//				}
//
//			}
//			GSOrganizationPayload response = new GSOrganizationPayload();
//			response.setGsOrganization(updatedOrg);
//			response.setTeamMember(newAdded);
//			return response;
//
//		}
//		return null;
//	}

	@Override
	public GSOrganizationChart changeStatus(Long id, int status) {
		GSOrganizationChart existing = gsOrgRepo.findById(id).get();
		if(existing != null)
		{
			existing.setStatus(status);
			existing.setUpdatedOn(new Date());
			
			return gsOrgRepo.save(existing);
		}
		return null;
	}

	@Override
	public GSOrganizationChart viewById(Long id) {
		GSOrganizationChart record = gsOrgRepo.findById(id).get();
		return record;
	}

	@Override
	public List<GSOrganizationChart> getOrganizationList(int status) {
		List<GSOrganizationChart> list = new ArrayList<>();
		if(status == 0 || status == 1)
		{
			list = gsOrgRepo.findByStatusOrderByIdDesc(status);
		}
		else
		{
			list = gsOrgRepo.findAllByOrderByIdDesc();
		}
		return list;
	}

	@Override
	public List<GSPosition> getGsPositions() {
		List<GSPosition> list = gsPositionRepo.findAllByStatus(1);
		return list;
	}

}
