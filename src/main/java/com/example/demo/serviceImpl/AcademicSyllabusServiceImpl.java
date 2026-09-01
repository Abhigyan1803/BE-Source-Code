package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AcademicSyllabus;
import com.example.demo.repository.AcademicSyllabusRepository;
import com.example.demo.service.AcademicSyllabusService;
import com.example.demo.util.FileUploader;

@Service
public class AcademicSyllabusServiceImpl implements AcademicSyllabusService {
	@Autowired
	private AcademicSyllabusRepository repo;
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Override
	public AcademicSyllabus addAcademicSyllabus(AcademicSyllabus academicSyllabus, MultipartFile doc) {
		// TODO Auto-generated method stub
		if (doc != null && !doc.isEmpty()) {

			String filename = FileUploader.uploadProfileImage(doc, UploadDir);
			academicSyllabus.setDoc(url + filename);
		}

		academicSyllabus.setCreatedAt(new Date());
		return repo.save(academicSyllabus);
	}

	@Override
	public AcademicSyllabus getById(Long id) {
		// TODO Auto-generated method stub
		Optional<AcademicSyllabus> result = repo.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public List<AcademicSyllabus> getByStatus(Integer status) {
		// TODO Auto-generated method stub
		List<AcademicSyllabus> result = null;
		if (status == 1) {
			result = repo.findAllByStatusOrderByIdDesc(status);
		} else {
			result = repo.findAllByOrderByIdDesc();
		}

		return result;
	}

	@Override
	public List<AcademicSyllabus> getAcademicSyllabusList(Long termId, String paper, String subject, Integer status) {
		// TODO Auto-generated method stub
		List<AcademicSyllabus> result = null;

		if (status == 1) {
			result = repo.findAllByTermIdAndPaperAndSubjectAndStatusOrderByIdDesc(termId, paper,subject, status);
		} else {
			result = repo.findAllByTermIdAndPaperAndSubjectOrderByIdDesc(termId, paper,subject);
		}

		return result;
	}

	@Override
	public AcademicSyllabus updateAcademicSyllabus(AcademicSyllabus academicSyllabus, MultipartFile doc) {
		AcademicSyllabus academicSylbs = null;
		if (academicSyllabus != null && academicSyllabus.getId() != null && academicSyllabus.getId() != 0) {

			Optional<AcademicSyllabus> aa = repo.findById(academicSyllabus.getId());
			if (aa.isPresent()) {
				academicSylbs = aa.get();
				if (doc != null && !doc.isEmpty()) {
					String filename = FileUploader.uploadProfileImage(doc, UploadDir);
					academicSylbs.setDoc(url + filename);
				}
				if (academicSyllabus.getName() != null) {

					academicSylbs.setName(academicSyllabus.getName());
				}

//				if (academicSyllabus.getDoc() != null) {
//
//					academicSylbs.setDoc(academicSyllabus.getDoc());
//				}
				if (academicSyllabus.getStatus() != null) {

					academicSylbs.setStatus(academicSyllabus.getStatus());
				}
				academicSyllabus.setUpdatedAt(new Date());

			}
			academicSylbs = repo.save(academicSylbs);
		}
		return academicSylbs;

	}
}
