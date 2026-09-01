package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CampSubjectDetails;
import com.example.demo.repository.CampSubjectDetailsRepo;
import com.example.demo.service.CampSubjectDetailsService;

@Service
public class CampSubjectDetailsServiceImpl implements CampSubjectDetailsService {

	@Autowired
	private CampSubjectDetailsRepo campSubjectDetailsRepo;

	@Override
	public CampSubjectDetails createSubject(CampSubjectDetails campSubjectDetails) {
		// TODO Auto-generated method stub
		CampSubjectDetails saveSubjectDetails = campSubjectDetailsRepo.save(campSubjectDetails);
		return saveSubjectDetails;
	}

	@Override
	public CampSubjectDetails getSubjectById(Long id) {
		// TODO Auto-generated method stub
		Optional<CampSubjectDetails> getSubjectDetails = campSubjectDetailsRepo.findById(id);
		return getSubjectDetails.get();
	}

	@Override
	public List<CampSubjectDetails> getAllSubjectByStatus(Integer status) {
		List<CampSubjectDetails> list = null;
		Integer[] deletedStatus = { 2 };
		if (status == 1) {
			list = campSubjectDetailsRepo.findByStatusAndStatusNotIn(status, deletedStatus);
		} else {
			list = campSubjectDetailsRepo.findAllByStatusNotIn(deletedStatus);
		}
		return list;
	}

	@Override
	public CampSubjectDetails updateSubject(CampSubjectDetails campSubjectDetails) {
		CampSubjectDetails subject = null;
		Optional<CampSubjectDetails> sb = campSubjectDetailsRepo.findById(campSubjectDetails.getId());
		if (sb.isPresent()) {
			subject = sb.get();

			subject.setUpdatedAt(campSubjectDetails.getUpdatedAt());

			if (campSubjectDetails.getSubjectName() != null) {
				subject.setSubjectName(campSubjectDetails.getSubjectName());
			}

			if (campSubjectDetails.getStatus() != null) {
				subject.setStatus(campSubjectDetails.getStatus());
			}

			if (campSubjectDetails.getTotalMarks() != null) {
				subject.setTotalMarks(campSubjectDetails.getTotalMarks());
			}

		}
		return campSubjectDetailsRepo.save(subject);
	}

	@Override
	public CampSubjectDetails findbySubject(String subjectName) {
		// TODO Auto-generated method stub
		CampSubjectDetails CampSubjectDetails = campSubjectDetailsRepo.findBySubjectName(subjectName);
		return CampSubjectDetails;
	}

	@Override
	public CampSubjectDetails validateSubjectExist(CampSubjectDetails campSubjectDetails) {
		CampSubjectDetails sd = campSubjectDetailsRepo.findBySubjectName(campSubjectDetails.getSubjectName());
		if (sd != null && sd.getId() != campSubjectDetails.getId()) {
			return sd;
		}
		return null;
	}

	@Override
	public List<CampSubjectDetails> getAllSubjectByStatusOrderByIdDesc(Integer status) {
		List<CampSubjectDetails> list = null;
		if (status == 1) {
			list = campSubjectDetailsRepo.findByStatusOrderByIdDesc(status);
		} else {
			list = campSubjectDetailsRepo.findAll();
		}
		return list;
	}

}
