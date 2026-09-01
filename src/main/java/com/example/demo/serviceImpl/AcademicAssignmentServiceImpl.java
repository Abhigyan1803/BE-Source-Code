package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AcademicAssignment;
import com.example.demo.model.CadetAssignmentAnswer;
import com.example.demo.repository.AcademicAssignmentRepository;
import com.example.demo.repository.CadetAssignmentAnswerRepository;
import com.example.demo.service.AcademicAssignmentService;

@Service
public class AcademicAssignmentServiceImpl implements AcademicAssignmentService {
	@Autowired
	private AcademicAssignmentRepository repo;

	@Autowired
	private CadetAssignmentAnswerRepository cadetAssignmentAnswerRepository;

	@Override
	public AcademicAssignment addAcademicAssignment(AcademicAssignment academicAssignment) {
		// TODO Auto-generated method stub
		academicAssignment.setCreatedAt(new Date());
		return repo.save(academicAssignment);
	}

	@Override
	public AcademicAssignment getById(Long id) {
		// TODO Auto-generated method stub
		Optional<AcademicAssignment> result = repo.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public List<AcademicAssignment> getByTermId(Long termId, Integer status) {
		// TODO Auto-generated method stub
		List<AcademicAssignment> result = repo.findByTermIdAndStatusOrderByIdDesc(termId, status);

		return result;
	}

	@Override
	public List<AcademicAssignment> getAcademicAssignment(String paper, String assignmentType, Long termId,
			Integer status) {
		// TODO Auto-generated method stub
		List<AcademicAssignment> list = null;
		if (status == 1) {
			list = repo.findAllByPaperAndAssignmentTypeAndTermIdAndStatusOrderByIdDesc(paper, assignmentType, termId,
					status);
		} else {
			list = repo.findAllByPaperAndAssignmentTypeAndTermIdOrderByIdDesc(paper, assignmentType, termId);
		}
		if (list.size() == 0) {
			list = null;
		}
		return list;
	}

	@Override
	public List<AcademicAssignment> getAcademicAssignmentAndAnswer(String paper, String assignmentType, Long termId,
			Integer status, String serviceId) {
// TODO Auto-generated method stub
		List<AcademicAssignment> list = null;
		if (status == 1) {
			list = repo.findAllByPaperAndAssignmentTypeAndTermIdAndStatusOrderByIdDesc(paper, assignmentType, termId,
					status);
		} else {
			list = repo.findAllByPaperAndAssignmentTypeAndTermIdOrderByIdDesc(paper, assignmentType, termId);
		}
		if (list.size() == 0) {
			list = null;
		}

		if (list != null && list.size() > 0) {
			for (AcademicAssignment cadetAssign : list) {
				if (cadetAssign != null) {
					Optional<CadetAssignmentAnswer> result = cadetAssignmentAnswerRepository
							.findByAcdAsnIdAndServiceId(cadetAssign.getId(), serviceId);
					if (result.isPresent()) {
						CadetAssignmentAnswer cadetAssignmentAnswer = result.get();
						cadetAssign.setAnswer(cadetAssignmentAnswer.getAnswer());
						cadetAssign.setRemark(cadetAssignmentAnswer.getRemark());
						cadetAssign.setIsAnswered(true);
					} else {
						cadetAssign.setIsAnswered(false);
					}
				}
			}
		}
		return list;

	}

	@Override
	public AcademicAssignment updateAcademicAssignment(AcademicAssignment academicAssignment) {
		// TODO Auto-generated method stub
		AcademicAssignment academicAssign = null;
		if (academicAssignment != null && academicAssignment.getId() != null && academicAssignment.getId() != 0) {

			Optional<AcademicAssignment> aa = repo.findById(academicAssignment.getId());
			if (aa.isPresent()) {

				academicAssign = aa.get();

				if (academicAssignment.getAssignment() != null) {

					academicAssign.setAssignment(academicAssignment.getAssignment());
				}
				if (academicAssignment.getQuestion() != null) {

					academicAssign.setQuestion(academicAssignment.getQuestion());
				}
				if (academicAssignment.getStatus() != null) {

					academicAssign.setStatus(academicAssignment.getStatus());
				}

				academicAssign.setUpdatedAt(new Date());

			}
			academicAssign = repo.save(academicAssign);
		}
		return academicAssign;
	}
}
