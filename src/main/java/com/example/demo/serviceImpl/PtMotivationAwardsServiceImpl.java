package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PtMotivationAwards;
import com.example.demo.repository.PtMotivationAwardsRepository;
import com.example.demo.service.PtMotivationAwardsService;

@Service
public class PtMotivationAwardsServiceImpl implements PtMotivationAwardsService{
	@Autowired 
	private PtMotivationAwardsRepository repo;
	
	@Override
	public PtMotivationAwards addPtMotivationAwards(PtMotivationAwards ptMotivationAwards) {
		// TODO Auto-generated method stub
		return repo.save(ptMotivationAwards);
	}
	
	@Override
	public PtMotivationAwards findByServiceIdAndTermId(String serviceId, Long termId) {
		// TODO Auto-generated method stub
		return repo.findByServiceIdAndTermId(serviceId,termId);
	}
	@Override
	public PtMotivationAwards updatePtMotivationAwards(PtMotivationAwards ptMotivationAwards) {
		// TODO Auto-generated method stub
		PtMotivationAwards ptMotivation=null;
		if(ptMotivationAwards !=null && ptMotivationAwards.getId()!=null && ptMotivationAwards.getId()!=0) {
			
			Optional<PtMotivationAwards> awards=repo.findById(ptMotivationAwards.getId());
			if(awards.isPresent()) {
				ptMotivation=awards.get();
				
				if (ptMotivationAwards.getMeritCard() != null) {

					ptMotivation.setMeritCard(ptMotivationAwards.getMeritCard());
				}
				if (ptMotivationAwards.getHalfBlue() != null) {

					ptMotivation.setHalfBlue(ptMotivationAwards.getHalfBlue());
				}
				if (ptMotivationAwards.getBlue() != null) {

					ptMotivation.setBlue(ptMotivationAwards.getBlue());
				}
				if (ptMotivationAwards.getBlazer() != null) {

					ptMotivation.setBlazer(ptMotivationAwards.getBlazer());
				}
				if (ptMotivationAwards.getTotalMarks() != null) {

					ptMotivation.setTotalMarks(ptMotivationAwards.getTotalMarks());
				}
				if (ptMotivationAwards.getObtainedMarks() != null) {

					ptMotivation.setObtainedMarks(ptMotivationAwards.getObtainedMarks());
				}
				ptMotivation.setUpdatedAt(new Date());
			}
			ptMotivation=repo.save(ptMotivation);
		}
		return ptMotivation;
	}
}
