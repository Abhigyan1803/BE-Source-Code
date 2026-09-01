package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AcademicTerm;
import com.example.demo.repository.AcademicTermRepository;
import com.example.demo.service.AcademicTermService;

@Service
public class AcademicTermServiceImpl implements AcademicTermService {

	@Autowired
	public AcademicTermRepository repo;

	@Override
	public AcademicTerm addAcademicTerm(AcademicTerm academic) {
		// TODO Auto-generated method stub
		academic.setStatus(1);  //Akash V1 09/08/2023
		academic.setCreatedAt(new Date());
		return repo.save(academic);
	}

	@Override
	public AcademicTerm getById(Long id) {
		// TODO Auto-generated method stub
		Integer  status= 1;  //for Active data status =1 //Akash V1 09/08/2023
		Optional<AcademicTerm> result = repo.findByAcademicTermIdAndStatus(id,status);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
//	public List<AcademicTerm> getAcademicTermList(String paper,  String subjectName) {
	public List<AcademicTerm> getAcademicTermList(String paper, Long termId, String subjectName) {
		List<AcademicTerm> result = null;
		Integer [] status= {2};  //for Delete status =2 //Akash V1 09/08/2023
		if (termId != null && termId != 0) {
	//		result = repo.findAllByPaperAndTermIdAndSubjectNameOrderByAcademicTermIdDesc(paper, termId, subjectName);
			result = repo.findAllByPaperAndTermIdAndSubjectNameAndStatusNotInOrderByAcademicTermIdDesc(paper, termId, subjectName,status);
		}
		else {
	//	result = repo.findAllByPaperAndSubjectNameOrderByAcademicTermIdDesc(paper, subjectName);
			result = repo.findAllByPaperAndSubjectNameAndStatusNotInOrderByAcademicTermIdDesc(paper, subjectName,status);
		}
		return result;
	}

	@Override
	public List<AcademicTerm> getAcademicTermListByTermIdAndSubject(String paper, Long termId, String subjectName) {
		// TODO Auto-generated method stub
		List<AcademicTerm> result = null;
		result = repo.findAllByPaperAndTermIdAndSubjectNameOrderByAcademicTermIdDesc(paper, termId, subjectName);
//		result = repo.findAllByPaperAndTermIdAndSubjectNameOrderByAcademicTermIdDesc(paper, subjectName);
		return result;
	}

	@Override
	public AcademicTerm updateAcademicTerm(AcademicTerm academic) {
		// TODO Auto-generated method stub
		AcademicTerm result = null;
		if (academic != null && academic.getAcademicTermId() != null && academic.getAcademicTermId() != 0) {

			Optional<AcademicTerm> at = repo.findById(academic.getAcademicTermId());
			if (at.isPresent()) {
				academic.setUpdatedAt(new Date());
				result = repo.save(academic);
			}
		}
		return result;
	}
}