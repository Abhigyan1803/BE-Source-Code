package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.GreyBook;
import com.example.demo.repository.GreyBookRepo;
import com.example.demo.service.GreyBookService;

@Service
public class GreyBookServiceImpl implements GreyBookService {

	@Autowired
	GreyBookRepo greyBookRepo;

	@Override
	public GreyBook addGreyBook(GreyBook request) {
		GreyBook greyBookNew = new GreyBook();
		try {
			greyBookNew = greyBookRepo.save(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return greyBookNew;
	}

	@Override
	public List<GreyBook> getAllGreyBookRecords(int status) {
		List<GreyBook> list = new ArrayList<>();
		Integer[] deletedStatus = { 2 };
		if (status == 1 || status == 0) {
			list = greyBookRepo.findByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
		} else {
			list = greyBookRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
		}
		return list;
	}

	@Override
	public GreyBook activeDeactiveGreyBookRecord(Long id, int status) {
		GreyBook greyBookNew = null;
		try {
			GreyBook greyBook = greyBookRepo.findById(id).get();
			if (greyBook != null) {
				greyBook.setStatus(status);
				greyBookNew = greyBookRepo.save(greyBook);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return greyBookNew;
	}

	@Override
	public GreyBook updateGreyBookRecord(GreyBook request) {
		GreyBook greyBookNew = null;
		try {
			GreyBook greyBook = greyBookRepo.findById(request.getId()).get();
			if (greyBook != null) {
				request.setCreatedAt(greyBook.getCreatedAt());
				request.setUpdatedOn(new Date());
				greyBookNew = greyBookRepo.save(request);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return greyBookNew;
	}

	@Override
	public GreyBook getGreyBookRecordById(Long id) {
		return greyBookRepo.findById(id).get();
	}

}
