package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Cadet;
import com.example.demo.model.HomeAboutEntries;
import com.example.demo.model.HomeAboutEntriesDetails;
import com.example.demo.model.HomeAboutUs;

public interface HomeService {

	HomeAboutUs addAbout(HomeAboutUs homeAboutUs);

	List<HomeAboutUs> getHomeAboutListByType(String type);

	HomeAboutUs updateHomeAbout(HomeAboutUs homeAboutUs);
	
	//-----------------------------About Entries-----------------------------------------------------
	
	HomeAboutEntries addAboutEntry(HomeAboutEntries entries);

	List<HomeAboutEntries> getAboutEntryList();

	HomeAboutEntries updateAboutEntry(HomeAboutEntries entries);
	
	HomeAboutEntries getAboutEntryById(Long id);
	
	//-----------------------------About Entries Description-----------------------------------------------------

	HomeAboutEntriesDetails addAboutEntryDetails(HomeAboutEntriesDetails entriesDetails);

	List<HomeAboutEntriesDetails> getAboutEntryDetailsList();

	HomeAboutEntriesDetails updateAboutEntryDetails(HomeAboutEntriesDetails entriesDetails);

	HomeAboutEntriesDetails getAboutEntryDetailsById(Long id);

	
	

}
