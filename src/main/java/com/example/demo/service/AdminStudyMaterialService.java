package com.example.demo.service;

import java.util.List;

import com.example.demo.model.StudyMaterial;

public interface AdminStudyMaterialService {

	StudyMaterial createStudyMaterial(StudyMaterial studyMaterial);

	List<StudyMaterial> getAllStudyMaterialList(String type, Long termId);

	StudyMaterial getStudyMaterialById(Long id);

	StudyMaterial updateStudyMaterial(StudyMaterial syllbus);

}
