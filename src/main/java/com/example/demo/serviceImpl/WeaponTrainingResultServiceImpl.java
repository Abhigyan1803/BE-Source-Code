package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cadet;
import com.example.demo.model.CadetWeaponTrainingMainResult;
import com.example.demo.model.CadetWeaponTrainingMainResult1;
import com.example.demo.model.CadetWeaponTrainingResult;
import com.example.demo.model.CadetWeaponTrainingResult1;
import com.example.demo.model.SpotTestWtt;
import com.example.demo.model.Term;
import com.example.demo.model.Weapon;
import com.example.demo.model.WeaponAttributes;
import com.example.demo.model.WeaponTrainingResult;
import com.example.demo.payload.WeaponTrainingResultFilterPayload;
import com.example.demo.payload.WeaponTrainingResultPayload;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.CadetWeaponTrainingResultRepo;
import com.example.demo.repository.SpotTestWttRepo;
import com.example.demo.repository.TermRepo;
import com.example.demo.repository.WeaponAttributesRepo;
import com.example.demo.repository.WeaponRepo;
import com.example.demo.repository.WeaponTrainingMainResultRepo;
import com.example.demo.repository.WeaponTrainingMainResultRepo1;
import com.example.demo.repository.WeaponTrainingResultRepo;
import com.example.demo.repository.WeaponTrainingResultRepo1;
import com.example.demo.service.WeaponService;
import com.example.demo.service.WeaponTrainingResultService;

@Service
public class WeaponTrainingResultServiceImpl implements WeaponTrainingResultService {

	@Autowired
	WeaponTrainingResultRepo resultRepo;

	@Autowired
	WeaponTrainingResultRepo1 resultRepo1;

	@Autowired
	WeaponTrainingMainResultRepo mainResultRepo;

	@Autowired
	WeaponTrainingMainResultRepo1 mainResultRepo1;

	@Autowired
	CadetWeaponTrainingResultRepo cadetWeaponTrainingResultRepo;

	@Autowired
	SpotTestWttRepo spotTestWttRepo;

	@Autowired
	TermRepo termRepo;
	@Autowired
	private WeaponRepo weaponRepo;

	@Autowired
	private WeaponAttributesRepo weaponAttributesRepo;

	@Autowired
	private AdminCadetRepo cadetRepo;

	@Autowired
	WeaponService weaponService;

	@Override
	public List<CadetWeaponTrainingResult> createResult(List<CadetWeaponTrainingResult> result) {
		List<CadetWeaponTrainingResult> saveResult = resultRepo.saveAll(result);
		return saveResult;
	}

	@Override
	public List<CadetWeaponTrainingResult> getCadetResult(String serviceId, Long termId) {
		List<CadetWeaponTrainingResult> list = resultRepo.findByServiceIdAndTermId(serviceId, termId);
		return list;
	}

	@Override
	public List<CadetWeaponTrainingResult> updateResult(List<CadetWeaponTrainingResult> result) {

		// List<CadetWeaponTrainingResult> list = new ArrayList<String>();
		List<CadetWeaponTrainingResult> list = new ArrayList<>();
		for (CadetWeaponTrainingResult cadetResult : result) {

			Optional<CadetWeaponTrainingResult> cadRes = resultRepo.findByServiceIdAndTermIdAndAttributesId(
					cadetResult.getServiceId(), cadetResult.getTermId(), cadetResult.getAttributes().getId());
			CadetWeaponTrainingResult res = null;
			if (cadRes.isPresent()) {
				res = cadRes.get();

				if (cadetResult.getServiceId() != null) {
					res.setServiceId(cadetResult.getServiceId());
				}
				if (cadetResult.getTermId() != null) {
					res.setTermId(cadetResult.getTermId());
				}
				if (cadetResult.getStatus() != null) {
					res.setStatus(cadetResult.getStatus());
				}
				if (cadetResult.getMarks() != null) {
					res.setMarks(cadetResult.getMarks());
				}
			}
			CadetWeaponTrainingResult data = resultRepo.save(res);
			list.add(data);
		}
		return list;
	}

	@Override
	public List<CadetWeaponTrainingMainResult> createMainResult(List<CadetWeaponTrainingMainResult> mainResult) {
		List<CadetWeaponTrainingMainResult> saveResult = mainResultRepo.saveAll(mainResult);
		return saveResult;
	}

