package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Counselling;
import com.example.demo.repository.CounsellingRepo;
import com.example.demo.service.CounsellingService;

@Service
public class CounsellingServiceImpl implements CounsellingService {
	@Autowired
	public CounsellingRepo repo;

	@Override
	public List<Counselling> getCounsellingList(Integer status, String serviceId) {
		// TODO Auto-generated method stub
		return repo.findByStatusAndServiceId(status, serviceId);
	}

	@Override
	public List<Counselling> getCounsellingList(Long id) {
		// TODO Auto-generated method stub
		return repo.findAllById(id);
	}

	@Override
	public List<Counselling> addCounselling(List<Counselling> counsellingList) {
		List<Counselling> counsellingList1 = null;
		Set<Long> ids1 = new HashSet<Long>();
		Set<Long> ids2 = new HashSet<Long>();
		String serviceId = "";
		if (counsellingList != null) {
			counsellingList1 = new ArrayList<Counselling>();
			for (Counselling counselling : counsellingList) {
				serviceId = counselling.getServiceId();
				counselling.setStatus(1);
				Counselling result = repo.save(counselling);
				ids1.add(counselling.getId());
				counsellingList1.add(result);
			}
		}
		List<Counselling> counsellingList2 = repo.findByStatusAndServiceId(1, serviceId);
		if (counsellingList2.size() > 0) {
			for (Counselling counselling : counsellingList2) {
				ids2.add(counselling.getId());
			}
		}
		// for deleteing old records
		if (ids2.size() > 0) {
			for (Long id : ids2) {
				if (!ids1.contains(id)) {
					repo.deleteById(id);
				}
			}
		}
		return counsellingList1;
	}

}
