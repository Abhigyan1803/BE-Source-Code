package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Adventure;

public interface AdventureService {

	Adventure addAdventure(Adventure adventure);

	Adventure getById(Long id);

	List<Adventure> getBystatus(Integer status);

	Adventure updateAdventure(Adventure adventure);

	Adventure getByServiceId(String serviceId);

	Adventure findByServiceIdAndTermId(String serviceId, Long termId);

}