	@Override
	public List<CadetWeaponTrainingMainResult> getCadetMainResult(String serviceId, Long termId) {
		List<CadetWeaponTrainingMainResult> list = mainResultRepo.findByServiceIdAndTermId(serviceId, termId);
		return list;
	}

	@Override
	public CadetWeaponTrainingMainResult updateMainResult(CadetWeaponTrainingMainResult result) {
		CadetWeaponTrainingMainResult mainResult = null;
		Optional<CadetWeaponTrainingMainResult> oldMainResult = mainResultRepo.findById(result.getId());
		if (oldMainResult.isPresent()) {
			mainResult = oldMainResult.get();

			if (result.getServiceId() != null) {
				mainResult.setServiceId(result.getServiceId());
			}

			if (result.getTermId() != null) {
				mainResult.setServiceId(result.getServiceId());
			}

			if (result.getMarkParam() != null) {
				mainResult.setMarkParam(result.getMarkParam());
			}

			if (result.getMarks() != null) {
				mainResult.setMarks(result.getMarks());
			}

			if (result.getMaxMarks() != null) {
				mainResult.setMaxMarks(result.getMaxMarks());
			}

			if (result.getStatus() != null) {
				mainResult.setStatus(result.getStatus());
			}

		}

		return mainResultRepo.save(mainResult);
	}

	@Override
	public WeaponTrainingResult createCadetWTResult(WeaponTrainingResult result) {
		WeaponTrainingResult wt = null;
		List<CadetWeaponTrainingMainResult1> cwtmr = result.getCadetWTMainResultlist();
		for (CadetWeaponTrainingMainResult1 wtmr : cwtmr) {
			List<CadetWeaponTrainingResult1> cwtr = wtmr.getCadetWTResultlist();
			for (CadetWeaponTrainingResult1 wtr : cwtr) {
				resultRepo1.save(wtr);
			}
			mainResultRepo1.save(wtmr);
		}
		wt = cadetWeaponTrainingResultRepo.save(result);
		return wt;
	}

	@Override
	public SpotTestWtt addSpotTestAndWttMarks(SpotTestWtt spotTestWtt) {
		SpotTestWtt saveResult = spotTestWttRepo.save(spotTestWtt);
		return saveResult;

	}

	@Override
	public List<SpotTestWtt> getAllSpotTestAndWttMarksByStatus(Integer status) {
		List<SpotTestWtt> list = null;
		if (status == 1) {
			list = spotTestWttRepo.findByStatus(status);
		} else {
			list = spotTestWttRepo.findAll();
		}
		for (SpotTestWtt stw : list) {
			Term term = termRepo.findById(stw.getTermId()).get();
			stw.setTermName(term.getName());
		}

		return list;
	}

	@Override
	public SpotTestWtt getByTermId(Long termId) {
		SpotTestWtt spotTestWtt = null;
		Optional<SpotTestWtt> result = spotTestWttRepo.getByTermId(termId);
		if (result.isPresent()) {
			spotTestWtt = result.get();
		}
		return spotTestWtt;
	}

	@Override
	public SpotTestWtt updateSpotTestAndWttMarks(SpotTestWtt spotTestWtt) {
		SpotTestWtt mainResult = null;
		Optional<SpotTestWtt> oldMainResult = spotTestWttRepo.findById(spotTestWtt.getId());
		if (oldMainResult.isPresent()) {
			mainResult = oldMainResult.get();

			if (spotTestWtt.getSpotTestMark() != null) {
				mainResult.setSpotTestMark(spotTestWtt.getSpotTestMark());
			}

			if (spotTestWtt.getWttMark() != null) {
				mainResult.setWttMark(spotTestWtt.getWttMark());
			}

			if (spotTestWtt.getStatus() != null) {
				mainResult.setStatus(spotTestWtt.getStatus());
			}

		}

		return spotTestWttRepo.save(mainResult);
	}

