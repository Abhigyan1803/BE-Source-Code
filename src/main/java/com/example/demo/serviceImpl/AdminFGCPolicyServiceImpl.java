package com.example.demo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.FGCPolicy;
import com.example.demo.repository.AdminFGCPolicyRepo;
import com.example.demo.service.AdminFGCPolicyService;

@Service
public class AdminFGCPolicyServiceImpl implements AdminFGCPolicyService {

	@Autowired
	AdminFGCPolicyRepo policyRepo;

	@Override
	public FGCPolicy createPolicy(FGCPolicy policy) {
		return policyRepo.save(policy);
	}

	@Override
	public List<FGCPolicy> getAllFGCPolicyList(Integer status) {
		Integer[] deletedStatus = { 2 };
		if (status < 2) {
			List<FGCPolicy> list = policyRepo.findByStatusAndStatusNotInOrderByIdDesc(status, deletedStatus);
			return list;
		} else {
			List<FGCPolicy> list = policyRepo.findAllByStatusNotInOrderByIdDesc(deletedStatus);
			return list;
		}
	}

	@Override
	public FGCPolicy getFGCPolicyById(Integer id) {
		Optional<FGCPolicy> list = policyRepo.findById(id);
		return list.get();
	}

	@Override
	public FGCPolicy updatePolicy(FGCPolicy policy) {
		FGCPolicy pol = null;
		Optional<FGCPolicy> pl = policyRepo.findById(policy.getId());
		if (pl.isPresent()) {

			pol = pl.get();

			if (StringUtils.isNotBlank(policy.getDoc())) {
				pol.setDoc(policy.getDoc());
			}

			if (policy.getName() != null) {

				pol.setName(policy.getName());
			}

			if (policy.getDescription() != null) {

				pol.setDescription(policy.getDescription());
			}

			if (policy.getStatus() != null) {

				pol.setStatus(policy.getStatus());
			}

			pol.setUpdatedAt(new Date());

		}
		FGCPolicy list = policyRepo.save(pol);
		return list;
	}
}
