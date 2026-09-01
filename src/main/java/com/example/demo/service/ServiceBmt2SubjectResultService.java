package com.example.demo.service;

import com.example.demo.model.ServiceBmt2SubjectResult;

public interface ServiceBmt2SubjectResultService {

	ServiceBmt2SubjectResult createSubResult(ServiceBmt2SubjectResult serviceBmt2SubRslt);

	ServiceBmt2SubjectResult getSubResultById(Long id);

	ServiceBmt2SubjectResult updateSubResult(ServiceBmt2SubjectResult serviceBmt2SubjectResult);

}