	@Override
	public WeaponTrainingResult getCadetWeaponMainResult(String serviceId, Long termId) {
		WeaponTrainingResult wtr = cadetWeaponTrainingResultRepo.findByServiceIdAndTermId(serviceId, termId);
		if (wtr != null) {
			List<CadetWeaponTrainingMainResult1> cadetWTMRL = new ArrayList<CadetWeaponTrainingMainResult1>();
			List<CadetWeaponTrainingMainResult1> cadetWeaponTrainingMainResult1 = wtr.getCadetWTMainResultlist();

			for (CadetWeaponTrainingMainResult1 cadetWTMR : cadetWeaponTrainingMainResult1) {
				Optional<CadetWeaponTrainingMainResult1> cadetWeaponTMR = mainResultRepo1.findById(cadetWTMR.getId());
				Optional<Weapon> weapon = weaponRepo.findById(cadetWeaponTMR.get().getWeaponId());
				cadetWTMR.setWeaponName(weapon.get().getName());
				cadetWTMRL.add(cadetWTMR);
				List<CadetWeaponTrainingResult1> cadetWTR = new ArrayList<CadetWeaponTrainingResult1>();
				List<CadetWeaponTrainingResult1> cadetWeaponTrainingResult1 = cadetWTMR.getCadetWTResultlist();
				for (CadetWeaponTrainingResult1 cadetWTR1 : cadetWeaponTrainingResult1) {
					Optional<CadetWeaponTrainingResult1> cwtr1 = resultRepo1.findById(cadetWTR1.getId());
					Optional<WeaponAttributes> weaponAttributes = weaponAttributesRepo
							.findById(cwtr1.get().getAttributeId());
					cadetWTR1.setAttributeName(weaponAttributes.get().getAttrName());
					cadetWTR.add(cadetWTR1);
				}
				cadetWTMR.setCadetWTResultlist(cadetWTR);
			}
			wtr.setCadetWTMainResultlist(cadetWTMRL);
		}
		return wtr;
	}

	@Override
	public WeaponTrainingResult updateCadetWeaponResult(WeaponTrainingResult weaponTrainingResult) {
		WeaponTrainingResult weaponTraining = null;

		Optional<WeaponTrainingResult> WeaponTrainingRlt = cadetWeaponTrainingResultRepo
				.findById(weaponTrainingResult.getId());
		if (WeaponTrainingRlt.isPresent()) {
			weaponTraining = WeaponTrainingRlt.get();
			// weaponTraining.setServiceId(weaponTrainingResult.getServiceId());
			weaponTraining.setGrandTotal(weaponTrainingResult.getGrandTotal());
			weaponTraining.setWtt(weaponTrainingResult.getWtt());
			weaponTraining.setSpotTest(weaponTrainingResult.getSpotTest());
			// weaponTraining.setTermId(weaponTrainingResult.getTermId());
			// weaponTraining.setgPoint(weaponTrainingResult.getgPoint());
			weaponTraining.setRemark(weaponTrainingResult.getRemark());
			weaponTraining.setUpdatedAt(new Date());

			List<CadetWeaponTrainingMainResult1> cadetWTMR = new ArrayList<CadetWeaponTrainingMainResult1>();

			List<CadetWeaponTrainingMainResult1> CadetWeaponTrainingMainResult1 = weaponTrainingResult
					.getCadetWTMainResultlist();

			CadetWeaponTrainingMainResult1 cadetWTMT = null;

			for (CadetWeaponTrainingMainResult1 cwtr : CadetWeaponTrainingMainResult1) {

				Optional<CadetWeaponTrainingMainResult1> cadetWeaponTMR = mainResultRepo1.findById(cwtr.getId());
				cadetWTMT = cadetWeaponTMR.get();

				// cadetWTMT.setServiceId(cwtr.getServiceId());
				// cadetWTMT.setTermId(cwtr.getTermId());
				cadetWTMT.setMarks(cwtr.getMarks());
				cadetWTMT.setStatus(cwtr.getStatus());
				cadetWTMT.setStd(cwtr.getStd());
				cadetWTMT.setgPoint(cwtr.getgPoint());
				cadetWTMT.setRemark(cwtr.getRemark());
				// cadetWTMT.setWeaponId(cwtr.getWeaponId());
				cadetWTMT.setUpdatedAt(new Date());

				List<CadetWeaponTrainingResult1> cadetWTR = new ArrayList<CadetWeaponTrainingResult1>();
				List<CadetWeaponTrainingResult1> cadetWTResultlist = cwtr.getCadetWTResultlist();
				CadetWeaponTrainingResult1 cwtResult = null;
				for (CadetWeaponTrainingResult1 cwt : cadetWTResultlist) {
					Optional<CadetWeaponTrainingResult1> cwtr1 = resultRepo1.findById(cwt.getId());
					cwtResult = cwtr1.get();
					// cwtResult.setAttributeId(cwt.getAttributeId());
					// cwtResult.setServiceId(cwt.getServiceId());
					// cwtResult.setWeaponId(cwt.getWeaponId());
					cwtResult.setMarks(cwt.getMarks());
					cwtResult.setStatus(cwt.getStatus());
					// cwtResult.setTermId(cwt.getTermId());
					cwtResult.setUpdatedAt(new Date());
					cadetWTR.add(cwtResult);
					resultRepo1.save(cwtResult);
				}
				cadetWTMT.setCadetWTResultlist(cadetWTR);
				cadetWTMR.add(cadetWTMT);
				mainResultRepo1.save(cadetWTMT);
			}
			weaponTraining.setCadetWTMainResultlist(cadetWTMR);
			weaponTraining = cadetWeaponTrainingResultRepo.save(weaponTraining);

		}
		return weaponTraining;
	}

