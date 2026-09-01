package com.example.demo.service;


import com.example.demo.model.OqSubjectResult;

public interface OqSubjectResultService {

	OqSubjectResult createSubResult(OqSubjectResult OqSubjectResult);

	OqSubjectResult getSubResultById(Long id);

	OqSubjectResult updateSubResult(OqSubjectResult oqSubjectResult);

}
