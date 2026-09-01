package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DRILLSubject;
import com.example.demo.model.Term;
import com.example.demo.repository.DRILLSubjectRepo;
import com.example.demo.repository.TermRepo;
import com.example.demo.service.DRILLSubjectService;

@Service
public class DRILLSubjectServiceImpl implements DRILLSubjectService {

	@Autowired
	private DRILLSubjectRepo drillSubjectRepo;
	@Autowired
	private TermRepo termRepo;

	@Override
	public DRILLSubject createSubject(DRILLSubject drillSubject) {
		Long term = drillSubject.getTermId();
		if (term == 3) {
			drillSubject.setDrillType("DAT");
		} else {
			drillSubject.setDrillType("DST");
		}
		DRILLSubject drillSub = drillSubjectRepo.save(drillSubject);
		return drillSub;
	}

	@Override
	public List<DRILLSubject> getAllSubject() {
		List<DRILLSubject> drillSub = drillSubjectRepo.findAll();
		for (DRILLSubject ds : drillSub) {
			Optional<Term> term = termRepo.findById(ds.getTermId());
			ds.setTermName(term.get().getName());
		}
		return drillSub;
	}

	@Override
	public List<DRILLSubject> getAllSubjectByStatus(Integer status) {
		List<DRILLSubject> list = null;
		Integer[] deletedStatus = { 2 };
		if (status == 1) {
			list = drillSubjectRepo.findByStatusAndStatusNotIn(status, deletedStatus);
		} else {
			list = drillSubjectRepo.findAllByStatusNotIn(deletedStatus);
		}
		return list;

	}

	@Override
	public List<DRILLSubject> getAllSubjectByTermId(Long termid) {
		List<DRILLSubject> DrillSub = drillSubjectRepo.findByTermIdOrderById(termid);
		return DrillSub;
	}

	@Override
	public DRILLSubject updateSubject(DRILLSubject drillSubject) {
		DRILLSubject subject = null;
		Optional<DRILLSubject> sub = drillSubjectRepo.findById(drillSubject.getId());
		if (sub.isPresent()) {
			subject = sub.get();
			subject.setUpdatedAt(new Date());
			if (drillSubject.getStatus() != null) {
				subject.setStatus(drillSubject.getStatus());
			}
			if (drillSubject.getSubjectName() != null) {
				subject.setSubjectName(drillSubject.getSubjectName());
			}

			if (drillSubject.getTotalMarks() != null) {
				subject.setTotalMarks(drillSubject.getTotalMarks());
			}
			if (drillSubject.getTermId() != null) {
				subject.setTermId(drillSubject.getTermId());
			}
			subject = drillSubjectRepo.save(subject);
		}
		return subject;
	}

	@Override
	public DRILLSubject isSubjectExist(DRILLSubject drillSubject) {
		return drillSubjectRepo.findBySubjectNameAndTermId(drillSubject.getSubjectName(), drillSubject.getTermId());
	}

	@Override
	public DRILLSubject validateSubjectExist(DRILLSubject drillSubject) {
		if (drillSubject.getSubjectName() == null && drillSubject.getTermId() == null) {
			return null;
		}
		DRILLSubject ds = drillSubjectRepo.findBySubjectNameAndTermId(drillSubject.getSubjectName(),
				drillSubject.getTermId());
		if (ds != null && ds.getId() != drillSubject.getId()) {
			return ds;
		}
		return null;
	}

	@Override
	public List<DRILLSubject> getAllSubjectByTermIdAndStatus(Long termid, Integer status) {
		// TODO Auto-generated method stub
		List<DRILLSubject> DrillSub = drillSubjectRepo.findByTermIdAndStatus(termid, status);
		return DrillSub;
	}

	@Override
	public Optional<DRILLSubject> getSubjectById(Long id) {
		return drillSubjectRepo.findById(id);
	}

}
