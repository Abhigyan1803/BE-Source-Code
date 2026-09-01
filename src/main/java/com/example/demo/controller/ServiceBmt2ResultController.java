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

import com.example.demo.model.ServiceBmt2Result;
import com.example.demo.model.ServiceBmt2Subject;
import com.example.demo.model.ServiceBmt2SubjectResult;
import com.example.demo.myexception.MyException;
import com.example.demo.payload.ServiceBmt2FilterPayload;
import com.example.demo.payload.ServiceBmt2Payload;
import com.example.demo.service.ServiceBmt2ResultService;
import com.example.demo.service.ServiceBmt2SubjectResultService;
import com.example.demo.service.ServiceBmt2SubjectService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.FileUploader;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("/api/service_bmt_2_result_controller")
public class ServiceBmt2ResultController {

	@Autowired
	private ServiceBmt2SubjectService serviceBmt2SubjectService;

	@Autowired
	private ServiceBmt2SubjectResultService serviceBmt2SubjectResultServic;

	@Autowired
	private ServiceBmt2ResultService serviceBmt2ResultService;

	@PostMapping(value = "/add_service_bmt_2_result")
	public ResponseEntity<?> addServiceBmt2Result(@RequestBody ServiceBmt2Result serviceBmt2Result) throws MyException {
		if (serviceBmt2Result != null) {

			List<ServiceBmt2SubjectResult> serviceBmt2SubjectResult = serviceBmt2Result.getServiceBmt2SubjectResult();
			if (serviceBmt2SubjectResult != null) {
				for (ServiceBmt2SubjectResult serviceBmt2SubRslt : serviceBmt2SubjectResult) {
					serviceBmt2SubjectResultServic.createSubResult(serviceBmt2SubRslt);
				}
			} else {
				return new ResponseEntity<>("Invalid leadership subject Details ", HttpStatus.NO_CONTENT);
			}
			ServiceBmt2Result response = serviceBmt2ResultService.createServiceBmt2Result(serviceBmt2Result);
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid service bmt 2 result Details ", HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/get_service_bmt_2_result_check_service_id_term_id")
	public ResponseEntity<?> getServiceBmt2ResultById(@RequestParam String serviceId, @RequestParam Long termId) {
		ServiceBmt2Result response = serviceBmt2ResultService.findByServiceIdAndTermId(serviceId, termId);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			List<ServiceBmt2Subject> result = serviceBmt2SubjectService.getByStatusAndTermId(1, termId);
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ADD, HttpStatus.OK, result), HttpStatus.OK);
		}

	}

	@PutMapping(value = "/update-service_bmt_2_result")
	public ResponseEntity<?> updateServiceBmt2Result(@RequestBody ServiceBmt2Result serviceBmt2Result)
			throws MyException {
		ServiceBmt2Result response = serviceBmt2ResultService.updateServiceBmt2Result(serviceBmt2Result);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
				HttpStatus.OK);
	}

	@GetMapping(value = "/get_all_service_bmt_2_result_by_termId_battalion_company")
	public ResponseEntity<?> getCadetsByTermIdAndBattaionAndCompany(@RequestParam(required = false) Long termId,
			@RequestParam(required = false) String battalion, @RequestParam(required = false) String company,
			@RequestParam(required = false) String serviceId, @RequestParam Integer pageNo,
			@RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		ServiceBmt2Payload response = serviceBmt2ResultService.getCadetsByTermIdAndBattaionAndCompany(termId, battalion,
				company, serviceId, pageable);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

	@PutMapping(value = "/save_bulk_service_bmt_2_result")
	public ResponseEntity<?> updateBulkServiceBmt2Result(
			@RequestBody List<ServiceBmt2FilterPayload> serviceBmt2FilterPayload) throws MyException {
		String response = serviceBmt2ResultService.updateBulkServiceBmt2Result(serviceBmt2FilterPayload);
		if (response.equalsIgnoreCase("success")) {
			return new ResponseEntity<>(
					new ResponseMessage(ConstantMessage.RECORD_UPDATED_SUCCESSFULLY, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.FAILED_TO_UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get_all_service_bmt_2_result_by_search")
	public ResponseEntity<?> getCadetsBySearch(@RequestParam Long termId,
			@RequestParam(required = false) String serviceId, @RequestParam Integer pageNo,
			@RequestParam Integer pageSize) {
		Pageable pageable = FileUploader.paginationData(pageNo, pageSize);
		ServiceBmt2Payload response = serviceBmt2ResultService.getCadetsBySearch(termId, serviceId, pageable);

		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.OK_MESSAGE, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORDS_NOT_FOUND, HttpStatus.OK, null),
					HttpStatus.OK);
		}

	}

}
