package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Battalion;
import com.example.demo.model.BattalionCompany;
import com.example.demo.model.BattalionHistory;
import com.example.demo.model.BattalionOrganizationChart;
import com.example.demo.model.Cadet;
import com.example.demo.model.RunbackRouteMr;
import com.example.demo.model.TrgBattalionPost;
import com.example.demo.payload.RouteRunMrFilterPayload;
import com.example.demo.payload.RouteRunMrPayload;
import com.example.demo.repository.AdminBattalionRepo;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.BattalionCompanyRepo;
import com.example.demo.repository.BattalionHistoryRepo;
import com.example.demo.repository.BattalionOrganizationChartRepo;
import com.example.demo.repository.RunbackRouteMrRepo;
import com.example.demo.repository.TrgBattalionPostRepo;
import com.example.demo.service.AdminBattalionService;
import com.example.demo.util.ConstantVar;

@Service
public class AdminBattalionServiceImpl implements AdminBattalionService {

	@Autowired
	AdminBattalionRepo battalionRepo;

	@Autowired
	BattalionCompanyRepo btCompanyRepo;

	@Autowired
	BattalionOrganizationChartRepo btOrgRepo;

	@Autowired
	BattalionHistoryRepo btHistoryRepo;

	@Autowired
	TrgBattalionPostRepo btPostRepo;

	@Autowired
	RunbackRouteMrRepo runbackRouteMrRepo;
	@Autowired
	AdminCadetRepo cadetRepo;

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Override
	public Battalion createBattalion(Battalion battalion) {
		return battalionRepo.save(battalion);
	}

	@Override
	public List<Battalion> getAllBattalionList() {
		Integer[] deletedStatus = { 2 };
		List<Battalion> list = battalionRepo.findAllByStatusAndStatusNotIn(ConstantVar.ONE, deletedStatus);
		return list;
	}

	@Override
	public Battalion updateBattalion(Battalion battalion) {
		Battalion btln = null;
		Optional<Battalion> bt = battalionRepo.findById(battalion.getId());
		if (bt.isPresent()) {

			btln = bt.get();

			if (battalion.getName() != null) {

				btln.setName(battalion.getName());
			}

			if (battalion.getShortName() != null) {

				btln.setShortName(battalion.getShortName());
			}

			if (battalion.getStatus() != null) {

				btln.setStatus(battalion.getStatus());
			}

			btln.setCreated_at(new Date());

		}
		Battalion list = battalionRepo.save(btln);
		return list;
	}

	@Override
	public List<BattalionCompany> getAllBattalionCompany() {
		List<BattalionCompany> comList = btCompanyRepo.findAll();
		return comList;
	}

