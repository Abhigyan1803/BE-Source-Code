package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Resignation;

public interface AdminResignationService {

	Resignation createResignation(Resignation resignation);

	List<Resignation> getAllResignationList(Integer status);

	Resignation getResignationById(Integer id);

	Resignation updateResignation(Resignation resignation);

}
