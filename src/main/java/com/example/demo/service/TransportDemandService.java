package com.example.demo.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.payload.AddTransportDemad;
import com.example.demo.payload.GetDataOnlyById;

public interface TransportDemandService {

//	Map<Object, Object> getAllTransportDemand(PaginationPayLoad request);
	
	Map<Object, Object> getAllTransportDemand();

	Map<Object, Object> getDetailsByOnlyById(GetDataOnlyById request);

	Map<Object, Object> activeDeActiveTransaport(Long id, int status,ServletRequest servletRequest);

	Map<Object, Object> addTransportDemand(MultipartFile file,AddTransportDemad request,ServletRequest servletRequest);

	Map<Object, Object> updateTransportDemand(MultipartFile file,Long id,String name,String description,int status,ServletRequest servletRequest);
	
}
