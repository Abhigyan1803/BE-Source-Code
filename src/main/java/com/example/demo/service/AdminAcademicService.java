package com.example.demo.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.AcademicFiles;

public interface AdminAcademicService {

	AcademicFiles addAcademicFiles(MultipartFile file);
	
	//GCBoard updateGCBoard(GCBoard details , MultipartFile file);
	
	//GCBoard viewById(Long id);
	
	//List<GCBoard> getList(int status);
	
	//GCBoard changeStatus(Long id , int status);
	
}
