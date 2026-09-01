package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AcademicExamination;
import com.example.demo.repository.AcademicExaminationRepository;
import com.example.demo.service.AcademicExaminationService;
import com.example.demo.util.FileUploader;

@Service
public class AcademinExaminationServiceImpl implements AcademicExaminationService {

	@Autowired
	private AcademicExaminationRepository repo;
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Override
	public AcademicExamination addAcademicExam(AcademicExamination academicExam, MultipartFile doc) {
		// TODO Auto-generated method stub
		if (doc != null && !doc.isEmpty()) {

			String filename = FileUploader.uploadProfileImage(doc, UploadDir);
			academicExam.setUrl(url + filename);
		}

		academicExam.setUploadedDate(new Date());
		return repo.save(academicExam);
	}

	@Override
	public AcademicExamination getById(Long id) {
		// TODO Auto-generated method stub
		Optional<AcademicExamination> result = repo.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public List<AcademicExamination> getAcademicExamList(String type, Long termId, Integer status) {
		List<AcademicExamination> list = null;
		if (status == 1) {
			list = repo.findAllByTypeAndTermIdAndStatusOrderByIdDesc(type, termId, status);
		} else {
			list = repo.findAllByTypeAndTermIdOrderByIdDesc(type, termId);
		}
		return list;
	}

	@Override
	public AcademicExamination updateAcademicExamination(AcademicExamination academicExam, MultipartFile doc) {
		// TODO Auto-generated method stub
		AcademicExamination academicEx = null;
		if (academicExam != null && academicExam.getId() != null && academicExam.getId() != 0) {

			Optional<AcademicExamination> ae = repo.findById(academicExam.getId());
			if (ae.isPresent()) {
				academicEx = ae.get();
				if (doc != null && !doc.isEmpty()) {
					String filename = FileUploader.uploadProfileImage(doc, UploadDir);
					academicEx.setUrl(url + filename);
				}

				if (academicExam.getName() != null) {

					academicEx.setName(academicExam.getName());
				}
				if (academicExam.getType() != null) {

					academicEx.setType(academicExam.getType());
				}

				if (academicExam.getStatus() != null) {

					academicEx.setStatus(academicExam.getStatus());
				}
				if (academicExam.getTermId() != null) {

					academicEx.setTermId(academicExam.getTermId());
				}
				if (academicExam.getUrl() != null) {

					academicEx.setUrl(academicExam.getUrl());
					academicEx.setUploadedDate(new Date());
				}

			}
			academicEx = repo.save(academicEx);
		}
		return academicEx;
	}
}
