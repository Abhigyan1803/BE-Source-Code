package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AcdClubSops;
import com.example.demo.model.AcdCounsellor;
import com.example.demo.model.GCBoard;
import com.example.demo.model.GCBoard_Pcht_Ol_Achievements;
import com.example.demo.repository.AcdClubSopsRepo;
import com.example.demo.repository.AcdCounsellorRepo;
import com.example.demo.repository.GCBoardRepo;
import com.example.demo.repository.GCBoard_Pcht_Ol_AchievementsRepo;
import com.example.demo.service.GCBoardService;
import com.example.demo.util.FileUploader;

@Service
public class GCBoardServiceImpl implements GCBoardService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	GCBoardRepo gcBoardRepo;

	@Autowired
	private GCBoard_Pcht_Ol_AchievementsRepo gcBoard_Pcht_Ol_AchievementsRepo;

	@Autowired
	private AcdClubSopsRepo acdClubSopsRepo;

	@Autowired
	private AcdCounsellorRepo acdCounsellorRepo;

	@Override
	public GCBoard addGCBoard(GCBoard details, MultipartFile file) {
		if (file != null && !file.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(file, UploadDir);
			details.setDocument(url + filename);
		}
		details.setCreatedAt(new Date());
		details.setUpdatedAt(new Date());

		return gcBoardRepo.save(details);
	}

	@Override
	public GCBoard updateGCBoard(GCBoard details, MultipartFile docFile) {
		GCBoard updated = null;
		GCBoard records = gcBoardRepo.findById(details.getId()).get();
		if (records != null) {
			if (docFile != null && !docFile.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(docFile, UploadDir);
				records.setDocument(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setDescription(details.getDescription());
			records.setStatus(details.getStatus());
			// records.setEventEndTime(details.getEventEndTime());
			// records.setEventStartTime(details.getEventStartTime());
			records.setTitle(details.getTitle());
			updated = gcBoardRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public GCBoard viewById(Long id) {
		GCBoard details = gcBoardRepo.findById(id).get();
		return details;
	}

	@Override
	public List<GCBoard> getList(int status) {
		List<GCBoard> list = new ArrayList<>();
		Integer[] deletedStatus = { 2 };
		if (status == 1 || status == 0) {
			list = gcBoardRepo.findAllByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = gcBoardRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

	@Override
	public GCBoard changeStatus(Long id, int status) {
		GCBoard details = gcBoardRepo.findById(id).get();
		if (details != null) {
			details.setStatus(status);
			details.setUpdatedAt(new Date());

			details = gcBoardRepo.save(details);
			return details;
		}
		return null;
	}

	@Override
	public GCBoard_Pcht_Ol_Achievements addDetailsGCBoard_Pcht_Ol_Achievements(GCBoard_Pcht_Ol_Achievements details,
			MultipartFile file) {
		// TODO Auto-generated method stub
		if (file != null && !file.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(file, UploadDir);
			details.setDocument(url + filename);
		}
		details.setType(details.getType().trim());
		return gcBoard_Pcht_Ol_AchievementsRepo.save(details);
	}

	@Override
	public GCBoard_Pcht_Ol_Achievements viewGCBoard_Pcht_Ol_AchievementsById(Long id) {
		// TODO Auto-generated method stub
		Optional<GCBoard_Pcht_Ol_Achievements> details = gcBoard_Pcht_Ol_AchievementsRepo.findById(id);
		return details.get();
	}

	@Override
	public List<GCBoard_Pcht_Ol_Achievements> getGCBoard_Pcht_Ol_AchievementsList(String type, String subType,
			int status) {
		List<GCBoard_Pcht_Ol_Achievements> list = new ArrayList<>();
		if (status == 1) {
			if (type == null) {
				list = gcBoard_Pcht_Ol_AchievementsRepo.findAllByStatusOrderByIdDesc(status);
			} else {
				if (subType == null || subType == "") {
					list = gcBoard_Pcht_Ol_AchievementsRepo.findAllByTypeAndStatusOrderByIdDesc(type, status);
				} else {
					list = gcBoard_Pcht_Ol_AchievementsRepo.findAllByTypeAndSubTypeAndStatusOrderByIdDesc(type, subType,
							status);
				}

			}

		} else {
			if (subType == null || subType == "") {
				list = gcBoard_Pcht_Ol_AchievementsRepo.findAllByTypeOrderByIdDesc(type);
			} else {
				list = gcBoard_Pcht_Ol_AchievementsRepo.findAllByTypeAndSubTypeOrderByIdDesc(type, subType);
			}

		}
		return list;
	}

	@Override
	public GCBoard_Pcht_Ol_Achievements updateGCBoard_Pcht_Ol_AchievementsDetails(GCBoard_Pcht_Ol_Achievements details,
			MultipartFile file) {
		// TODO Auto-generated method stub
		GCBoard_Pcht_Ol_Achievements updated = null;
		GCBoard_Pcht_Ol_Achievements records = gcBoard_Pcht_Ol_AchievementsRepo.findById(details.getId()).get();
		if (records != null) {
			if (file != null && !file.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(file, UploadDir);
				records.setDocument(url + filename);
			}
			records.setUpdatedAt(new Date());
			records.setName(details.getName());
			records.setStatus(details.getStatus());
			updated = gcBoard_Pcht_Ol_AchievementsRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public GCBoard_Pcht_Ol_Achievements changeGCBoard_Pcht_Ol_AchievementsDetailsStatus(Long id, int status) {
		// TODO Auto-generated method stub
		GCBoard_Pcht_Ol_Achievements details = gcBoard_Pcht_Ol_AchievementsRepo.findById(id).get();
		if (details != null) {
			details.setStatus(status);
			details.setUpdatedAt(new Date());
			details = gcBoard_Pcht_Ol_AchievementsRepo.save(details);
			return details;
		}
		return null;
	}

	@Override
	public AcdClubSops addAcdClubSops(AcdClubSops acdClubSops, MultipartFile file) {
		if (file != null && !file.isEmpty()) {
			String filename = FileUploader.uploadProfileImage(file, UploadDir);
			acdClubSops.setDocument(url + filename);
		}
		acdClubSops.setType(acdClubSops.getType().trim());
		acdClubSops.setSubType((acdClubSops.getSubType().trim()));
		if (acdClubSops.getSubSubType() != null) {
			acdClubSops.setSubSubType((acdClubSops.getSubSubType().trim()));
		}
		return acdClubSopsRepo.save(acdClubSops);
	}

	@Override
	public AcdClubSops updateAcdClubSops(AcdClubSops acdClubSops, MultipartFile file) {
		AcdClubSops updated = null;
		AcdClubSops records = acdClubSopsRepo.findById(acdClubSops.getId()).get();
		if (records != null) {
			if (file != null && !file.isEmpty()) {
				String filename = FileUploader.uploadProfileImage(file, UploadDir);
				records.setDocument(url + filename);
			}
			records.setUpdatedAt(new Date());
			if (acdClubSops.getName() != null) {
				records.setName(acdClubSops.getName());
			}
			if (acdClubSops.getDate() != null) {
				records.setDate(acdClubSops.getDate());
			}

			updated = acdClubSopsRepo.save(records);
			return updated;
		}
		return updated;
	}

	@Override
	public AcdClubSops getAcdClubSopsById(Long id) {
		Optional<AcdClubSops> ccdClubSops = acdClubSopsRepo.findById(id);
		if (ccdClubSops.isPresent()) {
			return ccdClubSops.get();
		}
		return null;

	}

	@Override
	public List<AcdClubSops> getAcdClubSopsList(String type, String subType, String subSubType, Integer status) {
		List<AcdClubSops> list = null;
		if (status == 1) {
			if (type == null) {
				return acdClubSopsRepo.findAllByStatusOrderByIdDesc(status);
			} else {
				if (subType != null && subType.trim() != "") {
					if (subSubType != null && subSubType.trim() != "") {
						list = acdClubSopsRepo.findAllByTypeAndSubTypeAndSubSubTypeAndStatusOrderByIdDesc(type, subType,
								subSubType, status);
					} else {
						list = acdClubSopsRepo.findAllByTypeAndSubTypeAndStatusOrderByIdDesc(type, subType, status);
					}
				} else {
					list = acdClubSopsRepo.findAllByTypeAndStatusOrderByIdDesc(type, status);
				}

			}

		} else {
			if (type == null) {   //For delete status=3
				return acdClubSopsRepo.findAllByStatusNotInOrderByIdDesc(3);
			} else {
				if (subType != null && subType.trim() != "") {
					if (subSubType != null && subSubType.trim() != "") {
						list = acdClubSopsRepo.findAllByTypeAndSubTypeAndSubSubTypeOrderByIdDesc(type, subType,
								subSubType);
					} else {
						list = acdClubSopsRepo.findAllByTypeAndSubTypeOrderByIdDesc(type, subType);
					}
				} else {
					list = acdClubSopsRepo.findAllByTypeOrderByIdDesc(type);
				}

			}

		}
		return list;
	}

	@Override
	public AcdClubSops changeAcdClubSopsStatus(Long id, Integer status) {
		AcdClubSops acdClubSops = null;
		Optional<AcdClubSops> details = acdClubSopsRepo.findById(id);
		if (details.isPresent()) {
			acdClubSops = details.get();
			acdClubSops.setStatus(status);
			acdClubSops.setUpdatedAt(new Date());
			acdClubSops = acdClubSopsRepo.save(acdClubSops);
		}
		return acdClubSops;
	}

	@Override
	public AcdCounsellor addAcdCounsellor(AcdCounsellor acdCounsellor) {
		AcdCounsellor result = null;
		if (acdCounsellor != null && acdCounsellor.getId() == null) {
			acdCounsellor.setCreatedAt(new Date());
			result = acdCounsellorRepo.save(acdCounsellor);
		}
		return result;
	}

	@Override
	public AcdCounsellor updateAcdCounsellor(AcdCounsellor acdCounsellor) {
		AcdCounsellor result = null;
		if (acdCounsellor != null && acdCounsellor.getId() != null && acdCounsellor.getId() != 0) {
			Optional<AcdCounsellor> data = acdCounsellorRepo.findById(acdCounsellor.getId());
			if (data.isPresent()) {
				result = data.get();
				result.setUpdatedAt(new Date());
				if (acdCounsellor.getName() != null) {
					result.setName(acdCounsellor.getName());
				}
				if (acdCounsellor.getRankName() != null) {
					result.setRankName(acdCounsellor.getRankName());
				}
				if (acdCounsellor.getMobileNumber() != null) {
					result.setMobileNumber(acdCounsellor.getMobileNumber());
				}
				if (acdCounsellor.getStatus() != null) {
					result.setStatus(acdCounsellor.getStatus());
				}
				if (acdCounsellor.getStatus() != null) {
					result.setStatus(acdCounsellor.getStatus());
				}
				result = acdCounsellorRepo.save(result);
			}
		}
		return result;
	}

	@Override
	public AcdCounsellor getAcdCounsellorById(Long id) {
		Optional<AcdCounsellor> result = acdCounsellorRepo.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public List<AcdCounsellor> getAcdCounsellorList(Long battalionId, Long companyId, Integer status) {
		List<AcdCounsellor> result = null;
		if (status == 1) {
			if (battalionId != null && companyId != null) {
				result = acdCounsellorRepo.findAllByBattalionIdAndCompanyIdAndStatusOrderByIdDesc(battalionId,
						companyId, status);
			} else {
				result = acdCounsellorRepo.findAllByStatusOrderByIdDesc(status);
			}

		} else {
			if (battalionId != null && companyId != null) {
				result = acdCounsellorRepo.findAllByBattalionIdAndCompanyIdOrderByIdDesc(battalionId, companyId);
			} else {
				result = acdCounsellorRepo.findAllByOrderByIdDesc();
			}

		}
		return result;
	}

}