	@Override
	public BattalionOrganizationChart addBattalionOrganization(BattalionOrganizationChart btOrg) {
		// check if organization having active record for same post and battalion
		BattalionOrganizationChart existing = null;
		if (btOrg.getBattalionPost().getId() != 3) {
			existing = btOrgRepo.findByBattalionPostIdAndBattalionTypeIdAndStatus(btOrg.getBattalionPost().getId(),
					btOrg.getBattalionType().getId(), 1);
			if (existing != null) {
				existing.setStatus(0);
				btOrgRepo.save(existing);
			}
		}
		if (btOrg.getBattalionPost().getId() == 3) {
			existing = btOrgRepo.findByBattalionPostIdAndCompanyIdAndStatus(btOrg.getBattalionPost().getId(),
					btOrg.getCompanyId(), 1);
			if (existing != null) {
				existing.setStatus(0);
				btOrgRepo.save(existing);
			}
		}

		Battalion bt = battalionRepo.findById((Integer) btOrg.getBattalionType().getId()).get();
		if (bt != null) {
			btOrg.setBattalionType(bt);
		}

		btOrg.setCreatedAt(new Date());
		btOrg.setUpdatedOn(new Date());

		TrgBattalionPost battalionPost = btPostRepo.findById(btOrg.getBattalionPost().getId()).get();
		if (battalionPost != null) {
			btOrg.setBattalionPost(battalionPost);
		}

		BattalionOrganizationChart saved = btOrgRepo.save(btOrg);
		if (saved.getCompanyId() != null && saved.getCompanyId() != 0) {
			saved.setBattalionCompany(btCompanyRepo.findById(saved.getCompanyId()).get());
		}

		return saved;

		// TrgBattalionPost battalionPost =
		// btPostRepo.findById(btOrg.getBattalionPost().getId()).get();
		// if(battalionPost != null)
		// btOrg.setBattalionPost(battalionPost);
		//
		// if(btOrg.getBattalionCompany() == null || btOrg.getBattalionCompany().getId()
		// == 0) {
		//
		// return btOrgRepo.save(btOrg);
		// }
		//
		// else
		// {
		// BattalionCompany btCompany=
		// btCompanyRepo.findById(btOrg.getBattalionCompany().getId()).get();
		// if(btCompany != null) {
		// btOrg.setBattalionCompany(btCompany);
		// }
		//
		// return btOrgRepo.save(btOrg);
		// }
		//
		// if(btOrg.getBattalionCompany() != null || btOrg.getBattalionCompany().getId()
		// != 0)
		// {
		// BattalionCompany btCompany=
		// btCompanyRepo.findById(btOrg.getBattalionCompany().getId()).get();
		// if(btCompany != null) {
		// btOrg.setBattalionCompany(btCompany);
		// }
		// TrgBattalionPost battalionPost =
		// btPostRepo.findById(btOrg.getBattalionPost().getId()).get();
		// if(battalionPost != null)
		// btOrg.setBattalionPost(battalionPost);
		//
		// btOrg.setCreatedAt(new Date());
		// btOrg.setUpdatedOn(new Date());
		// return btOrgRepo.save(btOrg);
		// }

	}

	@Override
	public BattalionOrganizationChart updateBattalionOrganization(BattalionOrganizationChart btOrg) {

		// BattalionOrganizationChart updated = null;
		BattalionOrganizationChart existingRec = btOrgRepo.findById(btOrg.getId()).get();
		if (existingRec != null) {
			if (btOrg.getImage() != null && !btOrg.getImage().isEmpty()) {
				existingRec.setImage(btOrg.getImage());
			}
			existingRec.setName(btOrg.getName());
			existingRec.setRank(btOrg.getRank());
			existingRec.setAward(btOrg.getAward());
			existingRec.setPosition(btOrg.getPosition());
			existingRec.setSubPosition(btOrg.getSubPosition());
			existingRec.setStatus(btOrg.getStatus());

			Battalion bt = battalionRepo.findById((Integer) btOrg.getBattalionType().getId()).get();
			if (bt != null) {
				existingRec.setBattalionType(bt);
			}

			TrgBattalionPost battalionPost = btPostRepo.findById(btOrg.getBattalionPost().getId()).get();
			if (battalionPost != null) {
				existingRec.setBattalionPost(battalionPost);
			}

			existingRec.setUpdatedOn(new Date());

			existingRec.setCompanyId(btOrg.getCompanyId());

			// if(btOrg.getBattalionCompany() != null && btOrg.getBattalionCompany().getId()
			// != 0)
			// {
			// BattalionCompany btCompany=
			// btCompanyRepo.findById(btOrg.getBattalionCompany().getId()).get();
			// if(btCompany != null)
			// existingRec.setBattalionCompany(btCompany);
			//
			// updated = btOrgRepo.save(existingRec);
			// }
			// else
			// {
			// updated = btOrgRepo.save(existingRec);
			// }

		}
		BattalionOrganizationChart updated = btOrgRepo.save(existingRec);
		if (updated.getCompanyId() != 0 && updated.getCompanyId() != null) {
			BattalionCompany btCompany = btCompanyRepo.findById(btOrg.getCompanyId()).get();
			updated.setBattalionCompany(btCompany);
		}
		return updated;
	}

