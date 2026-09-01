package com.example.demo.service;

import java.util.List;

import com.example.demo.model.AcademyParadeState;

public interface AdminAcademyParadeStateService {

	AcademyParadeState createParadeState(AcademyParadeState paradeState);

	List<AcademyParadeState> getAllParadeStateList(Integer status);

	AcademyParadeState getParadeStateById(Integer id);

	AcademyParadeState updateParadeState(AcademyParadeState paradeState);

}
