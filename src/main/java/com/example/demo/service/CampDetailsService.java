package com.example.demo.service;

import java.util.Set;

import com.example.demo.model.CampDetails;

public interface CampDetailsService {

	CampDetails createCamp(CampDetails campDetails);

	Set<CampDetails> getCampByTerm(Long termId);

	CampDetails getCampById(Long id);

	CampDetails updateCamp(CampDetails campDetails);

}
