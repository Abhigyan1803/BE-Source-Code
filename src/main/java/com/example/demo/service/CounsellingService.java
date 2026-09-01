package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Counselling;

public interface CounsellingService {

	List<Counselling> getCounsellingList(Integer status, String serviceId);

	List<Counselling> getCounsellingList(Long id);

	List<Counselling> addCounselling(List<Counselling> counselling);



}
