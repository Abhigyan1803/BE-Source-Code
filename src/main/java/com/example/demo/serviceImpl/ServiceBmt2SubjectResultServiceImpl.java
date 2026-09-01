package com.example.demo.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ServiceBmt2SubjectResult;
import com.example.demo.repository.ServiceBmt2SubjectResultRepository;
import com.example.demo.service.ServiceBmt2SubjectResultService;

@Service
public class ServiceBmt2SubjectResultServiceImpl implements ServiceBmt2SubjectResultService {

	@Autowired
	private ServiceBmt2SubjectResultRepository serviceBmt2SubjectResultRepository;

	@Override
	public ServiceBmt2SubjectResult createSubResult(ServiceBmt2SubjectResult serviceBmt2SubRslt) {
		// TODO Auto-generated method stub
		return serviceBmt2SubjectResultRepository.save(serviceBmt2SubRslt);
	}

	@Override
	public ServiceBmt2SubjectResult getSubResultById(Long id) {
		// TODO Auto-generated method stub
		Optional<ServiceBmt2SubjectResult> getServiceBmt2SubjectResult = serviceBmt2SubjectResultRepository
				.findById(id);
		return getServiceBmt2SubjectResult.get();
	}

	@Override
	public ServiceBmt2SubjectResult updateSubResult(ServiceBmt2SubjectResult serviceBmt2SubjectResult) {
		// TODO Auto-generated method stub
		ServiceBmt2SubjectResult serviceBmt2SubResult = null;
		// TODO Auto-generated method stub
		if (serviceBmt2SubjectResult != null) {
			Optional<ServiceBmt2SubjectResult> getServiceBmt2SubResult = serviceBmt2SubjectResultRepository
					.findById(serviceBmt2SubjectResult.getId());
			serviceBmt2SubResult = getServiceBmt2SubResult.get();
			serviceBmt2SubResult.setObtainedMarks(serviceBmt2SubjectResult.getObtainedMarks());
			serviceBmt2SubResult.setSubjectId(serviceBmt2SubjectResult.getSubjectId());
			serviceBmt2SubResult.setStatus(serviceBmt2SubjectResult.getStatus());
			serviceBmt2SubResult.setTermId(serviceBmt2SubjectResult.getTermId());
			serviceBmt2SubResult.setTotalMarks(serviceBmt2SubjectResult.getTotalMarks());
			// serviceBmt2SubResult.setUpdatedAt(serviceBmt2SubjectResult.getUpdatedAt());
		}

		return serviceBmt2SubjectResultRepository.save(serviceBmt2SubjectResult);

	}
}