	@Override
	public List<BattalionOrganizationChart> getAllBattalionOrg(int battalionId, int status) {
		List<BattalionOrganizationChart> responseList = new ArrayList<>();
		List<BattalionOrganizationChart> btList = new ArrayList<>();

		if (battalionId == 0 && status == 2) {
			btList = btOrgRepo.findAllByOrderByIdDesc();
		} else if (battalionId > 0 && battalionId < 5 && status == 2) {
			btList = btOrgRepo.findByBattalionTypeIdOrderByIdDesc(battalionId);
		} else if (battalionId > 0 && battalionId < 5 && status < 2) {
			btList = btOrgRepo.findByBattalionTypeIdAndStatusOrderByIdDesc(battalionId, status);
		} else if (battalionId == 0 && status < 2) {
			btList = btOrgRepo.findAllByStatusOrderByIdDesc(status);
		} else {
			btList = btOrgRepo.findAllByOrderByIdDesc();
		}
		// btList = btOrgRepo.findAllByOrderByIdDesc();

		for (BattalionOrganizationChart btOrg : btList) {
			if (btOrg.getCompanyId() != null && btOrg.getCompanyId() != 0) {
				btOrg.setBattalionCompany(btCompanyRepo.findById(btOrg.getCompanyId()).get());
			}

			responseList.add(btOrg);
		}
		return responseList;
	}

	@Override
	public BattalionOrganizationChart viewBattalionOrg(Long id) {
		BattalionOrganizationChart btOrg = btOrgRepo.findById(id).get();
		if (btOrg.getCompanyId() != null && btOrg.getCompanyId() != 0) {
			BattalionCompany btCompany = btCompanyRepo.findById(btOrg.getCompanyId()).get();
			btOrg.setBattalionCompany(btCompany);
		}
		return btOrg;
	}

	@Override
	public BattalionOrganizationChart updateOrganizationStatus(Long id, int status) {
		BattalionOrganizationChart btOrg = btOrgRepo.findById(id).get();
		if (btOrg != null) {
			btOrg.setStatus(status);
			btOrg.setUpdatedOn(new Date());
		}
		btOrg = btOrgRepo.save(btOrg);
		return btOrg;
	}

	@Override
	public BattalionHistory addBattalionHistory(BattalionHistory history) {
		history.setCreatedAt(new Date());
		history.setUpdatedOn(new Date());
		BattalionHistory added = btHistoryRepo.save(history);
		return added;
	}

	@Override
	public List<BattalionHistory> getAllHistory() {
		List<BattalionHistory> list = btHistoryRepo.findAll();
		return list;
	}

	@Override
	public BattalionHistory viewHistoryById(Long id) {
		BattalionHistory record = btHistoryRepo.findById(id).get();
		return record;
	}

	@Override
	public BattalionHistory updateHistory(BattalionHistory history) {
		BattalionHistory record = btHistoryRepo.findById(history.getId()).get();
		if (record != null)

		{
			record.setImage(history.getImage());
			record.setDescription(history.getDescription());
			record.setStatus(history.getStatus());
			record.setUpdatedOn(new Date());

			Battalion bt = battalionRepo.findById(history.getBattalionType().getId()).get();
			record.setBattalionType(bt);
		}
		BattalionHistory updated = btHistoryRepo.save(record);
		return updated;
	}

	@Override
	public BattalionHistory updateHistoryStatus(Long id, int status) {
		BattalionHistory record = btHistoryRepo.findById(id).get();
		if (record != null) {
			record.setStatus(status);
			record.setUpdatedOn(new Date());
		}
		BattalionHistory updated = btHistoryRepo.save(record);
		return updated;

	}

	@Override
	public List<BattalionCompany> getBattalionSpecificCompany(int id) {
	//	Battalion bt = battalionRepo.findById(id).get();
		List<BattalionCompany> record = btCompanyRepo.findByBattalionTypeId(id);
	//	List<BattalionCompany> record = btCompanyRepo.findAll();
		return record;
	}

