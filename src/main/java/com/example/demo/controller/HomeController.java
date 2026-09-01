package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import com.example.demo.model.AcademicTerm;
import com.example.demo.model.Cadet;
import com.example.demo.model.HomeAboutEntries;
import com.example.demo.model.HomeAboutEntriesDetails;
import com.example.demo.model.HomeAboutUs;
import com.example.demo.myexception.MyException;
import com.example.demo.service.HomeService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@CrossOrigin
@RestController
@RequestMapping("/api/home_controller")
public class HomeController {

	@Autowired
	HomeService homeService;
	
//	@Value("${spring.url}")
//	private String url;
//
//	@Value("${spring.dir}")
//	private String UploadDir;
	
	@PostMapping(value = "/add_home_about")
	public ResponseEntity<?> addAbout(@RequestBody HomeAboutUs homeAboutUs) throws MyException {

//		HomeAboutUs about = homeService.checkType(homeAboutUs.getType());
//		if (about != null) {
//			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.SERVICE_ALREADY_EXIST, HttpStatus.OK, ""),
//					HttpStatus.OK);
//		}

		HomeAboutUs response = homeService.addAbout(homeAboutUs);
//		FileWritting.createLog((HttpServletRequest) request,
//				response.getId() + ",added," + "About," + ConstantMessage.RECORD_ADDED_SUCCESSFULLY + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);

	}
	
	@GetMapping("/get_home_about_list")
	public ResponseEntity<?> getHomeAboutListByType(@RequestParam(required = false) String type) {
		List<HomeAboutUs> response = homeService.getHomeAboutListByType(type);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PutMapping("/update_home_about")
	public ResponseEntity<?> updateHomeAbout(@RequestBody HomeAboutUs homeAboutUs) {
		HomeAboutUs response = homeService.updateHomeAbout(homeAboutUs);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_UPDATED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}
	
	//-----------------------------About Entries-----------------------------------------------------
	
	@PostMapping(value = "/add_about_entry")
	public ResponseEntity<?> addAboutEntry(@RequestBody HomeAboutEntries entries) throws MyException {
		
		HomeAboutEntries response = homeService.addAboutEntry(entries);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping("/get_about_entry_list")
	public ResponseEntity<?> getAboutEntryList() {
		List<HomeAboutEntries> response = homeService.getAboutEntryList();
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@GetMapping("/get_about_entry_by_id")
	public ResponseEntity<?> getAboutEntryById(Long id) {
		HomeAboutEntries response = homeService.getAboutEntryById(id);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PutMapping("/update_about_enrty")
	public ResponseEntity<?> updateAboutEntry(@RequestBody HomeAboutEntries entries) {
		HomeAboutEntries response = homeService.updateAboutEntry(entries);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_UPDATED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}
	//-----------------------------About Entries Description-----------------------------------------------------
	
	@PostMapping(value = "/add_about_entry_destails")
	public ResponseEntity<?> addAboutEntryDetails(@RequestBody HomeAboutEntriesDetails entriesDetails) throws MyException {
		
		HomeAboutEntriesDetails response = homeService.addAboutEntryDetails(entriesDetails);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);

	}
	
	@GetMapping("/get_about_entry_details_by_id")
	public ResponseEntity<?> getAboutEntryDetailsById(Long id) {
		HomeAboutEntriesDetails response = homeService.getAboutEntryDetailsById(id);
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping("/get_about_entry_details_list")
	public ResponseEntity<?> getAboutEntryDetailsList() {
		List<HomeAboutEntriesDetails> response = homeService.getAboutEntryDetailsList();
		if (response != null) {

			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}
	
	@PutMapping("/update_about_enrty_details")
	public ResponseEntity<?> updateAboutEntryDetails(@RequestBody HomeAboutEntriesDetails entriesDetails) {
		HomeAboutEntriesDetails response = homeService.updateAboutEntryDetails(entriesDetails);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_UPDATED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}
}
