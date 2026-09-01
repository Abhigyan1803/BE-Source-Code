package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CentralLibrary;

public interface CentralLibraryService {

	CentralLibrary addCentralLibrary(CentralLibrary request);

	List<CentralLibrary> getAllCentralLibraryRecord();

	CentralLibrary getLibraryRecordById(Long id);

	CentralLibrary updateLibraryRecord(CentralLibrary request);

	CentralLibrary activeDeactiveLibrary(Long id, int status);

	List<CentralLibrary> getAllCentralLibraryRecordHomePage();

}