	@Override
	public BattalionHistory activeBattalionHistoryRecord(Integer battalionId) {
		Battalion bt = battalionRepo.findById(battalionId).get();
		Optional<BattalionHistory> btnHistory = btHistoryRepo
				.findTopByBattalionTypeIdAndStatusOrderByCreatedAtDesc(bt.getId(), 1);
		return btnHistory.get();
	}

	@Override
	public List<TrgBattalionPost> getBattalionPostList() {
		return btPostRepo.findAllByStatus(1);
	}

	@Override
	public RunbackRouteMr createRunbackRouteMr(RunbackRouteMr runbackRouteMr) {
		RunbackRouteMr result = null;
		if (runbackRouteMr != null && runbackRouteMr.getId() == null) {
			runbackRouteMr.setCreatedAt(new Date());
			runbackRouteMr.setStatus(1);
			result = runbackRouteMrRepo.save(runbackRouteMr);
		}
		return result;
	}

	@Override
	public RunbackRouteMr getResultByServiceIdAndResultType(String serviceId, String resultType) {
		Optional<RunbackRouteMr> result = runbackRouteMrRepo.findByServiceIdAndResultType(serviceId, resultType);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public List<RunbackRouteMr> getAllByStatus(Integer status) {
		List<RunbackRouteMr> result = null;
		if (status == 1) {
			result = runbackRouteMrRepo.getAllByStatus(status);
		} else {
			result = runbackRouteMrRepo.findAll();
		}

		return result;
	}

	@Override
	public RunbackRouteMr updateRunbackRouteMr(RunbackRouteMr runbackRouteMr) {
		RunbackRouteMr btln = null;

		if (runbackRouteMr != null && runbackRouteMr.getId() != null && runbackRouteMr.getId() != 0) {

			Optional<RunbackRouteMr> rrm = runbackRouteMrRepo.findById(runbackRouteMr.getId());
			if (rrm.isPresent()) {

				btln = rrm.get();
				btln.setUpdatedAt(new Date());

				if (runbackRouteMr.getObtainedMarks() != null) {

					btln.setObtainedMarks(runbackRouteMr.getObtainedMarks());
				}

				if (runbackRouteMr.getRemark() != null) {

					btln.setRemark(runbackRouteMr.getRemark());
				}

				if (runbackRouteMr.getHours() != null) {

					btln.setHours(runbackRouteMr.getHours());
				}

				if (runbackRouteMr.getMinutes() != null) {

					btln.setMinutes(runbackRouteMr.getMinutes());
				}

				if (runbackRouteMr.getDistance() != null) {

					btln.setDistance(runbackRouteMr.getDistance());
				}

				if (runbackRouteMr.getStatus() != null) {

					btln.setStatus(runbackRouteMr.getStatus());
				}

				btln = runbackRouteMrRepo.save(btln);

			}
		}

		return btln;
	}

	@Override
	public RunbackRouteMr getResultByServiceIdAndTermId(String serviceId, Long termId) {
		Optional<RunbackRouteMr> result1 = runbackRouteMrRepo.findByServiceIdAndResultTypeAndTermId(serviceId,
				"Runback", termId);
		Optional<RunbackRouteMr> result2 = runbackRouteMrRepo.findByServiceIdAndResultTypeAndTermId(serviceId,
				"Route March", termId);
		Integer s1 = 0;
		Integer s2 = 0;
		RunbackRouteMr runBackRouteMarchSum = new RunbackRouteMr();
		runBackRouteMarchSum.setSum(null);
		if (result1.isPresent()) {
			Integer obtainrdMarks = result1.get().getObtainedMarks();
			s1 = obtainrdMarks == null ? 0 : obtainrdMarks;

		}
		if (result2.isPresent()) {
			Integer obtainrdMarks = result2.get().getObtainedMarks();
			s2 = obtainrdMarks == null ? 0 : obtainrdMarks;
		}
		runBackRouteMarchSum.setSum(s1 + s2);
		return runBackRouteMarchSum;
	}

	@Override
	public RunbackRouteMr getResultByServiceIdAndResultTypeAndTermId(String serviceId, String resultType, Long termId) {
		// TODO Auto-generated method stub
		Optional<RunbackRouteMr> result = runbackRouteMrRepo.findByServiceIdAndResultTypeAndTermId(serviceId,
				resultType, termId);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public RouteRunMrPayload getCadetsByTermIdAndBattaionAndCompany(String resultType, Long termId, String battalion,
			String company, Pageable pageable) {
		Integer totalRecords = 0;
		RouteRunMrPayload routeRunMrPayload = new RouteRunMrPayload();
		List<RouteRunMrFilterPayload> routeRunMrFilterList = new ArrayList<RouteRunMrFilterPayload>();
		List<Cadet> cadetList = null;
		if (termId != null) {
			if (battalion != null) {
				if (company != null) {
					cadetList = cadetRepo.findAllByTermAndBattalianAndCompanyAndStatus(termId, battalion, company, 1,
							pageable);
					totalRecords = cadetRepo.findAllByTermAndBattalianAndCompanyAndStatus(termId, battalion, company, 1)
							.size();
				} else {
					cadetList = cadetRepo.findAllByTermAndBattalianAndStatus(termId, battalion, 1, pageable);
					totalRecords = cadetRepo.findAllByTermAndBattalianAndStatus(termId, battalion, 1).size();
				}
			} else {
				cadetList = cadetRepo.findAllByTermAndStatus(termId, 1, pageable);
				totalRecords = cadetRepo.findAllByTermAndStatus(termId, 1).size();
			}
		} else {
			cadetList = cadetRepo.findAllByStatus(1, pageable);
			// cadetList = pageCadet.toList();
			totalRecords = cadetRepo.findAllByStatus(1).size();
		}
		if (cadetList != null && cadetList.size() != 0) {
			for (Cadet cad : cadetList) {
				RouteRunMrFilterPayload routeRunMrFilterPayload = new RouteRunMrFilterPayload();
				routeRunMrFilterPayload.setId(cad.getId());
				routeRunMrFilterPayload.setTermId(cad.getTerm());
				routeRunMrFilterPayload.setName(cad.getName());
				routeRunMrFilterPayload.setBattalian(cad.getBattalian());
				routeRunMrFilterPayload.setCompany(cad.getCompany());
				routeRunMrFilterPayload.setRank(cad.getCadetRank());
				routeRunMrFilterPayload.setServiceId(cad.getServiceId());
				routeRunMrFilterPayload.setCourse(cad.getCourse());
				routeRunMrFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				routeRunMrFilterPayload.setNationality(cad.getNationality());
				RunbackRouteMr leaderResult = getResultByServiceIdAndResultTypeAndTermId(cad.getServiceId(), resultType,
						Long.parseLong(cad.getTerm().toString()));
				if (leaderResult != null) {
					routeRunMrFilterPayload.setRunbackRouteMrResult(leaderResult);
				} else {
					RunbackRouteMr runbackRouteMr = new RunbackRouteMr();
					runbackRouteMr.setResultType(resultType);
					runbackRouteMr.setServiceId(cad.getServiceId());
					runbackRouteMr.setStatus(1);
					runbackRouteMr.setTermId(cad.getTerm());
					routeRunMrFilterPayload.setRunbackRouteMrResult(runbackRouteMr);
				}
				routeRunMrFilterList.add(routeRunMrFilterPayload);
			}
			routeRunMrPayload.setTotalRecords(totalRecords);
			routeRunMrPayload.setRouteRunMrFilterPayload(routeRunMrFilterList);
			return routeRunMrPayload;
		} else {
			return null;
		}
	}

	@Override
	public RouteRunMrPayload getCadetsResultBySearch(Long termId, String resultType, String serviceId,
			Pageable pageable) {
		RouteRunMrPayload routeRunMrPayload = null;
		List<RouteRunMrFilterPayload> routeRunMrFilterList = new ArrayList<RouteRunMrFilterPayload>();
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		if (termId == null || termId == 0) {
			return routeRunMrPayload;
		}

		if (serviceId != null && !serviceId.trim().equals("")) {
			cadetList = cadetRepo.findAllByTermAndStatusAndServiceIdLike(termId, 1, "%" + serviceId + "%", pageable);
			totalRecords = cadetRepo.findAllByTermAndStatusAndServiceIdLike(termId, 1, "%" + serviceId + "%").size();
		} else {
			cadetList = cadetRepo.findAllByTermAndStatus(termId, 1, pageable);
			// cadetList = pageCadet.toList();
			totalRecords = cadetRepo.findAllByTermAndStatus(termId, 1).size();
		}
		if (cadetList != null && cadetList.size() != 0) {
			routeRunMrPayload = new RouteRunMrPayload();
			for (Cadet cad : cadetList) {
				RouteRunMrFilterPayload routeRunMrFilterPayload = new RouteRunMrFilterPayload();
				routeRunMrFilterPayload.setId(cad.getId());
				routeRunMrFilterPayload.setTermId(cad.getTerm());
				routeRunMrFilterPayload.setName(cad.getName());
				routeRunMrFilterPayload.setBattalian(cad.getBattalian());
				routeRunMrFilterPayload.setCompany(cad.getCompany());
				routeRunMrFilterPayload.setRank(cad.getCadetRank());
				routeRunMrFilterPayload.setServiceId(cad.getServiceId());
				routeRunMrFilterPayload.setCourse(cad.getCourse());
				routeRunMrFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				routeRunMrFilterPayload.setNationality(cad.getNationality());
				RunbackRouteMr leaderResult = getResultByServiceIdAndResultTypeAndTermId(cad.getServiceId(), resultType,
						Long.parseLong(cad.getTerm().toString()));
				if (leaderResult != null) {
					routeRunMrFilterPayload.setRunbackRouteMrResult(leaderResult);
				} else {
					RunbackRouteMr runbackRouteMr = new RunbackRouteMr();
					runbackRouteMr.setResultType(resultType);
					runbackRouteMr.setServiceId(cad.getServiceId());
					runbackRouteMr.setStatus(1);
					runbackRouteMr.setTermId(cad.getTerm());
					routeRunMrFilterPayload.setRunbackRouteMrResult(runbackRouteMr);
				}
				routeRunMrFilterList.add(routeRunMrFilterPayload);
			}
			routeRunMrPayload.setTotalRecords(totalRecords);
			routeRunMrPayload.setRouteRunMrFilterPayload(routeRunMrFilterList);
			return routeRunMrPayload;
		} else {
			return null;
		}
	}

	@Override
	public String updateBulkRouteRunMrResult(List<RouteRunMrFilterPayload> routeRunMrPayloadList) {
		String result = "failed";
		int size = routeRunMrPayloadList.size();
		if (size > 0) {
			for (RouteRunMrFilterPayload leadershipPayload : routeRunMrPayloadList) {
				RunbackRouteMr runbackRouteMrResult = leadershipPayload.getRunbackRouteMrResult();
				if (runbackRouteMrResult != null && runbackRouteMrResult.getId() != null
						&& runbackRouteMrResult.getId() != 0) {
					updateRunbackRouteMr(runbackRouteMrResult);
				} else if (runbackRouteMrResult != null) {
					if (runbackRouteMrResult.getObtainedMarks() != null) {
						runbackRouteMrResult.setCreatedAt(new Date());
						createRunbackRouteMr(runbackRouteMrResult);
					}
				}
			}
			result = "success";
		}
		return result;
	}

	@Override
	public Battalion getBattalionByShortName(String battalian) {
		Battalion battalion = battalionRepo.findByShortName(battalian);
		if (battalion != null) {
			return battalion;
		}
		return null;
	}

}