	@Override
	public List<WeaponTrainingResult> getCadetWeaponMainResultByServiceId(String serviceId) {
		// TODO Auto-generated method stub
		List<WeaponTrainingResult> wtrList = cadetWeaponTrainingResultRepo.findByServiceIdOrderByTermId(serviceId);
		// WeaponTrainingResult wtr =
		// cadetWeaponTrainingResultRepo.findByServiceIdAndTermId(serviceId, termId);
		if (wtrList != null && wtrList.size() != 0) {
			for (WeaponTrainingResult wtr : wtrList) {
				List<CadetWeaponTrainingMainResult1> cadetWTMRL = new ArrayList<CadetWeaponTrainingMainResult1>();
				List<CadetWeaponTrainingMainResult1> cadetWeaponTrainingMainResult1 = wtr.getCadetWTMainResultlist();

				for (CadetWeaponTrainingMainResult1 cadetWTMR : cadetWeaponTrainingMainResult1) {
					Optional<CadetWeaponTrainingMainResult1> cadetWeaponTMR = mainResultRepo1
							.findById(cadetWTMR.getId());
					Optional<Weapon> weapon = weaponRepo.findById(cadetWeaponTMR.get().getWeaponId());
					cadetWTMR.setWeaponName(weapon.get().getName());
					cadetWTMRL.add(cadetWTMR);
					List<CadetWeaponTrainingResult1> cadetWTR = new ArrayList<CadetWeaponTrainingResult1>();
					List<CadetWeaponTrainingResult1> cadetWeaponTrainingResult1 = cadetWTMR.getCadetWTResultlist();
					for (CadetWeaponTrainingResult1 cadetWTR1 : cadetWeaponTrainingResult1) {
						Optional<CadetWeaponTrainingResult1> cwtr1 = resultRepo1.findById(cadetWTR1.getId());
						Optional<WeaponAttributes> weaponAttributes = weaponAttributesRepo
								.findById(cwtr1.get().getAttributeId());
						cadetWTR1.setAttributeName(weaponAttributes.get().getAttrName());
						cadetWTR.add(cadetWTR1);
					}
					cadetWTMR.setCadetWTResultlist(cadetWTR);
				}
				wtr.setCadetWTMainResultlist(cadetWTMRL);
			}
		}
		return wtrList;

	}

