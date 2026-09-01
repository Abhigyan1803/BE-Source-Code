package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.OqSubjectDetails1;
import com.example.demo.repository.OqSubjectDetailsRepo1;
import com.example.demo.service.OqSubjectDetailsService1;

@Service
public class OqSubjectDetailsServiceImpl1 implements OqSubjectDetailsService1 {

	@Autowired
	private OqSubjectDetailsRepo1 oqSubjectDetailsRepo1;

	@Override
	public OqSubjectDetails1 createSubject(OqSubjectDetails1 oqSubjectDetails1) {
		OqSubjectDetails1 saveSubjectDetails = oqSubjectDetailsRepo1.save(oqSubjectDetails1);
		return saveSubjectDetails;
	}

	@Override
	public OqSubjectDetails1 getSubjectById(Long id) {
		Optional<OqSubjectDetails1> getSubjectDetails = oqSubjectDetailsRepo1.findById(id);
		return getSubjectDetails.get();
	}

	@Override
	public List<OqSubjectDetails1> getAllSubjectByStatus(Integer status) {
		List<OqSubjectDetails1> list = null;
		if (status == 1) {
			list = oqSubjectDetailsRepo1.findByStatus(status);
		} else {
			list = oqSubjectDetailsRepo1.findAll();
		}
		return list;
	}

	@Override
	public OqSubjectDetails1 updateSubject(OqSubjectDetails1 oqSubjectDetails1) {
		OqSubjectDetails1 subject = null;
		Optional<OqSubjectDetails1> sb = oqSubjectDetailsRepo1.findById(oqSubjectDetails1.getId());
		if (sb.isPresent()) {
			subject = sb.get();

			subject.setUpdatedAt(new Date());

			if (oqSubjectDetails1.getSubjectName() != null) {
				subject.setSubjectName(oqSubjectDetails1.getSubjectName());
			}

			if (oqSubjectDetails1.getStatus() != null) {
				subject.setStatus(oqSubjectDetails1.getStatus());
			}

			if (oqSubjectDetails1.getTotalMarksBnCdr() != null) {
				subject.setTotalMarksBnCdr(oqSubjectDetails1.getTotalMarksBnCdr());
			}

			if (oqSubjectDetails1.getTotalMarksPlCdr() != null) {
				subject.setTotalMarksPlCdr(oqSubjectDetails1.getTotalMarksPlCdr());
			}

			if (oqSubjectDetails1.getTotalMarksCoyCdr() != null) {
				subject.setTotalMarksCoyCdr(oqSubjectDetails1.getTotalMarksCoyCdr());
			}

		}
		return oqSubjectDetailsRepo1.save(subject);
	}

	@Override
	public OqSubjectDetails1 findBySubject(String subjectName) {
		OqSubjectDetails1 oqSubjectDtls = oqSubjectDetailsRepo1.findBySubjectName(subjectName);
		return oqSubjectDtls;
	}

	@Override
	public OqSubjectDetails1 validateSubject(OqSubjectDetails1 oqSubjectDetails1) {
		OqSubjectDetails1 sd = oqSubjectDetailsRepo1.findBySubjectName(oqSubjectDetails1.getSubjectName());
		if (sd != null && sd.getId() != oqSubjectDetails1.getId()) {
			return sd;
		}
		return null;
	}

}
