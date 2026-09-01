package com.example.demo.serviceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.OrganizationChart;
import com.example.demo.model.OrganizationTeamMember;
import com.example.demo.model.Position;
import com.example.demo.payload.OrganizationChartPayload;
import com.example.demo.payload.OrganizationMemberResponsePayload;
import com.example.demo.payload.OrganizationTeamMemberPayload;
import com.example.demo.repository.OrganizationChartRepo;
import com.example.demo.repository.OrganizationTeamMemberRepo;
import com.example.demo.repository.PositionRepo;
import com.example.demo.service.OrganizationChartService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;

@Service
public class OrganizationChartServiceImpl implements OrganizationChartService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	OrganizationChartRepo orgRepo;

	@Autowired
	PositionRepo positionRepo;

	@Autowired
	OrganizationTeamMemberRepo teamMemberRepo;

	@Override
	public Map<Object, Object> addOrgPosition(String name, String rank, Long reqPosition, MultipartFile img, int status,
			String award) {
		HashMap<Object, Object> map = new HashMap<>();

		try {
			OrganizationChart orgChart = new OrganizationChart();
			OrganizationChart existing = orgRepo.findByPositionIdAndStatus(reqPosition, 1);
			if (existing != null && existing.getPosition().getId() != 18 && existing.getPosition().getId() != 19) {
				existing.setStatus(0);
				orgRepo.save(existing);
			}
			if (img != null && !img.isEmpty()) {

				String filename = FileUploader.uploadProfileImage(img, UploadDir);
				orgChart.setImage(url + filename);
			}
			orgChart.setStatus(status);
			orgChart.setName(name);
			orgChart.setAward(award);

			Position position = positionRepo.findById(reqPosition).get();
			if (position != null) {
				orgChart.setPosition(position);
			}

			orgChart.setRank(rank);
			orgChart.setCreatedAt(new Date());
			orgChart.setUpdatedOn(new Date());
			orgChart = orgRepo.save(orgChart);
			if (orgChart != null) {
				map.put(ConstantMessage.LIST, orgChart);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_ADDED_SUCCESSFULLY);
				return map;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
			return map;
		}

		return map;
	}

	@Override
	public Map<Object, Object> getAllOrgPositions() {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			Integer[] deletedStatus = { 2 };
			List<OrganizationChart> orgList = orgRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
			if (orgList.size() != 0) {
				map.put(ConstantMessage.LIST, orgList);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;
	}

	@Override
	public Map<Object, Object> updateOrgPosition(Long id, String name, String rank, Long reqPosition, MultipartFile img,
			int status, String award) {
		long time_show = System.currentTimeMillis();
		HashMap<Object, Object> map = new HashMap<>();
		try {
			OrganizationChart orgChart = orgRepo.findById(id).get();
			if (orgChart != null) {
				if (img != null && !img.isEmpty()) {

					byte[] bytes = img.getBytes();
					Path path = Paths.get(UploadDir + time_show + img.getOriginalFilename().replaceAll("\\s+", "_"));
					Files.write(path, bytes);
					orgChart.setImage(url + time_show + img.getOriginalFilename());
				}

				orgChart.setStatus(status);
				orgChart.setName(name);
				orgChart.setRank(rank);
				orgChart.setAward(award);

				Position position = positionRepo.findById(reqPosition).get();
				if (position != null) {
					orgChart.setPosition(position);
				}

				orgChart.setUpdatedOn(new Date());

				orgChart = orgRepo.save(orgChart);
				if (orgChart != null) {
					map.put(ConstantMessage.LIST, orgChart);
					map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
					map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_UPDATED_SUCCESSFULLY);
					return map;
				}
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;
	}

	@Override
	public Map<Object, Object> viewDetailsById(Long id) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			OrganizationChart orgList = orgRepo.findById(id).get();
			List<OrganizationTeamMember> memberList = teamMemberRepo.findByOrganizationChartIdAndStatus(orgList.getId(),
					1);
			OrganizationMemberResponsePayload response = new OrganizationMemberResponsePayload();
			response.setOrganizationChart(orgList);
			response.setMemberList(memberList);

			if (response != null) {
				map.put(ConstantMessage.LIST, response);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;
	}

	@Override
	public Map<Object, Object> activeDeactiveStatus(Long id, int status, ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			OrganizationChart orgList = orgRepo.findById(id).get();
			if (orgList != null) {
				orgList.setStatus(status);
				orgList.setUpdatedOn(new Date());

				orgRepo.save(orgList);
				FileWritting.createLog((HttpServletRequest) request, orgList.getId() + ",updated,"
						+ "status update Organization," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
				map.put(ConstantMessage.LIST, orgList);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.STATUS_UPDATED_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;
	}

	@Override
	public Map<Object, Object> getAllActivePositions() {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<OrganizationChart> orgList = orgRepo.findAllByStatus(1);
			if (orgList.size() != 0) {
				map.put(ConstantMessage.LIST, orgList);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;
	}

	@Override
	public Map<Object, Object> addOrgPosition(MultipartFile image, OrganizationChartPayload payload,
			ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			OrganizationChart orgChart = new OrganizationChart();

			// if trg member(OrganizationChart) of same position is added then status of
			// previous will be set 0
			OrganizationChart existing = orgRepo.findByPositionIdAndStatus(payload.getPosition(), 1);
			if (existing != null && existing.getPosition().getId() != 18 && existing.getPosition().getId() != 19) {
				existing.setStatus(0);
				existing.setUpdatedOn(new Date());
				orgRepo.save(existing);
			}

			if (image != null && !image.isEmpty()) {

				String filename = FileUploader.uploadProfileImage(image, UploadDir);
				orgChart.setImage(url + filename);
			}
			orgChart.setStatus(payload.getStatus());
			orgChart.setName(payload.getName());
			orgChart.setAward(payload.getAward());

			Position position = positionRepo.findById(payload.getPosition()).get();
			if (position != null) {
				orgChart.setPosition(position);
			}

			orgChart.setRank(payload.getRank());
			orgChart.setCreatedAt(new Date());
			orgChart.setUpdatedOn(new Date());
			orgChart = orgRepo.save(orgChart);

			// team members of existing position is assaigned under new trg
			// member(OrganizationChart)
			if (existing != null) {
				List<OrganizationTeamMember> memberList = teamMemberRepo
						.findByOrganizationChartIdAndStatus(existing.getId(), 1);
				if (memberList != null) {
					for (OrganizationTeamMember team : memberList) {
						team.setOrganizationChart(orgChart);
						team.setUpdatedAt(new Date());
						teamMemberRepo.save(team);
					}
				}
			}
			// if new team member are added in organization

			List<OrganizationTeamMember> newAdded = new ArrayList<>();
			List<OrganizationTeamMemberPayload> list = payload.getTeamMembers();
			if (list != null && list.size() != 0) {

				for (OrganizationTeamMemberPayload details : list) {
					OrganizationTeamMember newMember = new OrganizationTeamMember();

					newMember.setTeam_name(details.getTeam_name());
					newMember.setTeam_award(details.getTeam_award());
					newMember.setTeam_position(details.getTeam_position());
					newMember.setTeam_rank(details.getTeam_rank());
					newMember.setStatus(details.getTeam_status());
					newMember.setOrganizationChart(orgChart);
					newMember.setCreatedAt(new Date());
					newMember.setUpdatedAt(new Date());

					newAdded.add(newMember);
					teamMemberRepo.save(newMember);

				}
				// newAdded = teamMemberRepo.saveAll(newAdded);
			}
			OrganizationMemberResponsePayload response = new OrganizationMemberResponsePayload();
			response.setOrganizationChart(orgChart);
			response.setMemberList(newAdded);
			if (newAdded != null & orgChart != null) {
				map.put(ConstantMessage.LIST, response);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_ADDED_SUCCESSFULLY);
				return map;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;

	}

	@Override
	public Map<Object, Object> updateOrgPosition(MultipartFile image, OrganizationChartPayload payload,
			ServletRequest request) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			OrganizationChart existing = orgRepo.findById(payload.getOrgId()).get();
			if (existing != null) {
				if (image != null && !image.isEmpty()) {

					String filename = FileUploader.uploadProfileImage(image, UploadDir);
					existing.setImage(url + filename);
				}
				existing.setStatus(payload.getStatus());
				existing.setName(payload.getName());
				existing.setRank(payload.getRank());
				existing.setAward(payload.getAward());

				Position position = positionRepo.findById(payload.getPosition()).get();
				if (position != null) {
					existing.setPosition(position);
				}
				existing.setUpdatedOn(new Date());
				OrganizationChart updated = orgRepo.save(existing);

				List<OrganizationTeamMember> newAdded = new ArrayList<>();
				List<OrganizationTeamMemberPayload> memberList = payload.getTeamMembers();
				if (memberList != null) {
					for (OrganizationTeamMemberPayload member : memberList) {
						if (member.getTeam_member_id() != 0l && member.getTeam_member_id() != null) {
							OrganizationTeamMember update = teamMemberRepo.findById(member.getTeam_member_id()).get();
							if (update != null) {
								update.setTeam_award(member.getTeam_award());
								update.setTeam_name(member.getTeam_award());
								update.setTeam_rank(member.getTeam_rank());
								update.setTeam_position(member.getTeam_position());
								update.setOrganizationChart(updated);
								update.setUpdatedAt(new Date());

								teamMemberRepo.save(update);
								newAdded.add(update);
							}
						} else {
							OrganizationTeamMember newMember = new OrganizationTeamMember();

							newMember.setTeam_name(member.getTeam_name());
							newMember.setTeam_award(member.getTeam_award());
							newMember.setTeam_position(member.getTeam_position());
							newMember.setTeam_rank(member.getTeam_rank());
							newMember.setStatus(member.getTeam_status());
							newMember.setOrganizationChart(updated);
							newMember.setCreatedAt(new Date());
							newMember.setUpdatedAt(new Date());

							newAdded.add(newMember);
							teamMemberRepo.save(newMember);
						}
					}

				}
				OrganizationMemberResponsePayload response = new OrganizationMemberResponsePayload();
				response.setOrganizationChart(updated);
				response.setMemberList(newAdded);
				if (newAdded != null & updated != null) {
					map.put(ConstantMessage.LIST, response);
					map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
					map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_UPDATED_SUCCESSFULLY);
					return map;
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;
	}

	@Override
	public Map<Object, Object> getAllTeamMembers() {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<OrganizationTeamMember> memberList = teamMemberRepo.findAll();
			if (memberList != null) {
				map.put(ConstantMessage.LIST, memberList);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;
	}

	@Override
	public Map<Object, Object> activeDeactiveMemberStatus(Long id, int status) {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			OrganizationTeamMember member = teamMemberRepo.findById(id).get();
			if (member != null) {
				member.setStatus(status);
				member.setUpdatedAt(new Date());
				teamMemberRepo.save(member);

				map.put(ConstantMessage.LIST, member);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.STATUS_UPDATED_SUCCESSFULLY);
				return map;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);

		}
		return map;
	}

}
