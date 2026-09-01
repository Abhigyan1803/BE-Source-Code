package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CentralLibrary;
import com.example.demo.repository.CentralLibraryRepo;
import com.example.demo.service.CentralLibraryService;

@Service
public class CentralLibraryServiceImpl implements CentralLibraryService {

	@Autowired
	CentralLibraryRepo centralLibraryRepo;

	@Override
	public CentralLibrary addCentralLibrary(CentralLibrary request) {
		CentralLibrary centralLibraryNew = null;
		try {
			centralLibraryNew = centralLibraryRepo.save(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return centralLibraryNew;
	}

	@Override
	public List<CentralLibrary> getAllCentralLibraryRecord() {
		Integer[] deletedStatus = { 2 };
		return centralLibraryRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
	}

	@Override
	public CentralLibrary activeDeactiveLibrary(Long id, int status) {
		CentralLibrary centralLibraryNew = null;
		try {
			CentralLibrary centralLibrary = centralLibraryRepo.findById(id).get();
			if (centralLibrary != null) {
				centralLibrary.setStatus(status);
				centralLibraryNew = centralLibraryRepo.save(centralLibrary);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return centralLibraryNew;
	}

	@Override
	public CentralLibrary updateLibraryRecord(CentralLibrary request) {
		CentralLibrary centralLibraryNew = null;
		try {
			CentralLibrary centralLibrary = centralLibraryRepo.findById(request.getId()).get();
			if (centralLibrary != null) {
				request.setCreatedAt(centralLibrary.getCreatedAt());
				request.setUpdatedOn(new Date());
				centralLibraryNew = centralLibraryRepo.save(request);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return centralLibraryNew;
	}

	@Override
	public CentralLibrary getLibraryRecordById(Long id) {
		return centralLibraryRepo.findById(id).get();
	}

	@Override
	public List<CentralLibrary> getAllCentralLibraryRecordHomePage() {
		return centralLibraryRepo.findByStatusOrderByIdDesc(1);
	}

}
