package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.IntellectualSkillsResult;
import com.example.demo.payload.IntellectualSkillsFilterPayload;
import com.example.demo.payload.IntellectualSkillsPayload;

@Service
public interface IntellectualSkillsResultService {

	IntellectualSkillsResult createIntellectualSkillsResult(IntellectualSkillsResult intellectualSkillsResult);

	IntellectualSkillsResult findByServiceIdAndTermId(String serviceId, Long termId);

	IntellectualSkillsResult updateIntellectualSkillsResult(IntellectualSkillsResult intellectualSkillsResult);

	List<IntellectualSkillsResult> findByServiceId(String serviceId);

	IntellectualSkillsPayload getCadetsByTermIdAndBattaionAndCompany(Long termId, String battalion, String company,
			String serviceId, Pageable pageable);

	String updateBulkIntellectualSkillsResult(List<IntellectualSkillsFilterPayload> intellectualSkillsPayloadList);

	IntellectualSkillsPayload getCadetsBySearch(String serviceId, Long termId, Pageable pageable);

}