	@Override
	public WeaponTrainingResultPayload getCadetsByTermIdAndBattaionAndCompany(Long termId, String battalion,
			String company, String serviceId, Pageable pageable) {
		Integer totalRecords = 0;
		WeaponTrainingResultPayload weaponTrainingResultPayload = new WeaponTrainingResultPayload();
		List<WeaponTrainingResultFilterPayload> weaponTrainingResultFilterList = new ArrayList<WeaponTrainingResultFilterPayload>();
		List<Cadet> cadetList = null;
		if (termId != null) {
			if (battalion != null) {
				if (company != null) {
					if (serviceId != null) {
						cadetList = cadetRepo.findAllByTermAndBattalianAndCompanyAndStatusAndServiceIdLike(termId,
								battalion, company, 1, "%" + serviceId + "%", pageable);
						totalRecords = cadetRepo.findAllByTermAndBattalianAndCompanyAndStatusAndServiceIdLike(termId,
								battalion, company, 1, "%" + serviceId + "%").size();
					} else {
						cadetList = cadetRepo.findAllByTermAndBattalianAndCompanyAndStatus(termId, battalion, company,
								1, pageable);
						totalRecords = cadetRepo
								.findAllByTermAndBattalianAndCompanyAndStatus(termId, battalion, company, 1).size();
					}

				} else {
					if (serviceId != null) {
						cadetList = cadetRepo.findAllByTermAndBattalianAndStatusAndServiceIdLike(termId, battalion, 1,
								"%" + serviceId + "%", pageable);
						totalRecords = cadetRepo.findAllByTermAndBattalianAndStatusAndServiceIdLike(termId, battalion,
								1, "%" + serviceId + "%").size();
					} else {
						cadetList = cadetRepo.findAllByTermAndBattalianAndStatus(termId, battalion, 1, pageable);
						totalRecords = cadetRepo.findAllByTermAndBattalianAndStatus(termId, battalion, 1).size();
					}

				}
			} else {
				if (serviceId != null) {
					cadetList = cadetRepo.findAllByTermAndStatusAndServiceIdLike(termId, 1, "%" + serviceId + "%",
							pageable);
					totalRecords = cadetRepo.findAllByTermAndStatusAndServiceIdLike(termId, 1, "%" + serviceId + "%")
							.size();
				} else {
					cadetList = cadetRepo.findAllByTermAndStatus(termId, 1, pageable);
					totalRecords = cadetRepo.findAllByTermAndStatus(termId, 1).size();
				}

			}
		} else {
			if (serviceId != null) {
				cadetList = cadetRepo.findByStatusAndServiceIdLike(1, "%" + serviceId + "%", pageable);
				totalRecords = cadetRepo.findByStatusAndServiceIdLike(1, "%" + serviceId + "%").size();
			} else {
				cadetList = cadetRepo.findAllByStatus(1, pageable);
				// cadetList = pageCadet.toList();
				totalRecords = cadetRepo.findAllByStatus(1).size();
			}

		}

		if (cadetList != null && cadetList.size() != 0) {
			for (Cadet cad : cadetList) {
				WeaponTrainingResultFilterPayload wtFilterPayload = new WeaponTrainingResultFilterPayload();
				wtFilterPayload.setId(cad.getId());
				wtFilterPayload.setTermId(cad.getTerm());
				wtFilterPayload.setName(cad.getName());
				wtFilterPayload.setBattalian(cad.getBattalian());
				wtFilterPayload.setCompany(cad.getCompany());
				wtFilterPayload.setRank(cad.getCadetRank());
				wtFilterPayload.setServiceId(cad.getServiceId());
				wtFilterPayload.setCourse(cad.getCourse());
				wtFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				wtFilterPayload.setNationality(cad.getNationality());
				WeaponTrainingResult wtResult = getCadetWeaponMainResult(cad.getServiceId(), cad.getTerm());
				if (wtResult != null) {
					//// start sort here
					List<CadetWeaponTrainingMainResult1> weaponResultList = wtResult.getCadetWTMainResultlist();
					Collections.sort(weaponResultList, (wr1, wr2) -> {
						if (wr1.getWeaponId() > wr2.getWeaponId()) {
							return 1;
						} else {
							return -1;
						}
					});
					for (CadetWeaponTrainingMainResult1 weaponResult : weaponResultList) {
						List<CadetWeaponTrainingResult1> warList = weaponResult.getCadetWTResultlist();
						Collections.sort(warList, (war1, war2) -> {
							if (war1.getAttributeId() > war2.getAttributeId()) {
								return 1;
							} else {
								return -1;
							}
						});
					}
					//// till here
					wtFilterPayload.setWeaponTrainingResult(wtResult);
				} else {
					WeaponTrainingResult wtResult1 = new WeaponTrainingResult();
					wtResult1.setServiceId(cad.getServiceId());
					wtResult1.setTermId(cad.getTerm());
					List<CadetWeaponTrainingMainResult1> cadetWeaponTrainingMainResult1 = new ArrayList<CadetWeaponTrainingMainResult1>();

					// Set<Weapon> weaponList = weaponService.getWeaponByTerm(termId, 1);
					List<Weapon> weaponList = weaponService.getWeaponByTermNew(termId, 1);
					for (Weapon weapon : weaponList) {
						List<CadetWeaponTrainingResult1> cadetWeaponTrainingResult1 = new ArrayList<CadetWeaponTrainingResult1>();
						CadetWeaponTrainingMainResult1 weaponResult = new CadetWeaponTrainingMainResult1();
						weaponResult.setWeaponId(weapon.getId());
						weaponResult.setServiceId(cad.getServiceId());
						weaponResult.setTermId(cad.getTerm());
						weaponResult.setMaxMarks(weapon.getTotalMaxMarks());
						if (cad.getTerm() == 1) {
							weaponResult.setMaxGPoint(Integer.parseInt(weapon.getgPointITerm()));
						}
						if (cad.getTerm() == 2) {
							weaponResult.setMaxGPoint(Integer.parseInt(weapon.getgPointIITerm()));
						}
						if (cad.getTerm() == 3) {
							weaponResult.setMaxGPoint(Integer.parseInt(weapon.getgPointIIITerm()));
						}
						if (cad.getTerm() == 7) {
							weaponResult.setMaxGPoint(Integer.parseInt(weapon.getgPointIITech()));
						}

						List<WeaponAttributes> weaponAttributeList = weapon.getWa();
						for (WeaponAttributes weaponAttribute : weaponAttributeList) {
							CadetWeaponTrainingResult1 attributeResult = new CadetWeaponTrainingResult1();
							attributeResult.setWeaponId(weapon.getId());
							attributeResult.setAttributeId(weaponAttribute.getId());
							attributeResult.setServiceId(cad.getServiceId());
							attributeResult.setTermId(cad.getTerm());
							attributeResult.setMaxMarks(weaponAttribute.getMaxMarks());
							cadetWeaponTrainingResult1.add(attributeResult);
						}
						weaponResult.setCadetWTResultlist(cadetWeaponTrainingResult1);
						cadetWeaponTrainingMainResult1.add(weaponResult);
					}
					wtResult1.setCadetWTMainResultlist(cadetWeaponTrainingMainResult1);
					wtFilterPayload.setWeaponTrainingResult(wtResult1);
				}
				weaponTrainingResultFilterList.add(wtFilterPayload);
			}

			weaponTrainingResultPayload.setTotalRecords(totalRecords);
			weaponTrainingResultPayload.setWeaponTrainingResultFilterPayload(weaponTrainingResultFilterList);
			return weaponTrainingResultPayload;
		} else {
			return null;
		}

	}

