package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CourtCase;

public interface AdminCourtCaseService {

	CourtCase createCourtCase(CourtCase courtCase);

	List<CourtCase> getAllCourtCaseList(Integer status);

	CourtCase getCourtCaseById(Integer id);

	CourtCase updateCourtCase(CourtCase courtCase);

}
