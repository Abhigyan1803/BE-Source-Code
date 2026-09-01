package com.example.demo.service;

import java.util.List;

import com.example.demo.model.FGCPolicy;

public interface AdminFGCPolicyService {

	FGCPolicy createPolicy(FGCPolicy policy);

	List<FGCPolicy> getAllFGCPolicyList(Integer status);

	FGCPolicy getFGCPolicyById(Integer id);

	FGCPolicy updatePolicy(FGCPolicy policy);

}
