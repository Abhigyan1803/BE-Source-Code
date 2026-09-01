package com.example.demo.service;

import com.example.demo.model.SportsSubjectResult;

public interface SportsSubjectResultService {

	SportsSubjectResult createSubResult(SportsSubjectResult sportsSubResult);

	SportsSubjectResult updateSubResult(SportsSubjectResult sportsSubjectResult);

	SportsSubjectResult getSubResultById(Long id);

}
