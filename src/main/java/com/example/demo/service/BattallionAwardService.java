package com.example.demo.service;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.BattalionAward;
import com.example.demo.payload.AddBatalionAwardPayLoad;
import com.example.demo.payload.GetDataOnlyById;

public interface BattallionAwardService {

	Map<Object, Object> addBatallioAward(AddBatalionAwardPayLoad request,MultipartFile img,ServletRequest servletRequest);

	Map<Object, Object> getAllAwards( int battalionId , int status);

	Map<Object, Object> updateAwards(BattalionAward request,MultipartFile img,ServletRequest servletRequest);

	Map<Object, Object> ActiveDeactiveAward(Long id , int status , ServletRequest servletRequest);

	Map<Object, Object> getDetailsByOnlyById(GetDataOnlyById request);
	
	
}
