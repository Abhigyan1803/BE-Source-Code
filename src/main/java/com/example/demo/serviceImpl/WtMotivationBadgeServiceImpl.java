package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.WtMotivationBadge;
import com.example.demo.repository.WtMotivationBadgeRepository;
import com.example.demo.service.WtMotivationBadgeService;

@Service
public class WtMotivationBadgeServiceImpl implements WtMotivationBadgeService {

	@Autowired
	private WtMotivationBadgeRepository repo;

	@Override
	public WtMotivationBadge addMotivationBadge(WtMotivationBadge wtMotivationBadge) {
		// TODO Auto-generated method stub
		return repo.save(wtMotivationBadge);
	}

	@Override
	public WtMotivationBadge getByServiceIdAndTermId(String serviceId, Long termId) {
		// TODO Auto-generated method stub
		return repo.findByServiceIdAndTermId(serviceId, termId);
	}

	@Override
	public WtMotivationBadge getByServiceId(String serviceId) {
		// TODO Auto-generated method stub
		Optional<WtMotivationBadge> result = repo.findByServiceId(serviceId);
//			if(result!=null && result.size()!=0) {
//			return result;
//			}
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public WtMotivationBadge updateMotivationBadge(WtMotivationBadge wtMotivationBadge) {

		WtMotivationBadge motivationBadge = null;
		if (wtMotivationBadge != null && wtMotivationBadge.getId() != null && wtMotivationBadge.getId() != 0) {

			Optional<WtMotivationBadge> wmb = repo.findById(wtMotivationBadge.getId());
			if (wmb.isPresent()) {
				motivationBadge = wmb.get();

				if (wtMotivationBadge.getBadge1() != null) {
					motivationBadge.setBadge1(wtMotivationBadge.getBadge1());
				}
				if (wtMotivationBadge.getBadge2() != null) {
					motivationBadge.setBadge2(wtMotivationBadge.getBadge2());
				}
				if (wtMotivationBadge.getBadge3() != null) {
					motivationBadge.setBadge3(wtMotivationBadge.getBadge3());
				}
				if (wtMotivationBadge.getBadge4() != null) {
					motivationBadge.setBadge4(wtMotivationBadge.getBadge4());
				}
				motivationBadge.setUpdatedAt(new Date());
				motivationBadge = repo.save(motivationBadge);
			}
		}
		return motivationBadge;
	}
}
