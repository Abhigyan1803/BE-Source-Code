package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.IntellectualSkillsSubjectResult;

@Service
public interface IntellectualSkillsSubjectResultService {

	IntellectualSkillsSubjectResult createSubResult(IntellectualSkillsSubjectResult intellectualSkillsSubResult);

	IntellectualSkillsSubjectResult getSubResultById(Long id);

	IntellectualSkillsSubjectResult updateSubResult(IntellectualSkillsSubjectResult intellectualSkillsSubjectResult);

}
