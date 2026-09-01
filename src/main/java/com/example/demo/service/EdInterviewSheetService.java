package com.example.demo.service;

import com.example.demo.model.EdInterviewSheet;

public interface EdInterviewSheetService {

	EdInterviewSheet addEdInterviewSheet(EdInterviewSheet edInterviewSheet);

	EdInterviewSheet getByServiceId(String serviceId);

	EdInterviewSheet updateEdInterviewSheetService(EdInterviewSheet edInterviewSheet);

}