	@Override
	public WeaponTrainingResultPayload getCadetsBySearch(String serviceId, Long termId, Pageable pageable) {
		// TODO Auto-generated method stub
		List<WeaponTrainingResultFilterPayload> weaponTrainingResultFilterList = new ArrayList<WeaponTrainingResultFilterPayload>();
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		WeaponTrainingResultPayload weaponTrainingResultPayload = new WeaponTrainingResultPayload();
		if (serviceId != null && !serviceId.trim().equals("")) {
			cadetList = cadetRepo.findAllByTermAndStatusAndServiceIdLike(termId, 1, "%" + serviceId + "%", pageable);
			totalRecords = cadetRepo.findAllByTermAndStatusAndServiceIdLike(termId, 1, "%" + serviceId + "%").size();
		} else {
			cadetList = cadetRepo.findAllByTermAndStatus(termId, 1, pageable);
			// cadetList = pageCadet.toList();
			totalRecords = cadetRepo.findAllByTermAndStatus(termId, 1).size();
		}
		if (cadetList != null && cadetList.size() != 0) {
			for (Cadet cad : cadetList) {
				WeaponTrainingResultFilterPayload wtFilterPayload = new WeaponTrainingResultFilterPayload();
				wtFilterPayload.setId(cad.getId());
				wtFilterPayload.setTermId(cad.getTerm());
				wtFilterPayload.setName(cad.getName());
				wtFilterPayload.setBattalian(cad.getBattalian());
				wtFilterPayload.setCompany(cad.getCompany());
				wtFilterPayload.setRank(cad.getCadetRank());
				wtFilterPayload.setServiceId(cad.getServiceId());
				wtFilterPayload.setCourse(cad.getCourse());
				wtFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				wtFilterPayload.setNationality(cad.getNationality());
				WeaponTrainingResult wtResult = getCadetWeaponMainResult(cad.getServiceId(), cad.getTerm());
				if (wtResult != null) {
					//// start sort here
					List<CadetWeaponTrainingMainResult1> weaponResultList = wtResult.getCadetWTMainResultlist();
					Collections.sort(weaponResultList, (wr1, wr2) -> {
						if (wr1.getWeaponId() > wr2.getWeaponId()) {
							return 1;
						} else {
							return -1;
						}
					});
					for (CadetWeaponTrainingMainResult1 weaponResult : weaponResultList) {
						List<CadetWeaponTrainingResult1> warList = weaponResult.getCadetWTResultlist();
						Collections.sort(warList, (war1, war2) -> {
							if (war1.getAttributeId() > war2.getAttributeId()) {
								return 1;
							} else {
								return -1;
							}
						});
					}
					//// till here
					wtFilterPayload.setWeaponTrainingResult(wtResult);
				} else {
					WeaponTrainingResult wtResult1 = new WeaponTrainingResult();
					wtResult1.setServiceId(cad.getServiceId());
					wtResult1.setTermId(cad.getTerm());
					List<CadetWeaponTrainingMainResult1> cadetWeaponTrainingMainResult1 = new ArrayList<CadetWeaponTrainingMainResult1>();

					// Set<Weapon> weaponList = weaponService.getWeaponByTerm(termId, 1);
					List<Weapon> weaponList = weaponService.getWeaponByTermNew(termId, 1);
					for (Weapon weapon : weaponList) {
						List<CadetWeaponTrainingResult1> cadetWeaponTrainingResult1 = new ArrayList<CadetWeaponTrainingResult1>();
						CadetWeaponTrainingMainResult1 weaponResult = new CadetWeaponTrainingMainResult1();
						weaponResult.setWeaponId(weapon.getId());
						weaponResult.setServiceId(cad.getServiceId());
						weaponResult.setTermId(cad.getTerm());
						weaponResult.setMaxMarks(weapon.getTotalMaxMarks());
						if (cad.getTerm() == 1) {
							weaponResult.setMaxGPoint(Integer.parseInt(weapon.getgPointITerm()));
						}
						if (cad.getTerm() == 2) {
							weaponResult.setMaxGPoint(Integer.parseInt(weapon.getgPointIITerm()));
						}
						if (cad.getTerm() == 3) {
							weaponResult.setMaxGPoint(Integer.parseInt(weapon.getgPointIIITerm()));
						}
						if (cad.getTerm() == 7) {
							weaponResult.setMaxGPoint(Integer.parseInt(weapon.getgPointIITech()));
						}
						List<WeaponAttributes> weaponAttributeList = weapon.getWa();
						for (WeaponAttributes weaponAttribute : weaponAttributeList) {
							CadetWeaponTrainingResult1 attributeResult = new CadetWeaponTrainingResult1();
							attributeResult.setWeaponId(weapon.getId());
							attributeResult.setAttributeId(weaponAttribute.getId());
							attributeResult.setServiceId(cad.getServiceId());
							attributeResult.setTermId(cad.getTerm());
							cadetWeaponTrainingResult1.add(attributeResult);
						}
						weaponResult.setCadetWTResultlist(cadetWeaponTrainingResult1);
						cadetWeaponTrainingMainResult1.add(weaponResult);
					}
					wtResult1.setCadetWTMainResultlist(cadetWeaponTrainingMainResult1);
					wtFilterPayload.setWeaponTrainingResult(wtResult1);
				}
				weaponTrainingResultFilterList.add(wtFilterPayload);
			}
			weaponTrainingResultPayload.setTotalRecords(totalRecords);
			weaponTrainingResultPayload.setWeaponTrainingResultFilterPayload(weaponTrainingResultFilterList);
			return weaponTrainingResultPayload;

		} else {
			return null;
		}
	}

	@Override
	public String updateBulkWeaponTrainingResult(
			List<WeaponTrainingResultFilterPayload> weaponTrainingResultPayloadList) {
		String result = "failed";
		int size = weaponTrainingResultPayloadList.size();
		if (size > 0) {
			for (WeaponTrainingResultFilterPayload weaponTrainingPayload : weaponTrainingResultPayloadList) {
				WeaponTrainingResult weaponTrainingResult = weaponTrainingPayload.getWeaponTrainingResult();
				if (weaponTrainingResult != null && weaponTrainingResult.getId() != null
						&& weaponTrainingResult.getId() != 0) {
					updateCadetWeaponResult(weaponTrainingResult);
				} else if (weaponTrainingResult != null) {
					if (weaponTrainingResult.getGrandTotal() != null) {
						weaponTrainingResult.setCreatedAt(new Date());
						createCadetWTResult(weaponTrainingResult);
					}

				}
			}
			result = "success";
		}
		return result;

	}

}