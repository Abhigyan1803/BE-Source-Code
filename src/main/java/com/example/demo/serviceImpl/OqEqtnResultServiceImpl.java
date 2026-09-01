package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cadet;
import com.example.demo.model.OqEqtnResult;
import com.example.demo.payload.OqEqtnFilterPayload;
import com.example.demo.payload.OqEqtnPayload;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.OqEqtnResultRepository;
import com.example.demo.service.OqEqtnResultService;

@Service
public class OqEqtnResultServiceImpl implements OqEqtnResultService {

	@Autowired
	private OqEqtnResultRepository oqEqtnResultRepo;

	@Autowired
	private AdminCadetRepo cadetRepo;

	@Override
	public OqEqtnResult findByServiceIdAndTermIdAndTermType(String serviceId, Long termId, String termType) {
		// TODO Auto-generated method stub
		Optional<OqEqtnResult> getOqEqtnResult = oqEqtnResultRepo.findByServiceIdAndTermIdAndTermType(serviceId, termId,
				termType);
		if (getOqEqtnResult.isPresent()) {
			OqEqtnResult oqEqResult = getOqEqtnResult.get();
			return oqEqResult;
		}
		return null;
	}

	@Override
	public OqEqtnPayload getCadetsBySearch(Long termId, String termType, String serviceId, Pageable pageable) {
		// TODO Auto-generated method stub
		OqEqtnPayload oqEqtnPayload = null;
		List<OqEqtnFilterPayload> oqEqtnFilterList = new ArrayList<OqEqtnFilterPayload>();
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		if (termId == null || termId == 0) {
			return oqEqtnPayload;
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
			oqEqtnPayload = new OqEqtnPayload();
			for (Cadet cad : cadetList) {
				OqEqtnFilterPayload oqFilterPayload = new OqEqtnFilterPayload();
				oqFilterPayload.setId(cad.getId());
				oqFilterPayload.setTermId(cad.getTerm());
				oqFilterPayload.setName(cad.getName());
				oqFilterPayload.setBattalian(cad.getBattalian());
				oqFilterPayload.setCompany(cad.getCompany());
				oqFilterPayload.setRank(cad.getCadetRank());
				oqFilterPayload.setServiceId(cad.getServiceId());
				oqFilterPayload.setCourse(cad.getCourse());
				oqFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				oqFilterPayload.setNationality(cad.getNationality());
				OqEqtnResult oqResult = findByServiceIdAndTermIdAndTermType(cad.getServiceId(), cad.getTerm(),
						termType);
				if (oqResult != null) {
					oqFilterPayload.setOqEqtnResult(oqResult);
				} else {
					OqEqtnResult oqEqtnResult = new OqEqtnResult();
					oqEqtnResult.setServiceId(cad.getServiceId());
					oqEqtnResult.setStatus(1);
					oqEqtnResult.setTermId(cad.getTerm());
					oqEqtnResult.setTermType(termType);
					oqFilterPayload.setOqEqtnResult(oqEqtnResult);
				}
				oqEqtnFilterList.add(oqFilterPayload);
			}
			oqEqtnPayload.setTotalRecords(totalRecords);
			oqEqtnPayload.setOqEqtnFilterPayload(oqEqtnFilterList);
			return oqEqtnPayload;
		} else {
			return null;
		}
	}

