package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.GC_Entitle;
import com.example.demo.repository.GC_EntitleServiceRepo;
import com.example.demo.service.GC_EntitleService;

@Service
public class GC_EntitleServiceImpl implements GC_EntitleService {
	@Autowired
	GC_EntitleServiceRepo GC_entitleServiceRepo;

	@Override
	public List<GC_Entitle> getGC_Entitle(String type, Long cadetId) {
		List<GC_Entitle> list = null;
		if (cadetId != null && cadetId != 0) {
			list = GC_entitleServiceRepo.findBytypeAndCadetId(type, cadetId);
		} else {
			list = GC_entitleServiceRepo.findBytype(type);
		}
		if (list != null && list.size() != 0) {
			return list;
		} else {
			return null;
		}

	}

	@Override
	public GC_Entitle saveGC_Entitle(GC_Entitle entitle) {
		GC_Entitle entitle1 = GC_entitleServiceRepo.save(entitle);
		return entitle1;
	}

}
