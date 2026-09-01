package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.model.EdAssessmentOqFinal;
import com.example.demo.myexception.MyException;
import com.example.demo.service.EdAssessmentOqFinalService;
import com.example.demo.util.ConstantMessage;
import com.example.demo.util.ResponseMessage;

@RestController
@CrossOrigin
@RequestMapping("api/ed_assessment_oq_final_controller")
public class EdAssessmentOqFinalController {

	@Autowired
	private EdAssessmentOqFinalService service;

	@PostMapping(value = "/ed_assessment_oq_final")
	public ResponseEntity<?> addEdAssessmentOqFinal(@RequestBody EdAssessmentOqFinal edAssessmentOqFinal)
			throws MyException {
		EdAssessmentOqFinal result = service.findByServiceIdAndTermId(edAssessmentOqFinal.getServiceId(),
				edAssessmentOqFinal.getTermId());
		if (result != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_ALREADY_EXIST, HttpStatus.OK, null),
					HttpStatus.OK);
		}
		EdAssessmentOqFinal response = service.createEdAssessmentOqFinal(edAssessmentOqFinal);
		return new ResponseEntity<>(
				new ResponseMessage(ConstantMessage.RECORD_ADDED_SUCCESSFULLY, HttpStatus.OK, response), HttpStatus.OK);
	}

	@GetMapping(value = "/get_ed_assessment_oq_final_by_serviceid_termid")
	public ResponseEntity<?> getEdAssessmentOqFinalById(@RequestParam String serviceId, @RequestParam Long termId) {
		EdAssessmentOqFinal response = service.findByServiceIdAndTermId(serviceId, termId);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);

		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ADD, HttpStatus.OK, null), HttpStatus.OK);
		}
	}

	@GetMapping(value = "/get_ed_assessment_oq_final_by_serviceid")
	public ResponseEntity<?> getEdAssessmentOqFinalByServiceId(@RequestParam String serviceId) {
		List<EdAssessmentOqFinal> response = service.findByServiceId(serviceId);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.UPDATE, HttpStatus.OK, response),
					HttpStatus.OK);

		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.ADD, HttpStatus.OK, null), HttpStatus.OK);
		}
	}

	@PutMapping("/update_ed_assessment_oq_final")
	public ResponseEntity<?> updateEdAssessmentOqFinal(@RequestBody EdAssessmentOqFinal edAssessmentOqFinal) {
		EdAssessmentOqFinal response = service.updateEdAssessmentOqFinal(edAssessmentOqFinal);
		if (response != null) {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_UPDATED, HttpStatus.OK, response),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage(ConstantMessage.RECORD_NOT_FOUND, HttpStatus.OK, response),
					HttpStatus.OK);
		}
	}

}
