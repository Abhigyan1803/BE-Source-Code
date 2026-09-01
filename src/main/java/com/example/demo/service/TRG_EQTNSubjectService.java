package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.TRG_EQTNSubject;

@Service
public interface TRG_EQTNSubjectService {

	// public List<TRG_EQTNSubject> getBystatus(Integer status);

	public TRG_EQTNSubject getSubjectById(Long subjectId);

	public List<TRG_EQTNSubject> getByStatusAndTermId(Integer status, Long termId);

}
