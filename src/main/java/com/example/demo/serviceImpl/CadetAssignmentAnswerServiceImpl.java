package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cadet;
import com.example.demo.model.CadetAssignmentAnswer;
import com.example.demo.repository.AdminCadetRepo;
import com.example.demo.repository.CadetAssignmentAnswerRepository;
import com.example.demo.service.CadetAssignmentAnswerService;

@Service
public class CadetAssignmentAnswerServiceImpl implements CadetAssignmentAnswerService {
	@Autowired
	private CadetAssignmentAnswerRepository repo;
	@Autowired
	AdminCadetRepo cadetRepo;

	@Override
	public CadetAssignmentAnswer addCadetAssignmentAnswer(CadetAssignmentAnswer cadetAssignmentAnswer) {
		// TODO Auto-generated method stub
		cadetAssignmentAnswer.setCreatedAt(new Date());
		return repo.save(cadetAssignmentAnswer);
	}

	@Override
	public CadetAssignmentAnswer getById(Long id) {
		// TODO Auto-generated method stub
		Optional<CadetAssignmentAnswer> result = repo.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public List<CadetAssignmentAnswer> getCadetAssignmentAnswerByServiceId(String serviceId, Integer status) {
		// TODO Auto-generated method stub
		List<CadetAssignmentAnswer> list = null;
		if (status == 1) {
			list = repo.findAllByServiceIdAndStatusOrderByIdDesc(serviceId, status);
		} else {
			list = repo.findAllByServiceIdOrderByIdDesc(serviceId);
		}
		if (list.size() == 0) {
			list = null;
		}
		return list;
	}

	@Override
	public List<CadetAssignmentAnswer> getCadetAssignmentAnswerByAcdAsnId(Long acdAsnId, Integer status) {
		// TODO Auto-generated method stub
		List<CadetAssignmentAnswer> list = null;
		if (status == 1) {
			list = repo.findAllByAcdAsnIdAndStatusOrderByIdDesc(acdAsnId, status);
		} else {
			list = repo.findAllByAcdAsnIdOrderByIdDesc(acdAsnId);
		}
		for (CadetAssignmentAnswer caa : list) {
			Integer[] deletedStatus = { 2 };
		//	Cadet cad = cadetRepo.findByServiceId(caa.getServiceId());
			Cadet cad = cadetRepo.findByServiceIdAndStatusNotIn(caa.getServiceId(),deletedStatus);
			if (cad != null) {
				caa.setCadetName(cad.getName());
			}
		}
		if (list.size() == 0) {
			list = null;
		}
		return list;
	}

	@Override
	public List<CadetAssignmentAnswer> getByStatus(Integer status) {
		// TODO Auto-generated method stub

		List<CadetAssignmentAnswer> list = null;
		if (status == 1) {
			list = repo.findAllByStatusOrderByIdDesc(status);
		} else {
			list = null;
		}
		for (CadetAssignmentAnswer caa : list) {

			Cadet cad = cadetRepo.findByServiceId(caa.getServiceId());
			if (cad != null) {
				caa.setCadetName(cad.getName());
			}

		}
		if (list.size() == 0) {
			list = null;
		}
		return list;
	}

	@Override
	public CadetAssignmentAnswer updateCadetAssignmentAnswer(CadetAssignmentAnswer cadetAssignmentAnswer) {
		// TODO Auto-generated method stub
		CadetAssignmentAnswer cadetAssign = null;
		if (cadetAssignmentAnswer != null && cadetAssignmentAnswer.getId() != null
				&& cadetAssignmentAnswer.getId() != 0) {

			Optional<CadetAssignmentAnswer> caa = repo.findById(cadetAssignmentAnswer.getId());
			if (caa.isPresent()) {

				cadetAssign = caa.get();

				if (cadetAssignmentAnswer.getInstructorId() != null) {

					cadetAssign.setInstructorId(cadetAssignmentAnswer.getInstructorId());
				}
				if (cadetAssignmentAnswer.getInstructorMark() != null) {

					cadetAssign.setInstructorMark(cadetAssignmentAnswer.getInstructorMark());
				}
				if (cadetAssignmentAnswer.getStatus() != null) {

					cadetAssign.setStatus(cadetAssignmentAnswer.getStatus());
				}
				if (cadetAssignmentAnswer.getAnswer() != null) {

					cadetAssign.setAnswer(cadetAssignmentAnswer.getAnswer());
				}
				if (cadetAssignmentAnswer.getRemark() != null) {

					cadetAssign.setRemark(cadetAssignmentAnswer.getRemark());
				}

				cadetAssign.setUpdatedAt(new Date());

			}
			cadetAssign = repo.save(cadetAssign);
		}
		return cadetAssign;

	}

	@Override
	public CadetAssignmentAnswer getByAcdAsnIdAndServiceId(Long acdAsnId, String serviceId) {
		Optional<CadetAssignmentAnswer> result = repo.findByAcdAsnIdAndServiceId(acdAsnId, serviceId);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}
}
