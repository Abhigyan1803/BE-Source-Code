package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.DRILLSubject;

public interface DRILLSubjectService {

	DRILLSubject createSubject(DRILLSubject drillSubject);

	List<DRILLSubject> getAllSubject();

	List<DRILLSubject> getAllSubjectByStatus(Integer status);

	List<DRILLSubject> getAllSubjectByTermId(Long termid);

	DRILLSubject updateSubject(DRILLSubject drillSubject);

	DRILLSubject isSubjectExist(DRILLSubject drillSubject);

	DRILLSubject validateSubjectExist(DRILLSubject drillSubject);

	List<DRILLSubject> getAllSubjectByTermIdAndStatus(Long termid, Integer status);

	Optional<DRILLSubject> getSubjectById(Long id);

}
