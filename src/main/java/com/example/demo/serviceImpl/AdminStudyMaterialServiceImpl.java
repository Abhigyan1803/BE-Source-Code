package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.StudyMaterial;
import com.example.demo.repository.AdminStudyMaterialRepo;
import com.example.demo.service.AdminStudyMaterialService;

@Service
public class AdminStudyMaterialServiceImpl implements AdminStudyMaterialService {
	@Autowired
	AdminStudyMaterialRepo studyMaterialRepo;

	@Override
	public StudyMaterial createStudyMaterial(StudyMaterial studyMaterial) {
		// TODO Auto-generated method stub
		return studyMaterialRepo.save(studyMaterial);
	}

	@Override
	public List<StudyMaterial> getAllStudyMaterialList(String type, Long termId) {
		List<StudyMaterial> list = null;
		if (termId != null && termId != 0) {
			list = studyMaterialRepo.findAllByStudyMaterialTypeAndTermIdOrderByIdDesc(type, termId);
		} else {
			list = studyMaterialRepo.findAllByStudyMaterialTypeOrderByIdDesc(type);
		}
		return list;
	}

	@Override
	public StudyMaterial getStudyMaterialById(Long id) {
		// TODO Auto-generated method stub
		Optional<StudyMaterial> list = studyMaterialRepo.findById(id);
		return list.get();
	}

	@Override
	public StudyMaterial updateStudyMaterial(StudyMaterial studyMaterial) {
		// TODO Auto-generated method stub
		StudyMaterial stuMaterial = null;
		Optional<StudyMaterial> stu = studyMaterialRepo.findById(studyMaterial.getId());
		if (stu.isPresent()) {
			stuMaterial = stu.get();
			if (StringUtils.isNotBlank(studyMaterial.getDoc())) {
				stuMaterial.setDoc(studyMaterial.getDoc());
			}
			if (studyMaterial.getName() != null) {
				stuMaterial.setName(studyMaterial.getName());
			}
			if (studyMaterial.getStudyMaterialType() != null) {
				stuMaterial.setStudyMaterialType(studyMaterial.getStudyMaterialType());
			}
			if (studyMaterial.getDate() != null) {
				stuMaterial.setDate(studyMaterial.getDate());
			}
			if (studyMaterial.getDescription() != null) {
				stuMaterial.setDescription(studyMaterial.getDescription());
			}
			if (studyMaterial.getStatus() != null) {
				stuMaterial.setStatus(studyMaterial.getStatus());
			}

			if (studyMaterial.getTermId() != null) {
				stuMaterial.setTermId(studyMaterial.getTermId());
			}

			stuMaterial.setUpdatedAt(new Date());
		}
		StudyMaterial list = studyMaterialRepo.save(stuMaterial);
		return list;
	}

}