	@Override
	public OqEqtnPayload getCadetsByTermIdAndTermTypeAndBattaionAndCompany(Long termId, String termType,
			String battalion, String company, String serviceId, Pageable pageable) {
		// TODO Auto-generated method stub
		Integer totalRecords = 0;
		OqEqtnPayload oqEqtnPayload = new OqEqtnPayload();
		List<OqEqtnFilterPayload> oqEqtnFilterList = new ArrayList<OqEqtnFilterPayload>();
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
				cadetList = cadetRepo.findByStatusAndServiceIdLike(1, "%" + serviceId + "%", pageable);// --%D/5016%
				totalRecords = cadetRepo.findByStatusAndServiceIdLike(1, "%" + serviceId + "%").size();
			} else {
				cadetList = cadetRepo.findAllByStatus(1, pageable);
				// cadetList = pageCadet.toList();
				totalRecords = cadetRepo.findAllByStatus(1).size();
			}
		}
		if (cadetList != null && cadetList.size() != 0) {
			for (Cadet cad : cadetList) {
				OqEqtnFilterPayload oqFilterPayload = new OqEqtnFilterPayload();
				oqFilterPayload.setId(cad.getId());
				oqFilterPayload.setTermId(cad.getTerm());
				oqFilterPayload.setName(cad.getName());
				oqFilterPayload.setBattalian(cad.getBattalian());
				oqFilterPayload.setCompany(cad.getCompany());
				oqFilterPayload.setRank(cad.getCadetRank());
				oqFilterPayload.setServiceId(cad.getServiceId());
				oqFilterPayload.setCourse(cad.getCourse());
				oqFilterPayload.setCourseSerNo(cad.getCourseSerNo());
				oqFilterPayload.setNationality(cad.getNationality());
				OqEqtnResult oqResult = findByServiceIdAndTermIdAndTermType(cad.getServiceId(), cad.getTerm(),
						termType);
				if (oqResult != null) {
					oqFilterPayload.setOqEqtnResult(oqResult);
				} else {
					OqEqtnResult oqEqtnResult = new OqEqtnResult();
					oqEqtnResult.setServiceId(cad.getServiceId());
					oqEqtnResult.setStatus(1);
					oqEqtnResult.setTermId(cad.getTerm());
					oqEqtnResult.setTermType(termType);
					oqFilterPayload.setOqEqtnResult(oqEqtnResult);
				}
				oqEqtnFilterList.add(oqFilterPayload);
			}
			oqEqtnPayload.setTotalRecords(totalRecords);
			oqEqtnPayload.setOqEqtnFilterPayload(oqEqtnFilterList);
			return oqEqtnPayload;
		} else {
			return null;
		}
	}

	@Override
	public OqEqtnResult updateOqEqtnResult(OqEqtnResult oqEqtnResult) {
		// TODO Auto-generated method stub
		OqEqtnResult oqEqtnRslt = null;
		Optional<OqEqtnResult> oqEqtnRsltData = oqEqtnResultRepo.findById(oqEqtnResult.getId());
		if (oqEqtnRsltData.isPresent()) {
			oqEqtnRslt = oqEqtnRsltData.get();
			if (oqEqtnRslt != null) {
				oqEqtnRslt = oqEqtnResultRepo.save(oqEqtnRslt);
			}
		}
		return oqEqtnRslt;
	}

	@Override
	public OqEqtnResult createOqEqtnResult(OqEqtnResult oqEqtnResult) {
		// TODO Auto-generated method stub
		OqEqtnResult saveOqEqtnResult = oqEqtnResultRepo.save(oqEqtnResult);
		return saveOqEqtnResult;
	}

	@Override
	public String updateBulkOqEqtnResult(List<OqEqtnFilterPayload> oqEqtnPayloadList) {
		// TODO Auto-generated method stub
		String result = "failed";
		int size = oqEqtnPayloadList.size();
		if (size > 0) {
			for (OqEqtnFilterPayload oqEqtnPayload : oqEqtnPayloadList) {
				OqEqtnResult oqEqtnResult = oqEqtnPayload.getOqEqtnResult();
				if (oqEqtnResult != null && oqEqtnResult.getId() != null && oqEqtnResult.getId() != 0) {
					updateOqEqtnResult(oqEqtnResult);
				} else {
					if (oqEqtnResult.getObtainedMarks() != null) {
						oqEqtnResult.setCreatedAt(new Date());
						createOqEqtnResult(oqEqtnResult);
					}
				}
			}
			result = "success";
		}
		return result;
	}
}
