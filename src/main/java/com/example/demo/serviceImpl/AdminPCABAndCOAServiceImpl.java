package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PCABAndCOA;
import com.example.demo.repository.AdminPCABAndCOARepo;
import com.example.demo.service.AdminPCABAndCOAService;

@Service
public class AdminPCABAndCOAServiceImpl implements AdminPCABAndCOAService {

	@Autowired
	AdminPCABAndCOARepo pcabRepo;

	@Override
	public PCABAndCOA createPCABAndCOA(PCABAndCOA pcab) {
		return pcabRepo.save(pcab);
	}

	@Override
	public List<PCABAndCOA> getAllPCABAndCOAList(Integer status) {
		if (status < 2) {
			List<PCABAndCOA> list = pcabRepo.findByStatusOrderByIdDesc(status);
			return list;
		}

		else {
			List<PCABAndCOA> list = pcabRepo.findAllByOrderByIdDesc();
			return list;
		}
	}

	@Override
	public PCABAndCOA getPCABAndCOAById(Integer id) {
		Optional<PCABAndCOA> list = pcabRepo.findById(id);
		return list.get();
	}

	@Override
	public PCABAndCOA updatePCABAndCOA(PCABAndCOA pcab) {
		PCABAndCOA coa = null;
		Optional<PCABAndCOA> par = pcabRepo.findById(pcab.getId());
		if (par.isPresent()) {

			coa = par.get();

			if (StringUtils.isNotBlank(pcab.getDoc())) {
				coa.setDoc(pcab.getDoc());
			}

			if (pcab.getName() != null) {

				coa.setName(pcab.getName());
			}

			if (pcab.getDescription() != null) {

				coa.setDescription(pcab.getDescription());
			}

			if (pcab.getStatus() != null) {

				coa.setStatus(pcab.getStatus());
			}

			coa.setUpdatedAt(new Date());

		}
		PCABAndCOA list = pcabRepo.save(coa);
		return list;
	}

}
