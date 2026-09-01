package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Cadet;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.AdminCadetPayload;
import com.example.demo.payload.CadetPayLoad;
import com.example.demo.payload.EdCadetPayload;
import com.example.demo.service.AdminCadetService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.FileWritting;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/cadet")
public class AdminCadetController {
	@Value("${spring.url}")
	private String url;

	@Value("${spring.dir}")
	private String UploadDir;

	@Autowired
	AdminCadetService cadetService;

	@PostMapping(value = "/add-cadet")
	public ResponseEntity<?> addCadet(Cadet cadet,
			@RequestParam("IMA_JoiningDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date IMA_JoiningDate,
			@RequestParam("dob") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob,
			@RequestParam(value = "file") List<MultipartFile> file, ServletRequest request) throws MyException {

		Cadet cad = cadetService.checkServiceId(cadet.getServiceId());
		if (cad != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.SERVICE_ALREADY_EXIST, HttpStatus.OK, ""),
					HttpStatus.OK);
		}

		Cadet response = cadetService.createCadetDetail(cadet, file, url, UploadDir);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "Cadet," + ConstantMessage.CADET_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.CADET_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	@GetMapping(value = "/get-cadet-list-old")
	public ResponseEntity<?> cadetListOld(@RequestParam String status) {
		// Page<Cadet> list =
		// cadetService.getAllCadetList(FileUploader.paginationData(pageNo, pageSize));
		List<Cadet> list = cadetService.getAllCadetList(status);
//		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
//		AdminCadetPayload response = cadetService.getAllCadetListWithFilterAndPagination(status, termId, battalion,
//				company, pageable);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get-cadet-list")
	public ResponseEntity<?> cadetList(@RequestParam String status, @RequestParam(required = false) Long termId,
			@RequestParam(required = false) String battalion, @RequestParam(required = false) String company,
			@RequestParam Integer pageNo, @RequestParam Integer pageSize) {
		// Page<Cadet> list =
		// cadetService.getAllCadetList(FileUploader.paginationData(pageNo, pageSize));
		// List<Cadet> list = cadetService.getAllCadetList(status);
		if(status.contains("2") || status.contains("3")) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		AdminCadetPayload response = cadetService.getAllCadetListWithFilterAndPagination(status, termId, battalion,
				company, pageable);
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get_all_admin_cadet_by_search")
	public ResponseEntity<?> getAdminCadetsBySearch(@RequestParam String status,
			@RequestParam(required = false) String serviceId, @RequestParam Integer pageNo,
			@RequestParam Integer pageSize) {
		
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		if(status.contains("2") || status.contains("3")) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
		AdminCadetPayload response = cadetService.getAdminCadetsBySearch(status, serviceId, pageable);

		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
		

	}

	@GetMapping(value = "/get-cadet")
	public ResponseEntity<?> getCadetByID(@RequestParam Long id) {
		Cadet list = cadetService.getCadetById(id);
		if(list!=null) {
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, list),
				HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@PatchMapping(value = "/update-cadet")
	public ResponseEntity<?> updateCadet(Cadet cadet,
			// @RequestParam(value = "IMA_JoiningDate", required = false)
			// @DateTimeFormat(pattern = "yyyy-MM-dd") Date IMA_JoiningDate,
			@RequestParam(value = "dob", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob,
			@RequestParam(value = "file", required = false) List<MultipartFile> file, ServletRequest request)
			throws MyException {
		Cadet cad = cadetService.checkServiceId(cadet.getServiceId());
		if (cad != null && !cad.getId().equals(cadet.getId())) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.SERVICE_ALREADY_EXIST, HttpStatus.OK, ""),
					HttpStatus.OK);
		}
		Cadet response = cadetService.updateCadetDetail(cadet, file, url, UploadDir);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",updated," + "Cadet," + ConstantMessage.CADET_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.CADET_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	/////
	@PatchMapping(value = "/update-cadet-new")
	public ResponseEntity<?> updateCadetNew(Cadet cadet,
			@RequestParam(value = "dob", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob,
			@RequestParam(value = "profileImg", required = false) MultipartFile profileImg,
			@RequestParam(value = "aadharImg", required = false) MultipartFile aadharImg,
			@RequestParam(value = "panImg1", required = false) MultipartFile panImg1,
			@RequestParam(value = "tenthImg", required = false) MultipartFile tenthImg,
			@RequestParam(value = "twelfthImg", required = false) MultipartFile twelfthImg,
			@RequestParam(value = "graduationImg", required = false) MultipartFile graduationImg,
			@RequestParam(value = "postGraduationImg", required = false) MultipartFile postGraduationImg,
			ServletRequest request) throws MyException {
		Cadet cad = cadetService.checkServiceId(cadet.getServiceId());
		if (cad != null && cad.getId() != cadet.getId()) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.SERVICE_ALREADY_EXIST, HttpStatus.OK, ""),
					HttpStatus.OK);
		}
		List<MultipartFile> file = new ArrayList<MultipartFile>();
		if (profileImg != null && !profileImg.isEmpty()) {
			file.add(profileImg);
		} else {
			file.add(null);
		}
		if (aadharImg != null && !aadharImg.isEmpty()) {
			file.add(aadharImg);
		} else {
			file.add(null);
		}
		if (panImg1 != null && !panImg1.isEmpty()) {
			file.add(panImg1);
		} else {
			file.add(null);
		}
		if (tenthImg != null && !tenthImg.isEmpty()) {
			file.add(tenthImg);
		} else {
			file.add(null);
		}
		if (twelfthImg != null && !twelfthImg.isEmpty()) {
			file.add(twelfthImg);
		} else {
			file.add(null);
		}
		if (graduationImg != null && !graduationImg.isEmpty()) {
			file.add(graduationImg);
		} else {
			file.add(null);
		}
		if (postGraduationImg != null && !postGraduationImg.isEmpty()) {
			file.add(postGraduationImg);
		} else {
			file.add(null);
		}

		Cadet response = cadetService.updateCadetDetail(cadet, file, url, UploadDir);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",updated," + "Cadet," + ConstantMessage.CADET_UPDATED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.CADET_UPDATED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

	/////
	@GetMapping(value = "/get-cadet-by-serviceId")
	public ResponseEntity<?> getCadetByServiceId(@RequestParam String serviceId) {
		Cadet cad = cadetService.getCadetByServiceId(serviceId);
		if (cad != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, cad),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, cad),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get-cadet-by-serviceId_termId")
	public ResponseEntity<?> getCadetByServiceId(@RequestParam String serviceId, @RequestParam Long termId) {
		Cadet cad = cadetService.getCadetByServiceIdAndTermId(serviceId, termId);
		if (cad != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, cad),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, cad),
					HttpStatus.OK);
		}

	}

	@PostMapping(value = "/update-cadet-by-serviceId")
	public ResponseEntity<?> updateCadetByServiceId(@RequestBody CadetPayLoad cadetPayload) {
		Cadet response = cadetService.updateCadetByServiceId(cadetPayload);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.CADET_UPDATED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_all_cadet_by_termId_battalion_company")
	public ResponseEntity<?> getCadetsByTermIdAndBattaionAndCompany(@RequestParam(required = false) Long termId,
			@RequestParam(required = false) String battalion, @RequestParam(required = false) String company,
			@RequestParam(required = false) Integer pageNo, @RequestParam(required = false) Integer pageSize) {
		EdCadetPayload response = null;
		if (pageNo != null && pageSize != null) {
			Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
			response = cadetService.getCadetsByTermIdAndBattaionAndCompany(termId, battalion, company, pageable);
		} else {
			response = cadetService.getCadetsByTermIdAndBattaionAndCompanyWithoutPagination(termId, battalion, company);
		}

		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_all_cadet_by_search")
	public ResponseEntity<?> getCadetsBySearch(@RequestParam(required = false) String serviceId,
			@RequestParam(required = false) Integer pageNo, @RequestParam(required = false) Integer pageSize) {
		EdCadetPayload response = null;
		if (pageNo != null && pageSize != null) {
			Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
			response = cadetService.getCadetsBySearch(serviceId, pageable);
		} else {
			response = cadetService.getCadetsBySearchWithoutPagination(serviceId);
		}

		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@PostMapping(value = "update-bulk-cadet-term")
	public ResponseEntity<?> updateCadetTermById(@RequestBody List<Cadet> cadetList) {
		String response = cadetService.updateCadetTermById(cadetList);
		if (response == "success") {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}
////////////////////////////////////////////////////////////////////////////////////////////////
	@PostMapping(value = "/add-cadet-new")
	public ResponseEntity<?> addCadetNew(@RequestBody Cadet cadet,ServletRequest request) throws MyException {

		Cadet cad = cadetService.checkServiceId(cadet.getServiceId());
		if (cad != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.SERVICE_ALREADY_EXIST, HttpStatus.OK, ""),
					HttpStatus.OK);
		}

		Cadet response = cadetService.createCadetDetailNew(cadet, url, UploadDir);
		FileWritting.createLog((HttpServletRequest) request,
				response.getId() + ",added," + "Cadet," + ConstantMessage.CADET_ADDED + "," + new Date());
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.CADET_ADDED, HttpStatus.OK, response),
				HttpStatus.OK);

	}

}
