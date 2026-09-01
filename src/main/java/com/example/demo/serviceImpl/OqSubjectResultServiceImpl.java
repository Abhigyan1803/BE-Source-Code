package com.example.demo.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.OqSubjectResult;
import com.example.demo.repository.OqSubjectResultRepo;
import com.example.demo.service.OqSubjectResultService;

@Service
public class OqSubjectResultServiceImpl implements OqSubjectResultService {

	@Autowired
	private OqSubjectResultRepo oqSubjectResultRepo;

	@Override
	public OqSubjectResult createSubResult(OqSubjectResult OqSubjectResult) {
		// TODO Auto-generated method stub
		return oqSubjectResultRepo.save(OqSubjectResult);

	}

	@Override
	public OqSubjectResult getSubResultById(Long id) {
		// TODO Auto-generated method stub
		Optional<OqSubjectResult> oqSubjectResult = oqSubjectResultRepo.findById(id);
		return oqSubjectResult.get();
	}

	@Override
	public OqSubjectResult updateSubResult(OqSubjectResult oqSubjectResult) {
		// TODO Auto-generated method stub
		OqSubjectResult oqSubjectRslt = null;
		// TODO Auto-generated method stub
		if (oqSubjectResult != null) {
			Optional<OqSubjectResult> getOqSubResult = oqSubjectResultRepo.findById(oqSubjectResult.getId());
			oqSubjectRslt = getOqSubResult.get();
			oqSubjectRslt.setObtainedMarksBnCdr(oqSubjectResult.getObtainedMarksBnCdr());
			oqSubjectRslt.setObtainedMarksCoyCdr(oqSubjectResult.getObtainedMarksCoyCdr());
			oqSubjectRslt.setObtainedMarksPlCdr(oqSubjectResult.getObtainedMarksPlCdr());
			oqSubjectRslt.setSubjectId(oqSubjectResult.getSubjectId());
			oqSubjectRslt.setTotalMarksBnCdr(oqSubjectResult.getTotalMarksBnCdr());
			oqSubjectRslt.setTotalMarksCoyCdr(oqSubjectResult.getTotalMarksCoyCdr());
			oqSubjectRslt.setTotalMarksPlCdr(oqSubjectResult.getTotalMarksPlCdr());
			oqSubjectRslt.setServiceId(oqSubjectResult.getServiceId());
			oqSubjectRslt.setStatus(oqSubjectResult.getStatus());
			oqSubjectRslt.setTermId(oqSubjectResult.getTermId());
			oqSubjectRslt.setUpdatedAt(oqSubjectResult.getUpdatedAt());
		}

		return oqSubjectResultRepo.save(oqSubjectRslt);
	}

}
