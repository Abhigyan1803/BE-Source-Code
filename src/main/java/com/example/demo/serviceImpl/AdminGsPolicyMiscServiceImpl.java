package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.GsPolicyMisc;
import com.example.demo.repository.AdminGsPolicyMiscRepo;
import com.example.demo.service.AdminGsPolicyMiscService;

@Service
public class AdminGsPolicyMiscServiceImpl implements AdminGsPolicyMiscService {

	@Autowired
	AdminGsPolicyMiscRepo miscRepo;

	@Override
	public GsPolicyMisc createMisc(GsPolicyMisc misc) {
		return miscRepo.save(misc);
	}

	@Override
	public List<GsPolicyMisc> getAllMiscList(Integer status) {
		Integer[] deletedStatus = { 2 };
		if (status < 2) {
			List<GsPolicyMisc> list = miscRepo.findByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
			return list;
		} else {
			List<GsPolicyMisc> list = miscRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
			return list;
		}
	}

	@Override
	public GsPolicyMisc getMiscById(Integer id) {
		Optional<GsPolicyMisc> list = miscRepo.findById(id);
		return list.get();
	}

	@Override
	public GsPolicyMisc updateMisc(GsPolicyMisc misc) {
		GsPolicyMisc mis = null;
		Optional<GsPolicyMisc> m = miscRepo.findById(misc.getId());
		if (m.isPresent()) {

			mis = m.get();

			if (StringUtils.isNotBlank(misc.getDoc())) {
				mis.setDoc(misc.getDoc());
			}

			if (misc.getName() != null) {

				mis.setName(misc.getName());
			}

			if (misc.getDescription() != null) {

				mis.setDescription(misc.getDescription());
			}

			if (misc.getStatus() != null) {

				mis.setStatus(misc.getStatus());
			}

			mis.setUpdatedAt(new Date());

		}
		GsPolicyMisc list = miscRepo.save(mis);
		return list;
	}

}
