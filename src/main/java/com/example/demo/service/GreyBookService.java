package com.example.demo.service;

import java.util.List;

import com.example.demo.model.GreyBook;

public interface GreyBookService {

	GreyBook addGreyBook(GreyBook request);

	List<GreyBook> getAllGreyBookRecords(int status);

	GreyBook activeDeactiveGreyBookRecord(Long id, int status);

	GreyBook updateGreyBookRecord(GreyBook request);

	GreyBook getGreyBookRecordById(Long id);
	
	
}
