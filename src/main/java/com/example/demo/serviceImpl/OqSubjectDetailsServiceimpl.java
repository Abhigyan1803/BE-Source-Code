package com.example.demo.serviceImpl;

import java.util.ArrayList;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.OqSubjectAttribute;
import com.example.demo.model.OqSubjectDetails;
import com.example.demo.repository.OqSubjectAttributeRepo;
import com.example.demo.repository.OqSubjectDetailsRepo;
import com.example.demo.service.OqSubjectDetailsService;
import com.example.demo.util.ConstantVar;

@Service
public class OqSubjectDetailsServiceimpl implements OqSubjectDetailsService {

	@Autowired
	private OqSubjectDetailsRepo oqSubjectDetailsRepo;

	@Autowired
	private OqSubjectAttributeRepo oqSubjectAttributeRepo;

	@Override
	public OqSubjectDetails createSubject(OqSubjectDetails oqSubjectDetails) {
		// TODO Auto-generated method stub
		oqSubjectDetails.setStatus(ConstantVar.ONE);
		OqSubjectDetails saveSubjectDetails = oqSubjectDetailsRepo.save(oqSubjectDetails);
		if (saveSubjectDetails != null) {
			for (OqSubjectAttribute ca : saveSubjectDetails.getOqSubjectAttribute()) {
				// ca.setWeapon(saveWeapon);
				ca.setOqSubjectDetails(oqSubjectDetails);

				oqSubjectAttributeRepo.save(ca);
			}
		}
		return saveSubjectDetails;
	}

	@Override
	public Set<OqSubjectDetails> getSubjectByTerm(Long termId) {
		Set<OqSubjectDetails> set = oqSubjectDetailsRepo.findByOqSubjectAttributeTermId(termId);
		return set;
	}

	@Override
	public OqSubjectDetails getSubjectById(Long id) {
		Optional<OqSubjectDetails> list = oqSubjectDetailsRepo.findById(id);
		return list.get();
	}

	@Override
	public OqSubjectDetails updateSubject(OqSubjectDetails oqSubjectDetails) {
		new ArrayList<>();
		OqSubjectDetails sub = null;
		Optional<OqSubjectDetails> subData = oqSubjectDetailsRepo.findById(oqSubjectDetails.getId());
		if (subData.isPresent()) {
			sub = subData.get();

			if (oqSubjectDetails.getSubJectName() != null) {
				sub.setSubJectName(oqSubjectDetails.getSubJectName());
			}
			if (oqSubjectDetails.getStatus() != null) {
				sub.setStatus(oqSubjectDetails.getStatus());
			}
			for (OqSubjectAttribute ca : oqSubjectDetails.getOqSubjectAttribute()) {
				ca.setOqSubjectDetails(oqSubjectDetails);
				oqSubjectAttributeRepo.save(ca);
			}
		}
		return oqSubjectDetailsRepo.save(sub);
	}

}
