package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cadet;
import com.example.demo.model.OqDrillResult;
import com.example.demo.payload.OqDrillFilterPayload;
import com.example.demo.payload.OqDrillPayload;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.OqDrillResultRepository;
import com.example.demo.service.OqDrillResultService;

@Service
public class OqDrillResultServiceImpl implements OqDrillResultService {

	@Autowired
	private OqDrillResultRepository oqDrillResultRepo;

	@Autowired
	private AdminCadetRepo cadetRepo;

	@Override
	public OqDrillResult findByServiceIdAndTermIdAndTermType(String serviceId, Long termId, String termType) {
		// TODO Auto-generated method stub
		Optional<OqDrillResult> getOqDrillResult = oqDrillResultRepo.findByServiceIdAndTermIdAndTermType(serviceId,
				termId, termType);
		if (getOqDrillResult.isPresent()) {
			OqDrillResult oqDrillResult = getOqDrillResult.get();
			return oqDrillResult;
		}
		return null;
	}

	@Override
	public OqDrillPayload getCadetsBySearch(Long termId, String termType, String serviceId, Pageable pageable) {
		// TODO Auto-generated method stub
		OqDrillPayload oqDrillPayload = null;
		List<OqDrillFilterPayload> oqDrillFilterList = new ArrayList<OqDrillFilterPayload>();
		List<Cadet> cadetList = null;
		Integer totalRecords = 0;
		if (termId == null || termId == 0) {
			return oqDrillPayload;
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
			oqDrillPayload = new OqDrillPayload();
			for (Cadet cad : cadetList) {
				OqDrillFilterPayload oqFilterPayload = new OqDrillFilterPayload();
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
				OqDrillResult oqResult = findByServiceIdAndTermIdAndTermType(cad.getServiceId(), cad.getTerm(),
						termType);
				if (oqResult != null) {
					oqFilterPayload.setOqDrillResult(oqResult);
				} else {
					OqDrillResult oqDrillResult = new OqDrillResult();
					oqDrillResult.setServiceId(cad.getServiceId());
					oqDrillResult.setStatus(1);
					oqDrillResult.setTermId(cad.getTerm());
					oqDrillResult.setTermType(termType);
					oqFilterPayload.setOqDrillResult(oqDrillResult);
				}
				oqDrillFilterList.add(oqFilterPayload);
			}
			oqDrillPayload.setTotalRecords(totalRecords);
			oqDrillPayload.setOqDrillFilterPayload(oqDrillFilterList);
			return oqDrillPayload;
		} else {
			return null;
		}
	}

	@Override
	public OqDrillPayload getCadetsByTermIdAndTermTypeAndBattaionAndCompany(Long termId, String termType,
			String battalion, String company, String serviceId, Pageable pageable) {
		// TODO Auto-generated method stub
		Integer totalRecords = 0;
		OqDrillPayload oqDrillPayload = new OqDrillPayload();
		List<OqDrillFilterPayload> oqDrillFilterList = new ArrayList<OqDrillFilterPayload>();
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
				OqDrillFilterPayload oqFilterPayload = new OqDrillFilterPayload();
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
				OqDrillResult oqResult = findByServiceIdAndTermIdAndTermType(cad.getServiceId(), cad.getTerm(),
						termType);
				if (oqResult != null) {
					oqFilterPayload.setOqDrillResult(oqResult);
				} else {
					OqDrillResult oqDrillResult = new OqDrillResult();
					oqDrillResult.setServiceId(cad.getServiceId());
					oqDrillResult.setStatus(1);
					oqDrillResult.setTermId(cad.getTerm());
					oqDrillResult.setTermType(termType);
					oqFilterPayload.setOqDrillResult(oqDrillResult);
				}
				oqDrillFilterList.add(oqFilterPayload);
			}
			oqDrillPayload.setTotalRecords(totalRecords);
			oqDrillPayload.setOqDrillFilterPayload(oqDrillFilterList);
			return oqDrillPayload;
		} else {
			return null;
		}
	}

	@Override
	public OqDrillResult createOqDrillResult(OqDrillResult oqDrillResult) {
		// TODO Auto-generated method stub
		OqDrillResult saveOqDrillResult = oqDrillResultRepo.save(oqDrillResult);
		return saveOqDrillResult;
	}

	@Override
	public OqDrillResult updateOqDrillResult(OqDrillResult oqDrillResult) {
		// TODO Auto-generated method stub
		OqDrillResult oqDrillRslt = null;
		Optional<OqDrillResult> oqDrillRsltData = oqDrillResultRepo.findById(oqDrillResult.getId());
		if (oqDrillRsltData.isPresent()) {
			oqDrillRslt = oqDrillRsltData.get();
			if (oqDrillRslt != null) {
				oqDrillRslt = oqDrillResultRepo.save(oqDrillRslt);
			}
		}
		return oqDrillRslt;
	}

	@Override
	public String updateBulkOqdrillResult(List<OqDrillFilterPayload> oqDrillPayloadList) {
		// TODO Auto-generated method stub
		String result = "failed";
		int size = oqDrillPayloadList.size();
		if (size > 0) {
			for (OqDrillFilterPayload oqDrillPayload : oqDrillPayloadList) {
				OqDrillResult oqDrillResult = oqDrillPayload.getOqDrillResult();
				if (oqDrillResult != null && oqDrillResult.getId() != null && oqDrillResult.getId() != 0) {
					updateOqDrillResult(oqDrillResult);
				} else {
					if (oqDrillResult.getObtainedMarks() != null) {
						oqDrillResult.setCreatedAt(new Date());
						createOqDrillResult(oqDrillResult);
					}

				}
			}
			result = "success";
		}
		return result;
	}
}
