package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.GSO2ServiceSubjectBMTResult;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.BmtFilterPayload;
import com.example.demo.payload.BmtPayload;
import com.example.demo.payload.EdserviceSubPayload;
import com.example.demo.payload.ServiceSubTermPayload;
import com.example.demo.service.GSO2ServiceSubjectBMTResultService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.ResponseMessage;

@CrossOrigin
@RestController
@RequestMapping("/api/gso2servicesubjectbmtcontroller")
public class GSO2ServiceSubjectBMTResultController {
	@Autowired
	GSO2ServiceSubjectBMTResultService gSO2ServiceSubjectBMTResultService;

	@PostMapping(value = "/add_GSO2ServiceSubjectBMT")
	public ResponseEntity<?> addGSO2ServiceSubjectBMT(@RequestBody GSO2ServiceSubjectBMTResult gSO2ServiceSubjectBMT)
			throws MyException {
		if (gSO2ServiceSubjectBMT != null) {
			GSO2ServiceSubjectBMTResult response = gSO2ServiceSubjectBMTResultService
					.createGSO2ServiceSubjectBMTResult(gSO2ServiceSubjectBMT);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid gso2 service subject bmt  Details ", HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/get_GSO2ServiceSubjectBMT")
	public ResponseEntity<?> getById(Long id) {
		GSO2ServiceSubjectBMTResult response = gSO2ServiceSubjectBMTResultService.getByid(id);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping("/getbytermId_subjecttype_assesmenttermType_serviceid_status")

	public ResponseEntity<?> getGSO2ServiceSubjectBMT(@RequestParam Long termId, @RequestParam String subjectType,
			@RequestParam String assesmentTermType, @RequestParam Integer status, @RequestParam String serviceId) {
		GSO2ServiceSubjectBMTResult response = gSO2ServiceSubjectBMTResultService.getGSO2ServiceSubjectBMTResult(termId,
				subjectType, assesmentTermType, status, serviceId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@PutMapping("/update_academic_subject")
	public ResponseEntity<?> updateGSO2ServiceSubjectBMT(
			@RequestBody GSO2ServiceSubjectBMTResult gSO2ServiceSubjectBMTResult) {
		GSO2ServiceSubjectBMTResult response = gSO2ServiceSubjectBMTResultService
				.updateGSO2ServiceSubjectBMTResult(gSO2ServiceSubjectBMTResult);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_UPDATED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}

	@GetMapping("/getbyserviceId_GSO2ServiceSubjectBMT")
	public ResponseEntity<?> getGSO2ServiceSubjectBMT(@RequestParam String serviceId) {
		List<GSO2ServiceSubjectBMTResult> response = gSO2ServiceSubjectBMTResultService
				.getGSO2ServiceSubjectBMTResult(serviceId);
		if (response != null) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_FOUND_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get_all_cadet_getbyserviceId_GSO2ServiceSubjectBMT_result_by_termId_battalion_company")
	public ResponseEntity<?> getCadetsByTermIdAndBattaionAndCompany(@RequestParam(required = false) Long termId,
			@RequestParam(required = false) String battalion, @RequestParam String serviceSubjectType,
			@RequestParam String assesmentTermType, @RequestParam(required = false) String company,
			@RequestParam(required = false) String serviceId, @RequestParam Integer pageNo,
			@RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		BmtPayload response = gSO2ServiceSubjectBMTResultService.getCadetsByTermIdAndBattaionAndCompany(termId,
				battalion, serviceSubjectType, assesmentTermType, company, serviceId, pageable);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@PutMapping(value = "/save_bulk_GSO2ServiceSubjectBMT_result")
	public ResponseEntity<?> updateBulkGSO2ServiceSubjectBMTResult(@RequestBody List<BmtFilterPayload> bmtPayloadList)
			throws MyException {
		String response = gSO2ServiceSubjectBMTResultService.updateBulkGSO2ServiceSubjectBMTResult(bmtPayloadList);
		if (response.equalsIgnoreCase("success")) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}

	@GetMapping(value = "/get_all_GSO2ServiceSubjectBMT_by_search")
	public ResponseEntity<?> getCadetsBySearch(@RequestParam Long termId,
			@RequestParam(required = false) String serviceId, @RequestParam String serviceSubjectType,
			@RequestParam String assesmentTermType, @RequestParam Integer pageNo, @RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		BmtPayload response = gSO2ServiceSubjectBMTResultService.getCadetsBySearch(termId, serviceId,
				serviceSubjectType, assesmentTermType, pageable);

		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_bmt1_bmt2_mrprac")
	public ResponseEntity<?> getBmt1Bmt2Mrprac(@RequestParam String serviceId, @RequestParam String resultType,
			@RequestParam String serviceSubjectType, @RequestParam(required = false) String assesmentTermType) {

//		ServiceSubTermPayload response = gSO2ServiceSubjectBMTResultService.findBmt1Bmt2Mrprac(serviceId, resultType,
//				serviceSubjectType, assesmentTermType);
		ServiceSubTermPayload response = gSO2ServiceSubjectBMTResultService.findBmt1Bmt2MrpracNew1(serviceId,
				resultType, serviceSubjectType);

		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}

	@PutMapping(value = "/update_bmt1_bmt2_mrprac")
	public ResponseEntity<?> updateBmt1Bmt2Mrprac(@RequestBody EdserviceSubPayload edServiceSubPayload) {

		String response = gSO2ServiceSubjectBMTResultService.updateBmt1Bmt2Mrprac(edServiceSubPayload);

		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}
	}
}
