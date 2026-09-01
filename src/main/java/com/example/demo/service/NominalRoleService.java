package com.example.demo.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.payload.AddSopDetails;
import com.example.demo.payload.GetDataOnlyById;

public interface NominalRoleService {

	
	Map<Object, Object> getDetailsByOnlyById(GetDataOnlyById request);

	Map<Object, Object> addNominalRole(MultipartFile file,AddSopDetails request,ServletRequest servletRequest);

//	Map<Object, Object> getAllNominalRole(PaginationPayLoad request);
	
	Map<Object, Object> getAllNominalRole();

	Map<Object, Object> activeDeActiveNominal(Long id, int status,ServletRequest servletRequest);

	Map<Object, Object> updateNominal(MultipartFile file,Long id,String name,String description,int status,ServletRequest servletRequest);

	
}
