package com.example.demo.service;

import java.util.List;

import com.example.demo.model.SpecialOccasion;

public interface SpecialOccasionService {

	SpecialOccasion addOccasion(SpecialOccasion occasion);

	List<SpecialOccasion> getAllOccasiomList(Integer status);

	SpecialOccasion getOccasionById(Long id);

	SpecialOccasion updateOccasion(SpecialOccasion occasion);

	List<SpecialOccasion> getWeekOccasion();

}
