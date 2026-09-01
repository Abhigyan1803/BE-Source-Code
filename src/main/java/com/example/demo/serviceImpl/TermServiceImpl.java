package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Term;
import com.example.demo.repository.TermRepo;
import com.example.demo.service.TermService;
import com.example.demo.util.ConstantMessage;

@Service
public class TermServiceImpl implements TermService {

	@Autowired
	TermRepo termRepo;

	@Override
	public Map<Object, Object> getAllTerms() {

		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<Term> termList = termRepo.findAllByStatusOrderBySeqNo(1);
			if (termList != null) {
				map.put(ConstantMessage.LIST, termList);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

	@Override
	public Term createTerm(Term term) {
		return termRepo.save(term);
	}

	@Override
	public Term updateTerm(Term term) {
		Term existingTerm = termRepo.findById(term.getId()).get();
		if (existingTerm != null) {
			existingTerm.setName(term.getName());
			existingTerm.setStatus(term.getStatus());
			existingTerm.setUpdateOn(new Date());

		}
		Term updated = termRepo.save(existingTerm);
		return updated;
	}

	@Override
	public Term getTermById(Long id) {
		Optional<Term> result = termRepo.findById(id);
		return result.get();
	}

	@Override
	public Map<Object, Object> getAllTermsNew() {
		HashMap<Object, Object> map = new HashMap<>();
		try {
			List<Term> termList = termRepo.getAllTermByIds();
			if (termList != null) {
				map.put(ConstantMessage.LIST, termList);
				map.put(ConstantMessage.status, ConstantMessage.SUCCESS_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_FOUND_SUCCESSFULLY);
				return map;
			} else {
				map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
				map.put(ConstantMessage.MESSAGE, ConstantMessage.RECORD_NOT_FOUND);
				return map;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			map.put(ConstantMessage.status, ConstantMessage.FAILED_STATUS);
			map.put(ConstantMessage.MESSAGE, ConstantMessage.TECHNICAL_ISSUE);
		}
		return map;
	}

}
