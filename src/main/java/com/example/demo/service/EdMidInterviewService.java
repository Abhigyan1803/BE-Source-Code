package com.example.demo.service;

import java.util.List;

import com.example.demo.model.EdMidInterview;

public interface EdMidInterviewService {

	EdMidInterview addEdMidInterview(EdMidInterview edMidInterview);

	List<EdMidInterview> getByServiceId(String serviceId);

	EdMidInterview updateEdMidInterview(EdMidInterview edMidInterview);

	EdMidInterview getByServiceIdAndSubmittedBy(String serviceId, String submittedBy);

}
