package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ObsnSheet;
import com.example.demo.repository.ObsnSheetRepo;
import com.example.demo.service.ObsnSheetService;

@Service
public class ObsnSheetServiceImpl implements ObsnSheetService {
	@Autowired
	public ObsnSheetRepo repo;

	@Override
	public List<ObsnSheet> getObsnSheetList(Integer status, String serviceId) {
		// TODO Auto-generated method stub
		return repo.findByStatusAndServiceId(status, serviceId);
	}

	@Override
	public List<ObsnSheet> getObsnSheetList(Long id) {
		// TODO Auto-generated method stub
		return repo.findAllById(id);
	}

	@Override
	public List<ObsnSheet> addObsnSheet(List<ObsnSheet> obsnSheetList) {
		List<ObsnSheet> obsnSheetResultList = null;
		Set<Long> ids1 = new HashSet<Long>();
		Set<Long> ids2 = new HashSet<Long>();
		String serviceId = "";

		if (obsnSheetList != null) {
			obsnSheetResultList = new ArrayList<ObsnSheet>();
			for (ObsnSheet obsnSheet : obsnSheetList) {
				serviceId = obsnSheet.getServiceId();
				obsnSheet.setStatus(1);
				ObsnSheet result = repo.save(obsnSheet);
				ids1.add(obsnSheet.getId());
				obsnSheetResultList.add(result);
			}
		}

		List<ObsnSheet> obsnSheetList1 = repo.findByStatusAndServiceId(1, serviceId);
		if (obsnSheetList1.size() > 0) {
			for (ObsnSheet obsnSheet : obsnSheetList1) {
				ids2.add(obsnSheet.getId());
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

		return obsnSheetResultList;
	}

}
