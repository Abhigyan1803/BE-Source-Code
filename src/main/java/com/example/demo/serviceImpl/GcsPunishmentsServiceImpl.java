package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.GcsPunishments;
import com.example.demo.repository.GcsPunishmentsRepo;
import com.example.demo.service.GcsPunishmentsService;

@Service
public class GcsPunishmentsServiceImpl implements GcsPunishmentsService {
	@Autowired
	GcsPunishmentsRepo repo;

	@Override
	public GcsPunishments addGcspunshiments(GcsPunishments gcsPunishments) {
		// TODO Auto-generated method stub
		return repo.save(gcsPunishments);
	}

	@Override
	public GcsPunishments updateGcsPunishments(GcsPunishments gcsPunishments) {
		// TODO Auto-generated method stub
		GcsPunishments result = null;
		if (gcsPunishments != null && gcsPunishments.getId() != null && gcsPunishments.getId() != 0) {
			Optional<GcsPunishments> gcsPunish = repo.findById(gcsPunishments.getId());
			if (gcsPunish.isPresent()) {
				result = gcsPunish.get();
				if (result != null) {

					if (gcsPunishments.getDate() != null) {
						result.setDate(gcsPunishments.getDate());
					}

					if (gcsPunishments.getAwardedBy() != null) {
						result.setAwardedBy(gcsPunishments.getAwardedBy());
					}

//					if (gcsPunishments.getDate() != null) {
//						result.setDate(gcsPunishments.getDate());
//					}
					if (gcsPunishments.getOffence() != null) {
						result.setOffence(gcsPunishments.getOffence());
					}
					if (gcsPunishments.getPunshmentAwarded() != null) {
						result.setPunshmentAwarded(gcsPunishments.getPunshmentAwarded());
					}
					if (gcsPunishments.getPoints() != null) {
						result.setPoints(gcsPunishments.getPoints());
					}
					if (gcsPunishments.getStatus() != null) {
						result.setStatus(gcsPunishments.getStatus());
					}
					result = repo.save(result);
				}
			}
		}

		return result;
	}

	@Override
	public List<GcsPunishments> getGcsPunishmentsList(String serviceId, Long termId, Integer status) {
		List<GcsPunishments> result = null;
		Integer[] deletedStatus = { 2 };
		if (termId != null) {
			if (status != null && status == 1) {
				System.out.println("case1");
				result = repo.findAllByServiceIdAndTermIdAndStatusAndStatusNotIn(serviceId, termId, 1, deletedStatus);
			} else {
				System.out.println("case2");
				result = repo.findAllByServiceIdAndTermIdAndStatusNotIn(serviceId, termId, deletedStatus);
			}

		} else {
			if (status != null && status == 1) {
				System.out.println("case3");
				result = repo.findAllByServiceIdAndStatusAndStatusNotIn(serviceId, 1, deletedStatus);
			} else {
				System.out.println("case4");
				result = repo.findAllByServiceIdAndStatusNotIn(serviceId, deletedStatus);
			}
		}

		return result;
	}

}
