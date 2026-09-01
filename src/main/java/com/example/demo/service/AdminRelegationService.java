package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Relegation;

public interface AdminRelegationService {

	Relegation createRelegation(Relegation relegation);

	List<Relegation> getAllRelegationList(Integer status);

	Relegation getRelegationById(Integer id);

	Relegation updateRelegation(Relegation relegation);

}
