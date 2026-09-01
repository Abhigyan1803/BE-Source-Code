package com.example.demo.service;

import com.example.demo.model.EdSpecialInterview;

public interface EdSpecialInterviewService {
	EdSpecialInterview addEdSpecialInterview(EdSpecialInterview edSpecialInterview);

	EdSpecialInterview getByServiceId(String serviceId);

	EdSpecialInterview updateEdSpecialInterview(EdSpecialInterview edMidInterview);

}
