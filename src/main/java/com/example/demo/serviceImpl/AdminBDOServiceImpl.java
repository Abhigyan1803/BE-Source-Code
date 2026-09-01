package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BDO;
import com.example.demo.model.BDODocuments;
import com.example.demo.repository.AdminBDORepo;
import com.example.demo.service.AdminBDOService;

@Service
public class AdminBDOServiceImpl implements AdminBDOService {

	@Autowired
	AdminBDORepo bdoRepo;

	@Override
	public BDO createBDO(BDO bdo, Set<BDODocuments> bdodocs) {
		bdo.setBdoDocuments(bdodocs);
		return bdoRepo.save(bdo);
	}

	@Override
	public List<BDO> getAllBDOList(String status) {
//		List<BDO> list = bdoRepo.findAllByStatus(ConstantVar.ONE);
		Integer[] deletedStatus = { 2 };
		if (status.equals("All")) {
			List<BDO> list = bdoRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
			return list;
		} else {
			Integer id = Integer.parseInt(status);
			List<BDO> list = bdoRepo.findByBattalianIdAndStatusNotInOrderByIdDesc(id, deletedStatus);
			return list;
		}
	}

	@Override
	public BDO getBDOById(Long id) {
		Optional<BDO> list = bdoRepo.findById(id);
		return list.get();
	}

	@Override
	public BDO updateBDO(BDO bdo, Set<BDODocuments> bdodocs) {
		BDO bd = null;
		Optional<BDO> b = bdoRepo.findById(bdo.getId());
		if (b.isPresent()) {

			bd = b.get();

			if (bdo.getDate() != null) {

				bd.setDate(bdo.getDate());
			}

			if (bdo.getStatus() != null) {
				bd.setStatus(bdo.getStatus());
			}

			if (bdo.getBattalian() != null) {
				bd.setBattalian(bdo.getBattalian());
			}
			if (!bdodocs.isEmpty()) {

				bd.setBdoDocuments(bdodocs);
			}

			bd.setUpdatedAt(new Date());

		}
		BDO list = bdoRepo.save(bd);
		return list;
	}

}
