package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.SportsResult;
import com.example.demo.payload.SportsFilterPayload;
import com.example.demo.payload.SportsPayload;

@Service
public interface SportsResultService {

	SportsResult createSportsResult(SportsResult sportsResult);

	// SportsResult findByServiceIdAndTermId(String serviceId, Long termId);

	List<SportsResult> findByServiceId(String serviceId);

	SportsResult updateSportsResult(SportsResult sportsResult);

	SportsResult findByServiceIdAndTermIdAndTermSession(String serviceId, Long termId, String termSession);

	SportsPayload getCadetsSportsByTermIdAndBattaionAndCompany(Long termId, String termSession, String battalion,
			String company, String serviceId, Pageable pageable);

	SportsPayload getCadetsBySearch(Long termId, String termSession, String serviceId, Pageable pageable);

	String updateBulkSportsResult(List<SportsFilterPayload> sportsPayloadList);

}
