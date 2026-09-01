package com.example.demo.service;

import java.util.List;

import com.example.demo.model.EdBeginningInterview;

public interface EdBeginningInterviewService {

	EdBeginningInterview addEdBeginningInterview(EdBeginningInterview edBeginningInterview);

	List<EdBeginningInterview> getByServiceId(String serviceId);

	EdBeginningInterview updateEdBeginningInterview(EdBeginningInterview edBeginningInterview);

	EdBeginningInterview getByServiceIdAndSubmittedBy(String serviceId, String submittedBy);

}
