package com.example.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AcademicFiles;
import com.example.demo.repository.AcademicFilesRepo;
import com.example.demo.service.AdminAcademicService;
import com.example.demo.util.FileUploader;

@Service
public class AdminAcademicServiceImpl implements AdminAcademicService {

	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AcademicFilesRepo academicFilesRepo;

	@Override
	public AcademicFiles addAcademicFiles(MultipartFile docfile) {
		AcademicFiles academicFiles = null;
		System.out.println("inside addAcademicFiles serviceImpl");
		if (docfile != null && !docfile.isEmpty()) {
			System.out.println("url=>" + url);
			System.out.println("UploadDir=>" + UploadDir);
			String filename = FileUploader.uploadProfileImage(docfile, UploadDir);
			if (filename != "") {
				System.out.println("Img uploaded on server name as=>" + filename);
				academicFiles = new AcademicFiles();
				academicFiles.setUrl(url + filename);
				academicFiles.setInUsed(false);
				System.out.println("Before link save=>" + academicFiles.getUrl());
				academicFiles = academicFilesRepo.save(academicFiles);
			}

		}
		return academicFiles;
	}

}
