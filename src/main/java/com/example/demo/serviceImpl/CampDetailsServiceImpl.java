package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CampAttribute;
import com.example.demo.model.CampDetails;
import com.example.demo.model.Weapon;
import com.example.demo.model.WeaponAttributes;
import com.example.demo.repository.CampAttributeRepo;
import com.example.demo.repository.CampDetailsRepo;
import com.example.demo.repository.WeaponAttributesRepo;
import com.example.demo.repository.WeaponRepo;
import com.example.demo.service.CampDetailsService;
import com.example.demo.util.ConstantVar;

@Service
public class CampDetailsServiceImpl implements CampDetailsService {

	@Autowired
	CampDetailsRepo campDetailsRepo;

	@Autowired
	CampAttributeRepo campAttributeRepo;

	@Override
	public CampDetails createCamp(CampDetails campDetails) {
		campDetails.setStatus(ConstantVar.ONE);
		CampDetails saveCampDetails = campDetailsRepo.save(campDetails);
		if (saveCampDetails != null) {
			for (CampAttribute ca : saveCampDetails.getCampAtribute()) {
			//	ca.setWeapon(saveWeapon);
				ca.setCampDetails(saveCampDetails);
				
				campAttributeRepo.save(ca);
			}
		}
		return saveCampDetails;
	}

	
	@Override
	public Set<CampDetails> getCampByTerm(Long termId) {
		Set<CampDetails> set = campDetailsRepo.findByCampAtributeTermId(termId);
		return set;
	}

	@Override
	public CampDetails getCampById(Long id) {
		Optional<CampDetails> list = campDetailsRepo.findById(id);
		return list.get();
	}

	@Override
	public CampDetails updateCamp(CampDetails campDetails) {
		new ArrayList<>();
		CampDetails camp = null;
		Optional<CampDetails> campData= campDetailsRepo.findById(campDetails.getId());
		if (campData.isPresent()) {
			camp = campData.get();

			if (campDetails.getCampName() != null) {
				camp.setCampName(campDetails.getCampName());
			}
			if (campDetails.getStatus() != null) {
				camp.setStatus(campDetails.getStatus());
			}

			for (CampAttribute ca : campDetails.getCampAtribute()) {
				ca.setCampDetails(campDetails);
				campAttributeRepo.save(ca);
			}

		}
		return campDetailsRepo.save(camp);
	}

}
