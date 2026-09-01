package com.example.demo.service;

import java.util.List;

import com.example.demo.model.EdInitialInterview;

public interface EdInitialInterviewService {

	EdInitialInterview addEdInitialInterview(EdInitialInterview edInitialInterview);

	List<EdInitialInterview> getByServiceId(String serviceId);

	EdInitialInterview updateEdInitialInterview(EdInitialInterview edInitialInterview);

	EdInitialInterview getByServiceIdAndSubmittedBy(String serviceId, String submittedBy);

}
