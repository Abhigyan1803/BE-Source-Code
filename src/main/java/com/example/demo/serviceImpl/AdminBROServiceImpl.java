package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BRO;
import com.example.demo.model.BRODocuments;
import com.example.demo.myexception.MyException;
import com.example.demo.repository.AdminBRORepo;
import com.example.demo.service.AdminBROService;
import com.example.demo.util.ConstantMessage;

@Service
public class AdminBROServiceImpl implements AdminBROService {

	@Autowired
	AdminBRORepo broRepo;

	@Override
	public BRO createBRO(BRO bro, Set<BRODocuments> brodocs) throws MyException {
		BRO exist = broRepo.findByBroNumber(bro.getBroNumber());
		if (exist != null) {
			throw new MyException(ConstantMessage.BRO_NUMBER_EXIST);
		} else {
			bro.setBroDocuments(brodocs);
			return broRepo.save(bro);
		}
	}

	@Override
	public List<BRO> getAllBROList(String status) {
		Integer[] deletedStatus = { 2 };
		if (status.equals("All")) {
			List<BRO> list = broRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
			return list;
		} else {
			Integer id = Integer.parseInt(status);
			List<BRO> list = broRepo.findByBattalianIdAndStatusNotInOrderByIdDesc(id, deletedStatus);
			return list;
		}
	}

	@Override
	public BRO getBROById(Long id) {
		Optional<BRO> list = broRepo.findById(id);
		return list.get();
	}

	@Override
	public BRO updateBRO(BRO bro, Set<BRODocuments> brodocs) {
		BRO br = null;
		Optional<BRO> b = broRepo.findById(bro.getId());
		if (b.isPresent()) {

			br = b.get();

			if (bro.getDate() != null) {

				br.setDate(bro.getDate());
			}
			if (bro.getStatus() != null) {
				br.setStatus(bro.getStatus());
			}

			if (bro.getBattalian() != null) {
				br.setBattalian(bro.getBattalian());
			}

			if (!brodocs.isEmpty()) {

				br.setBroDocuments(brodocs);
			}

			br.setUpdatedAt(new Date());

		}
		BRO list = broRepo.save(br);
//		BRO list = broRepo.saveAndFlush(br);
		return list;
	}

}
